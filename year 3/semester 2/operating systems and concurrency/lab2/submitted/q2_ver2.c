#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h>

void *print_message_function( void *ptr );
void *parent_print_message_function( void *ptr );

 /*
 "I am the parent thread"
 "The parent is done"
 "I am the child thread"
 "The child is done"
 */

int non_concurrent = 0;

pthread_mutex_t mutex;
     
struct thread_data{
   char* first_message;
   char* second_message;
};

pthread_t thread1, thread2;

int main(int argc, char** argv)
{
if (argc == 2){
	
	int parent, child;
	    
    pthread_mutex_init(&mutex, NULL);
    
    struct thread_data * data1 = malloc(sizeof(struct thread_data));
    struct thread_data * data2 = malloc(sizeof(struct thread_data));
	data1->first_message = "I am the parent thread";
	data1->second_message = "The parent is done";
	
	data2->first_message = "I am the child thread";
	data2->second_message = "The child is done";
	
	if(strcmp(argv[1], "a") == 0 || strcmp(argv[1], "c") == 0){
		parent = pthread_create( &thread1, NULL, print_message_function, (void*) data1);
		child = pthread_create( &thread2, NULL, print_message_function, (void*) data2);
	} else if(strcmp(argv[1], "b") == 0){
		parent = pthread_create( &thread1, NULL, parent_print_message_function, (void*) data1);
		child = pthread_create( &thread2, NULL, print_message_function, (void*) data2);
	}
	// wait till thread 1 is done
	pthread_join(thread1, NULL);
	pthread_join(thread2, NULL);
	
	pthread_exit(NULL);
}

exit(0);
}

void *print_message_function( void *ptr )
{
	struct thread_data * temp = ptr;
    printf("%s \n", temp->first_message);    
    printf("%s \n", temp->second_message);
}

void *parent_print_message_function( void *ptr )
{
	pthread_mutex_lock(&mutex);
	struct thread_data * temp = ptr;
    printf("%s \n", temp->first_message);
	pthread_mutex_unlock(&mutex);
	
	pthread_join(thread2, NULL);
	
    printf("%s \n", temp->second_message);
}
