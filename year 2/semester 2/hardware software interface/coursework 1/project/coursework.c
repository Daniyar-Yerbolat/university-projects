# include <stdlib.h>
# include <stdio.h>
# include <string.h>
# include <stddef.h>
# include <inttypes.h>
//# include <limits.h>

struct PPM;
struct pixels;

char** splitString(char*, char*);
struct PPM * getPPM(FILE*);
void showPPM(struct PPM*);
struct PPM * copyPPM(struct PPM *);
struct PPM * encode(char*, struct PPM *);
void showPPM(struct PPM*);
char* decode(struct PPM*, struct PPM*);
int difference(struct PPM*, struct PPM*);
void makePPM(struct PPM*);
int duplicate(int[], int, int);
//void releasePPM(struct PPM*);
int contains(char*, int, char*, int);
int getLine(FILE*, char*, int);
int RNJesus(int, int);

// minimum 3 arguments required
int main(int argc, char** argv){
	//printf("%llu\n", RAND_MAX); // = 32767
	//printf("%"PRId64"\n", RAND_MAX);
	//printf("%d\n", INT_MAX);
	if(argc>3){
		// args[1] is d for decode
		// args[2] is original ppm
		// args[3] is altered ppm
		if(strcmp(argv[1], "d") == 0){
			FILE* ppm1 = fopen(argv[2], "r");
			struct PPM* original = getPPM(ppm1);
			fclose(ppm1);
			
			FILE* ppm2 = fopen(argv[3], "r");
			struct PPM* altered = getPPM(ppm2);
			fclose(ppm2);
			
			printf("%s\n", decode(original, altered));
		} 
		// args[1] is e for encode
		// args[2] is original ppm
		// args[3] is text you want to encode
		else if(strcmp(argv[1], "e") == 0){
			FILE* ppm1 = fopen(argv[2], "r");
			struct PPM* original = getPPM(ppm1);
			fclose(ppm1);
			
			struct PPM* altered = encode(argv[3], original);
			makePPM(altered);
		}
	}
	return 0;
}

struct pixel{
	unsigned char red;
	unsigned char green;
	unsigned char blue;
};

struct PPM {
	char* ext_code;
	char** comments;
	int number_of_comments;
	int width;
	int height;
	int max;
	struct pixel** pixels; // i wanted to do pixels[width*height], but it does not compile, so i will allocate memory after i create an instance of a struct.
};

struct PPM * getPPM(FILE * fd){
	struct PPM* image = malloc(sizeof(struct PPM));
	
	int n = 50;
	char temp[n];
	int length = 0;
		
	length = getLine(fd, &temp[0], n-1);
	if(contains("P3", 2, temp, n)){
		image->ext_code = malloc(length);
		strcpy(image->ext_code, temp);
		
		// &temp[0] instead of &temp is too avoid a compiler warning
		length = getLine(fd, &temp[0], n-1);
		int i = 0; //comment_counter
		
		// comments are optional, so i need to check whether they exist first, and only then allocate.
		if(contains("#", 1, temp, n)){	
			image->comments = malloc((i+1)*sizeof(char*));
			image->comments[i] = malloc(length);
			strcpy(image->comments[i], temp);
			i++;
			length = getLine(fd, &temp[0], n-1);
			while(contains("#", 1, temp, n)){
				// proper realloc
				char** comments_temp = realloc(image->comments, ((i+1)*sizeof(char*)));
				if(comments_temp!=NULL){
					image->comments = comments_temp;
				} else{
					free(comments_temp);
					exit(0);
				}
				image->comments[i] = malloc(length);
				strcpy(image->comments[i], temp);
				i++;
				length = getLine(fd, &temp[0], n-1);
			}	
		}
		image->number_of_comments = i;
		char** str = splitString(temp, " ");
		image->width = atoi(str[0]);
		image->height = atoi(str[1]);
		
		length = getLine(fd, &temp[0], n-1);
		image->max = atoi(temp); // atoi to convert to INT
		
		image->pixels = malloc(image->width * image->height * sizeof(struct pixel*));
		int counter = 0;
		// so that i won't repeat the calculation
		int resolution = image->width * image->height;
		
		while((length = getLine(fd, &temp[0], n-1)) && counter<resolution){
			image->pixels[counter] = malloc(sizeof(struct pixel));
			if(length>1){
				str = splitString(temp, " ");
				image->pixels[counter]->red = atoi(str[0]);
				image->pixels[counter]->green = atoi(str[1]);
				image->pixels[counter]->blue = atoi(str[2]);				
			} else{
				image->pixels[counter]->red = 0;
				image->pixels[counter]->green = 0;
				image->pixels[counter]->blue = 0;
			}
		counter++;
		}
		return image;
	} else{
		exit(0);
	}
	
}

void showPPM(struct PPM * i){
	printf("%s\n", i->ext_code);
	
	int counter = 0;
	while(counter < i->number_of_comments){
		printf("%s\n", i->comments[counter]);
		counter++;
	}
	printf("%d %d\n", i->width, i->height);
	
	printf("%d\n", i->max);
	counter = 0;
	int pixel_counter = i->width * i->height; // so that i would not repeat the calculation
	while(counter<pixel_counter){
		printf("%d. %d %d %d\n", counter, i->pixels[counter]->red, i->pixels[counter]->green, i->pixels[counter]->blue);
		counter++;
	}
}

/*
// function that i wanted to add to free up the heap.
// not working. program crashes.
void releasePPM(struct PPM* ppm){
	int x = 0;
	while(x<ppm->number_of_comments){
		free(ppm->comments[x]);
		x++;
	}
	free(ppm->comments);
	free(ppm->ext_code);
	x = 0;
	int until = ppm->height*ppm->width;
	while(x<until){
		free(ppm->pixels[x]);
		x++;
	}
	free(ppm->pixels);
	free(ppm);
}

*/

// deep copy
struct PPM * copyPPM(struct PPM* original){
	struct PPM* copy = malloc(sizeof(struct PPM));
	copy->ext_code = malloc(strlen(original->ext_code)+1);
	copy->ext_code = original->ext_code;
	copy->number_of_comments = original->number_of_comments;
	
	copy->comments = malloc (original->number_of_comments*sizeof(char*));
	int i=0;
	while(i<copy->number_of_comments){
		copy->comments[i] = malloc(strlen(original->comments[i])+1);
		copy->comments[i] = original->comments[i];
		i++;
	}
	
	copy->max = original->max;
	copy->width = original->width;
	copy->height = original->height;
	
	i=0;
	int resolution = copy->width * copy->height;
	copy->pixels = malloc(copy->width * copy->height * sizeof(struct pixel*));
	while(i<resolution){
		copy->pixels[i] = malloc (sizeof(struct pixel));
		copy->pixels[i]->red = original->pixels[i]->red;
		copy->pixels[i]->green = original->pixels[i]->green;
		copy->pixels[i]->blue = original->pixels[i]->blue;
		i++;
	}
	return copy;
}

struct PPM * encode(char * text, struct PPM * i){
	struct PPM * altered = copyPPM(i);
	int number;
	srand(1);
	
	// check if there are enough pixels to hold the text
	if(strlen(text) < altered->width * altered->height){
		int x = 0;
		int* rand_numbers = malloc(sizeof(int*));
		rand_numbers[0] = altered->width * altered->height; // using this value because the random number generater won't be able to get it
		while(x < strlen(text)){
			number = RNJesus(0, altered->width*altered->height);
			if((altered->pixels[number]->red != text[x]) && (duplicate(rand_numbers, x+1, number)==0)){
				altered->pixels[number]->red = text[x];
				rand_numbers[x] = number;
				x++;
				int* temp = realloc(rand_numbers, ((x+1)*sizeof(int*)));
				if(temp!=NULL){
					rand_numbers = temp;
				} else{
					free(temp);
					exit(0);
				}
			}
		}
	}
	return altered;
}

int duplicate(int* array, int length, int number){
	int x = 0;
	while(x<length){
		if(array[x]==number){
			return 1;
		}
	x++;	
	}
	return 0;
}

// i1 is original, i2 is altered
char * decode(struct PPM * original, struct PPM * altered){
	srand(1);
	int text_length = difference(original, altered);
	char* text =  malloc(text_length+1);
	int random;
	int x = 0;

	int* rand_numbers = malloc(sizeof(int*));
	rand_numbers[0] = altered->width * altered->height; // using this value because the random number generater won't be able to get it
	while(x < text_length){
		random = RNJesus(0, original->width * original->height);
		if((original->pixels[random]->red != altered->pixels[random]->red) && (duplicate(rand_numbers, x+1, random)==0)){
			text[x] = altered->pixels[random]->red;
			rand_numbers[x] = random;
			x++;
			int* temp = realloc(rand_numbers, ((x+1)*sizeof(int*)));
			if(temp!=NULL){
				rand_numbers = temp;
			} else{
				free(temp);
				exit(0);
			}
		}
	}
	text[x++] = '\0';
	return text;
}

int difference(struct PPM * i1, struct PPM * i2){
	int x = 0;
	int counter = 0;
	int pix_count = i1->width*i1->height;
	while(x < pix_count){
		if(i1->pixels[x]->red != i2->pixels[x]->red){
			counter++;
		}
		x++;
	}
	return counter;
}

// for windows - C:/Users/Daniel/Desktop/out.ppm
// for linux - /home/daniel-potter/Desktop/out.ppm ; pwd was used to get that
void makePPM(struct PPM * ppm){
	FILE * output = fopen("C:/Users/Daniel/Desktop/out.ppm", "w");
	fprintf(output, "%s\n", ppm->ext_code);
	int x = 0;
	while(x < ppm->number_of_comments){
		fprintf(output, "%s\n", ppm->comments[x]);
		x++;
	}
	fprintf(output, "%d %d\n", ppm->width, ppm->height);
	fprintf(output, "%d\n", ppm->max);
	x = 0;
	int total = ppm->width * ppm->height;
	while(x < total){
		fprintf(output, "%d %d %d\n", ppm->pixels[x]->red, ppm->pixels[x]->green, ppm->pixels[x]->blue);
		x++;
	}
	fclose(output);
}

int RNJesus(int lower_limit, int higher_limit){
	higher_limit -= 1;
	return lower_limit + rand() / (RAND_MAX / (higher_limit-lower_limit+1)+1);
}

char** splitString(char* string, char* delim){
	char** output = malloc(sizeof(char*));
	char* temp;
	int i = 0;
	temp = strtok(string, delim);
	while(temp!= NULL){
		output[i] = malloc(strlen(temp)+1);
		strcpy(output[i], temp);
		temp = strtok(NULL, delim);
		i++;
		
		char** reallocation = realloc(output, (i+1)*sizeof(char*));
		if(reallocation==NULL){
			free(reallocation);
			exit(0);
		} else{
			output = reallocation;
		}
	}
	return output;
}

// input for getline's n parameter should be (length of the string - 1)
// this is to null terminate a char array
int getLine(FILE * fin, char a[], int n){
	int counter = 0;
	char ch;
	while((ch = fgetc(fin))!='\n'){
		// use single quotes (') for chars
		if(counter<n){
			a[counter] = ch;
			counter++;
		} else if(ch==EOF){
			a[counter++] = '\0';
			return EOF;
		}
	}
	a[counter++] = '\0';
	return counter;
}

int contains(char target[], int m, char source[], int n){	
	int x = 0;
	int y = 0;
	
	int rollback = 0;
	
	while(n>x){
		if(source[x]==target[y]){
			rollback = 1;
			if((m-1)==y){
				return 1;
			}
			y++;
		} else if (rollback && source[x]!=target[y]){
			y = 0;
			rollback = 0;
			continue;
		}
		x++;
	}
	return 0;
}
