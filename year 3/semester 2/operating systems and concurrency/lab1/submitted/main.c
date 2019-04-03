#include <stdlib.h>
#include <stdio.h>
 
#include "linkedlist.c"
 
int main()
{
	int i;
	printf("pushing stage\n");
	for( i=1; i<15; i++) {
	push(i);
	printf("%d\n", top());
	}


printf("\n\n");
printf("popping stage\n");
	int x;
	for( i=0; i<19; i++) {
	printf("top - %d\n", top());
	pop(&x);
	printf("popped - %d\n", x);
	}
    return 0;
}
   
		


				
	
 
		
        
	
		

		
	


 
   
