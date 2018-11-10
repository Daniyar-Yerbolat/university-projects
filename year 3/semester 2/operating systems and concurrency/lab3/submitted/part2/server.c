#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>           /* For O_* constants */
#include <sys/stat.h>        /* For mode constants */

#include "shm.h"
#include <semaphore.h>

int ticket_counter = 1;

int main()
{
    FILE * fp;
    int shmFd;
    struct SHM initData = { ticket_counter, false, false};
    struct SHM * shmData;
    
    fp = fopen("/tmp/server.log", "a");
    if(fp == NULL) {
        perror("fopen");
        return EXIT_FAILURE;
    }

    fprintf(stderr, "Shared Memory Area created\n");
    shmFd = createSHM(SHNAME);
    shmData = initSHM( shmFd, &initData );

	sem_t * semiphore = sem_open(SHNAME, O_CREAT, 0644, 1);

int previous_num = 0;

    // Remember the condition value!!!
    while(ticket_counter < MAX_TICKETS)
    {
	if(previous_num != ticket_counter){
		fprintf(fp, "ticket %d - %s\n", ticket_counter, getTimeStamp());
		fprintf(stderr, "ticket %d - %s\n", ticket_counter, getTimeStamp());
	}

previous_num = ticket_counter;


	sem_wait(semiphore);
		if(shmData->isTaken == true){
			fprintf(fp, "ticket %d taken - %s\n", ticket_counter, getTimeStamp());
			fprintf(stderr, "ticket %d taken - %s\n", ticket_counter, getTimeStamp());
			ticket_counter++;
			sleep(MAX_SLEEP);
			shmData->ticket = ticket_counter;
			shmData->isTaken = false;
		}
	sem_post(semiphore);
    }

sem_wait(semiphore);
shmData->soldOut = true;
sem_post(semiphore);
	
fprintf(fp, "tickets sold out - %s\n", getTimeStamp());
fprintf(stderr, "tickets sold out - %s\n", getTimeStamp());

    fprintf(stderr, "Shared Memory Area destroyed\n");
    clearSHM(shmData);
    closeSHM(shmFd);
    destroySHM(SHNAME);
    fclose(fp);
	sem_close(semiphore);
	sem_unlink(SHNAME);

    return EXIT_SUCCESS;
}
