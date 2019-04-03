#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>           /* For O_* constants */
#include <sys/stat.h>        /* For mode constants */


#include "shm.h"
#include <semaphore.h>

int counter = 1;

int main()
{
    FILE * fp;
    int shmFd;
    SHMstruct * shmData;

    fp = fopen("/tmp/client.log", "a");
    if(fp == NULL) {
        perror("fopen");
        return EXIT_FAILURE;
    }

    shmFd = loadSHM(SHNAME);
    shmData = accessSHM(shmFd);

	sem_t * semiphore = sem_open(SHNAME, O_RDWR);
bool cond = false;
int x = 100;
int y;

    // Remember the condition value!!!
    while(cond != true)
    {
	sem_wait(semiphore);
	if(x==100){
	sem_getvalue(semiphore, &x);
	fprintf(stderr, "%d\n", x);
		switch(x){
			case 3:
				y = 0;
				break;
			case 2:
				y = 1;
				break;
			case 1:
				y = 2;
				break;
			default:
				y = 100;
				x = 100;
		}
	}
		sleep(1);
	cond = shmData->soldOut;
	if(y != 100 && shmData->isTaken[y] == false){
		
		shmData->isTaken[y] = true;
		fprintf(fp, "ticket %d acquired - %s\n", shmData->ticket[y], getTimeStamp());
		fprintf(stderr, "ticket %d acquired - %s\n", shmData->ticket[y], getTimeStamp());			
	}
	sem_post(semiphore);
    }

fprintf(fp, "tickets sold out - %s\n", getTimeStamp());
fprintf(stderr, "tickets sold out - %s\n", getTimeStamp());

    clearSHM(shmData);
    closeSHM(shmFd);
    fclose(fp);

	sem_close(semiphore);

    return EXIT_SUCCESS;
}

void s_pause(sem_t * semiphore){
	while(sem_wait(semiphore) != 0){}
}

void s_resume(sem_t * semiphore){
	while(sem_post(semiphore) != 0){}
}
