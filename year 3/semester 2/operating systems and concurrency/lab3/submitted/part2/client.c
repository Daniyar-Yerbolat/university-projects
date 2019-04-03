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

    // Remember the condition value!!!
    while(cond != true)
    {

	sem_wait(semiphore);
		cond = shmData->soldOut;
		if(shmData->isTaken == false){
			shmData->isTaken = true;
			fprintf(fp, "ticket %d acquired - %s\n", shmData->ticket, getTimeStamp());
			fprintf(stderr, "ticket %d acquired - %s\n", shmData->ticket, getTimeStamp());		
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
