#include <stdio.h>   /* printf, stderr, fprintf */
#include <sys/types.h> /* pid_t */
#include <unistd.h>  /* _exit, fork */
#include <stdlib.h>  /* exit */
#include <errno.h>   /* errno */
#include <sys/wait.h> /*wait */

int main(void)
{
 pid_t  pid;
 int status;
 pid = fork();
    if (pid == 0) {
        printf("I am the child process.\n");
        printf("The child is done \n");
    } else {
    printf("This is the parent process.\n" );
    wait(&status);
    printf("The parent is done \n");
	}
 
 exit(1);

}

