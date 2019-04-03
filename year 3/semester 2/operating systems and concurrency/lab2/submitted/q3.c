#include <stdio.h>   /* printf, stderr, fprintf */
#include <sys/types.h> /* pid_t */
#include <unistd.h>  /* _exit, fork */
#include <stdlib.h>  /* exit */
#include <errno.h>   /* errno */
#include <sys/wait.h> /*wait */

int main(void)
{
 pid_t  pid;
 int fd[2];
 int done = 0;
 int status;
 
 if (pipe(fd) == -1)
    return (1);
 
 pid = fork();
    if (pid == 0) {
    	
    close (fd[1]);
    while(done == 0){
      read(fd[0], &done, sizeof(done));
  	}
  	
    printf("I am the child process.\n");
    printf("The child is done \n");
    exit(0);
    }
    else {
    close(fd[0]);
    printf("This is the parent process.\n" );
    
    done = 1;
    write(fd[1], &done, sizeof(done));
    
    wait(&status);
    printf("The parent is done \n");
    exit(1);
 }
}
