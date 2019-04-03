#define STRB 12
#define RS 6
#define DATA_1 20
#define DATA_2 21
#define DATA_3 26
#define DATA_4 16
#define DATA_5 19
#define DATA_6 13
#define DATA_7 5
#define DATA_8 7

#define BUTTON 14
#define YELLOW_LED 18
#define RED_LED 15

#define CLEAR_SCREEN 1
#define RETURN_HOME 2

#define ENTRY_MODE 4
#define ENTRY_INCREMENT 2
#define ENTRY_DECREMENT 0
#define ENTRY_SHIFT 1
#define ENTRY_STAY 0

#define DISPLAY1 8
#define DISPLAY1_ON 4
#define DISPLAY1_OFF 0
#define DISPLAY1_CURSOR_ON 2
#define DISPLAY1_CURSOR_OFF 0
#define DISPLAY1_BLINK_ON 1
#define DISPLAY1_BLINK_OFF 0

#define DISPLAY2 16
#define DISPLAY2_SCROLL_ON 8
#define DISPLAY2_SCROLL_OFF 0
#define DISPLAY2_RIGHT 4
#define DISPLAY2_LEFT 0

#define FUNCTION_SET 32
#define FUNCTION_4_BIT 0
#define FUNCTION_8_BIT 16
#define FUNCTION_2_LINES 8
#define FUNCTION_1_LINE 0
#define FUNCTION_FONT_SMALL 0
#define FUNCTION_FONT_LARGE 1

// OR it with 0-63
#define CHARACTER_GENERATION 64

// OR it with 0-127
// line 1 = 0x0 to 0xF
// line 2 = 0x40 to 0x4F
#define DDRAM 128

#define COMMAND 0
#define CHARACTER 1

#define OUTPUT 1
#define INPUT 0

#define SUCCESS 0
#define FAILURE 1

#define	PAGE_SIZE		(4*1024)
#define	BLOCK_SIZE		(4*1024)

#include <stdio.h>
#include <stdarg.h>
#include <stdint.h>
#include <stdlib.h>
#include <ctype.h>
#include <poll.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <time.h>
#include <fcntl.h>
#include <pthread.h>
#include <sys/time.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/ioctl.h>
#include <unistd.h>

struct pins;

void init_lcd();
void delay(unsigned int);
void strobe();
void send_data(int, unsigned char);
void write_to_data_pins(unsigned char);
void send_string(char[], int);
void position();
void reset_lcd();
void set_4_pin_mode();
void delayMicroseconds (unsigned int);
void command_4bits(unsigned char);
void change_position(int, int);

void gpiomode_w(int, int);
void init_gpio();
int gpiomode_r(int);
void gpioset(int);
void gpioclear(int);
int gpiolev(int);
int gpioeds_r(int);
void gpioeds_w(int, unsigned int);
void gpiofen(int, unsigned int);
void gpioren(int, unsigned int);
void reset_gpio();

void ctrlc_handler(int);
void blink(int, int);
int press_blink(int, int, int);
int detect_presses(int);
void print_lcd(char *);


static volatile unsigned int gpiobase ;
static volatile uint32_t *gpio ;
static struct pins * lcd_pins;
static int debug;

struct pins{
	int x, y;
	int data[8];
	int rs;
	int strb;
	int bit_mode;
};

//===================================================================================================
//============================ HIGH LEVEL FUNCTIONS =================================================
//===================================================================================================
int main(int argc, char** argv){

// DEBUG MODE
// shows print statements in the terminal while the game is running	
if(argc > 1 && (strcmp(argv[1], "d") == 0)){
	debug = 1;
} else {
	debug = 0;
}
	
//============================= LCD SETUP ===================================================
	lcd_pins = malloc(sizeof(struct pins));
	signal(SIGINT, ctrlc_handler);
	init_gpio();
	init_lcd(4);
	
	if(lcd_pins->bit_mode == 4){
		set_4_pin_mode();
	}

	send_data(COMMAND, FUNCTION_SET | (lcd_pins->bit_mode == 4 ? FUNCTION_4_BIT : FUNCTION_8_BIT) | FUNCTION_2_LINES | FUNCTION_FONT_SMALL);
	send_data(COMMAND, DISPLAY1 | DISPLAY1_ON | DISPLAY1_CURSOR_ON | DISPLAY1_BLINK_ON);
	send_data(COMMAND, DISPLAY2 | DISPLAY2_SCROLL_OFF | DISPLAY2_RIGHT);
	send_data(COMMAND, CLEAR_SCREEN);
	//send_data(COMMAND, RETURN_HOME);
	change_position(0, 0);
	send_data(COMMAND, ENTRY_MODE | ENTRY_INCREMENT | ENTRY_STAY);

//============================ GENERATE RANDOM SEQUENCE =================================================

	srand(time(NULL));
	int sequence[3];
	int i;
	for(i = 0; i < 3; i++){
		sequence[i] = (rand() % 3) + 1;
	}

	
	if(debug) printf("SECRET: %d %d %d\n", sequence[0],sequence[1],sequence[2]);

//======================================== GAME =====================================================
	
	int button, led1, led2;
	
	button = BUTTON;
	led1 = RED_LED;
	led2 = YELLOW_LED;
	
	gpiomode_w(led1, OUTPUT);
	gpiomode_w(led2,OUTPUT);
	gpiomode_w(button, INPUT);
	
	char buf[32];
	char * str = "MASTERMIND";
	print_lcd(str);
	delay(2000);
	send_data(COMMAND, CLEAR_SCREEN);

while(1){
	
	int guessnum = 0;
	int guesses[3];
	if(debug) printf("INPUT PHASE\n");
	
	//INPUT 3 TIMES
	while(guessnum < 3){
	if(debug) printf("Enter input %d\n",guessnum + 1);

		send_data(COMMAND, CLEAR_SCREEN);
		print_lcd("INPUT PHASE");
		change_position(1, 0);
		memset(buf, 0, strlen(buf));
		if(debug) sprintf(buf, "Enter number %d\n", guessnum + 1);
		print_lcd(buf);
	
		guesses[guessnum] = press_blink(button, led1, led2);
		guessnum++;
	}
	if(debug) printf("Input completed. Guesses: %d %d %d\n", guesses[0], guesses[1], guesses[2]);

	send_data(COMMAND, CLEAR_SCREEN);
	print_lcd("Input completed.");
	// END OF INPUT
	blink(2, led1);

	if(debug) printf("Checking answers...\n");
	
	//ANSWER CHECKING
	int c;
	int exact = 0;
	int approx = 0;
	for(c = 0; c < 3; c++){
		if(guesses[c] == sequence[c]){
			exact++;
		}else{
			int d;
			for(d = c; d < 3; d++){
				if(guesses[c] == sequence[d] && guesses[c] != sequence[c]){
					approx++;
				}
			}
		}
	}
	if(debug) printf("Exact matches: %d  Approximate matches: %d\n", exact, approx);

	send_data(COMMAND, CLEAR_SCREEN);
	if(debug) sprintf(buf, "Exact matches: %d\n", exact);
	print_lcd(buf);
	memset(buf, 0, strlen(buf));

	blink(exact, led2);
	
	//BLINK ONCE
	blink(1, led1);
	
	change_position(1,0);
	if(debug) sprintf(buf, "Part matches: %d\n", approx);
	print_lcd(buf);
	blink(approx, led2);
	
	send_data(COMMAND, CLEAR_SCREEN);
	if(exact == 3){
		print_lcd("YOU WIN! :)");
		if(debug) printf("Winner, Winner, Chicken Dinner\n"); 
		delay(3000);
		break;
	}
	
	blink(3, led1);
	print_lcd("NEXT ROUND.");
	delay(3000);
}

	reset_lcd();
	reset_gpio();
	
	exit(SUCCESS);
}

void blink(int times, int pin){
	int t;
	for(t = 0; t < times; t++){
		gpioset(pin);
		delay(700);
		gpioclear(pin);
		delay(700);
	}
}

int press_blink(int button, int led1, int led2){

	int presses = 0;
	int attempt = 0;

	// REPEATS if button was pressed more than 3 times.
	do {
		if(debug && (attempt != 0)){
			printf("%d presses. Maximum of 3 allowed. Re-enter input again.\n", presses);
		}
		presses = detect_presses(button);		
		attempt++;
	} while(presses > 3);

	if(debug) printf("Button pressed %d times.\n",presses);
	
	blink(1, led1);
	
	//LED BLINKS
	blink(presses, led2);
	
	return presses;
}
int detect_presses(int button){
	gpiofen(button, 1);
	gpioeds_w(button, 1);
	
	int counter = 0;
	int i = 0;
	while(i<40){
	if(debug) printf("%d\n", i);
	if(gpioeds_r(button)==1){
		gpioeds_w(button, 1);
		counter++;
	}
	delay(100);
	i++;
	}
	if(debug) printf("Input received.\n");
	send_data(COMMAND, CLEAR_SCREEN);
	if(debug) print_lcd("Input received");
	return counter;
}

void print_lcd(char * s){
	send_string(s, strlen(s));
}

//============================ CTRL C HANDLER =================================================

void ctrlc_handler(int sig) {
	reset_lcd();
	reset_gpio();
	exit(FAILURE);
}

//===================================================================================================
//============================ LCD LIBRARY (+ DELAY FUNCTIONS) ======================================
//===================================================================================================
void set_4_pin_mode(){
	int i = 0;
	while(i < 3){
	//0011
	command_4bits(3);
	delay(35);
	i++;
	}
	//0010
	command_4bits(2);
	
	if(debug) printf("4x2 pin mode set\n");
}

void command_4bits(unsigned char command){
	gpioclear(lcd_pins->rs);
	write_to_data_pins(command);
	strobe();
}

void init_lcd(int mode){
	if(mode==4){
		lcd_pins->data[0] = DATA_5;
		lcd_pins->data[1] = DATA_6;
		lcd_pins->data[2] = DATA_7;
		lcd_pins->data[3] = DATA_8;
	} else if (mode == 8){
		lcd_pins->data[0] = DATA_1;
		lcd_pins->data[1] = DATA_2;
		lcd_pins->data[2] = DATA_3;
		lcd_pins->data[3] = DATA_4;
		lcd_pins->data[4] = DATA_5;
		lcd_pins->data[5] = DATA_6;
		lcd_pins->data[6] = DATA_7;
		lcd_pins->data[7] = DATA_8;
	}
	lcd_pins->rs = RS;
	lcd_pins->strb = STRB;
	lcd_pins->bit_mode = mode;
	lcd_pins->x = 0;
	lcd_pins->y = 0;
	
	int i = 0;
	while(i < mode){
		gpiomode_w(lcd_pins->data[i], OUTPUT);
		gpioclear(lcd_pins->data[i]);
		i++;
	}
	gpiomode_w(lcd_pins->rs, OUTPUT);
	gpioclear(lcd_pins->rs);
	gpiomode_w(lcd_pins->strb, OUTPUT);
	gpioclear(lcd_pins->strb);		
}

void delayMicroseconds (unsigned int howLong)
{
  struct timespec sleeper ;
  unsigned int uSecs = howLong % 1000000 ;
  unsigned int wSecs = howLong / 1000000 ;

  /**/ if (howLong ==   0)
    return ;
#if 0
  else if (howLong  < 100)
    delayMicrosecondsHard (howLong) ;
#endif
  else
  {
    sleeper.tv_sec  = wSecs ;
    sleeper.tv_nsec = (long)(uSecs * 1000L) ;
    nanosleep (&sleeper, NULL) ;
  }
}

// milliseconds
void delay (unsigned int howLong)
{
  struct timespec sleeper, dummy ;

  sleeper.tv_sec  = (time_t)(howLong / 1000) ;
  sleeper.tv_nsec = (long)(howLong % 1000) * 1000000 ;

  nanosleep (&sleeper, &dummy) ;
}

// high to low
void strobe(){
	gpioset(lcd_pins->strb);
	delayMicroseconds(50);
	gpioclear(lcd_pins->strb);
	delayMicroseconds(50);
}

void send_data(int mode, unsigned char data){
	// 1 is for sending characters
	if(mode == CHARACTER){
		gpioset(lcd_pins->rs);
	// 0 is for sending commands
	} else if (mode == COMMAND) {
		gpioclear(lcd_pins->rs);
		
		if((data == CLEAR_SCREEN) || (data == RETURN_HOME)){
			lcd_pins->x = 0;
			lcd_pins->y = 0;
		}
	}
	if(lcd_pins->bit_mode == 4){
		write_to_data_pins((data >> 4) & 0x0F);
		strobe();
		write_to_data_pins(data & 0x0F);
		strobe();
	} else if(lcd_pins->bit_mode == 8){
		write_to_data_pins(data & 0xFF);
		strobe();
	}

	delay(30);
}

void write_to_data_pins(unsigned char data){
	int i = 0;
	while(i < lcd_pins->bit_mode){
		if((data >> i) & 1 == 1){
			gpioset(lcd_pins->data[i]);
		} else{
			gpioclear(lcd_pins->data[i]);
		}
		i++;
	}
}

void send_string(char str[], int str_length){
	int i=0;
	while(i<str_length){
		send_data(CHARACTER, str[i]);
		i++;
		position();
	}
}

void position(){
	lcd_pins->x += 1;
	// 0-15 column numbering
	if(lcd_pins->x > 15){
		lcd_pins->x = 0;
		lcd_pins->y += 1;
		send_data(COMMAND, DDRAM | 0x40);
	}
	// 0-1 row numbering
	if(lcd_pins->y > 1){
		send_data(COMMAND, CLEAR_SCREEN);
		lcd_pins->y = 0;
		lcd_pins->x = 0;
	}
}

// row - 0 or 1
// col - 0 to (including) 15
void change_position(int row, int col){
	if((row==0 || row==1) && (col>=0 && col<=15)){
		if(row==1){
			send_data(COMMAND, DDRAM | (0x40 + col));
		} else{
			send_data(COMMAND, DDRAM | col);
		}
	lcd_pins->x = col;
	lcd_pins->y = row;
	} else{
		exit(FAILURE);
	}
}

void reset_lcd(){
	send_data(COMMAND, CLEAR_SCREEN);
}

//===========================================================================================
//============================ GPIO LIBRARY =================================================
//===========================================================================================

void init_gpio(){
	int fd;
	gpiobase = 0x3F200000;
	if ((fd = open ("/dev/mem", O_RDWR | O_SYNC | O_CLOEXEC) ) < 0){
		if(debug) printf("less than 0\n");
    	exit(FAILURE);
	}

  	gpio = (uint32_t *)mmap(0, BLOCK_SIZE, PROT_READ|PROT_WRITE, MAP_SHARED, fd, gpiobase) ;
  	if ((int32_t)gpio == -1){
		if(debug) printf("equals -1\n");
    	exit(FAILURE);
	}
}

// 000 - input
// 001 - output
// function takes input 0 to 7
void gpiomode_w(int pin, int mode){
	if(mode>=0 && mode<=7 && pin>=0 && pin<=53){
		int select = pin/10;
		int bits = (pin - (select*10)) * 3;
		
		// should be universal, since (x | 0000) should be equal to x
		*(gpio+select) = (*(gpio+select) & ~(7 << bits)) | (mode << bits);
	} else {
		exit(FAILURE);
	}
}

int gpiomode_r(int pin){
	if(pin>=0 && pin<=53){
		int select = pin/10;
		int bits = (pin - (select*10)) * 3;
		
		return (*(gpio+select) << bits) & (7 << 0);
	} else{
		return -1;
	}
}

// takes input 1 or 0
void gpioset(int pin){
	if(pin>=0 && pin<=53){
		// GPSET1
		// 32 to 53
		if(pin>31){
		pin -= 32;
		*(gpio+8) = 1 << pin;
		// GPSET0
		// 0 to 31	
		} else{ 
		*(gpio+7) = 1 << pin;
		}
	} else {
		exit(FAILURE);
	}
}

// takes input 1 or 0
void gpioclear(int pin){
	if(pin>=0 && pin<=53){
		// GPCLR1
		// 32 to 53
		if(pin>31){
		pin -= 32;
		*(gpio+11) = 1 << pin;
		// GPCLR0
		// 0 to 31	
		} else{ 
		*(gpio+10) = 1 << pin;
		}
	} else {
		exit(FAILURE);
	}
}

// takes input 1 or 0
int gpiolev(int pin){
	if(pin>=0 && pin<=53){
		// GPLEV1
		// 32 to 53
		if(pin>31){
		pin -= 32;
		return (*(gpio+14) >> pin) & (1);
		// GPLEV0
		// 0 to 31	
		} else{
		return (*(gpio+13) >> pin) & (1);
		}
	} else {
		exit(FAILURE);
	}
}

int gpioeds_r(int pin){
	if(pin>=0 && pin<=53){
		// GPEDS1
		// 32 to 53
		if(pin>31){
		pin -= 32;
		return (*(gpio+17) >> pin) & (1);
		// GPEDS0
		// 0 to 31	
		} else{
		return (*(gpio+16) >> pin) & (1);
		}
	} else {
		exit(FAILURE);
	}
}

void gpioeds_w(int pin, unsigned int bit){
	if((bit==1 || bit==0) && pin>=0 && pin<=53){
		// GPEDS1
		// 32 to 53
		if(pin>31){
		pin -= 32;
		*(gpio+17) = (*(gpio+17) & ~(1 << pin)) | (bit << pin);
		// GPEDS0
		// 0 to 31	
		} else{ 
		*(gpio+16) = (*(gpio+16) & ~(1 << pin)) | (bit << pin);
		}
	} else {
		exit(FAILURE);
	}
}

void gpioren(int pin, unsigned int bit){
if((bit==1 || bit==0) && pin>=0 && pin<=53){
		// GPREN1
		// 32 to 53
		if(pin>31){
		pin -= 32;
		*(gpio+20) = (*(gpio+20) & ~(1 << pin)) | (bit << pin);
		// GPREN0
		// 0 to 31	
		} else{ 
		*(gpio+19) = (*(gpio+19) & ~(1 << pin)) | (bit << pin);
		}
	} else {
		exit(FAILURE);
	}
}

// falling edge
// when LEV changes from 1 to 0
void gpiofen(int pin, unsigned int bit){
if((bit==1 || bit==0) && pin>=0 && pin<=53){
		// GPRFEN1
		// 32 to 53
		if(pin>31){
		pin -= 32;
		*(gpio+23) = (*(gpio+23) & ~(1 << pin)) | (bit << pin);
		// GPFEN0
		// 0 to 31	
		} else{ 
		*(gpio+22) = (*(gpio+22) & ~(1 << pin)) | (bit << pin);
		}
	} else {
		exit(FAILURE);
	}
}

void reset_gpio(){
	int gpio_pins[] = {2, 3, 4, 14, 15, 17, 18, 27, 22, 23, 24, 10, 9, 11, 8, 7, 25, 5, 6, 12, 13, 19, 16, 26, 20, 21};
	int i = 0;
	int size = sizeof(gpio_pins)/sizeof(gpio_pins[0]);
	while(i < size){
		gpiomode_w(gpio_pins[i], INPUT);
		gpiofen(gpio_pins[i], 0);
		gpioren(gpio_pins[i], 0);
		gpioclear(gpio_pins[i]);
		i++;
	}
}