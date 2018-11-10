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
    struct SHM initData = { {ticket_counter, ++ticket_counter, ++ticket_counter}, {false, false, false}, false};
    struct SHM * shmData;
    
    fp = fopen("/tmp/server.log", "a");
    if(fp == NULL) {
        perror("fopen");
        return EXIT_FAILURE;
    }

	//sem_unlink(SHNAME); exit(1);


    fprintf(stderr, "Shared Memory Area created\n");
    shmFd = createSHM(SHNAME);
    shmData = initSHM( shmFd, &initData );

	sem_t * semiphore = sem_open(SHNAME, O_CREAT, 0644, 5);

int previous_num[3] = {0};
sem_wait(semiphore);
    // Remember the condition value!!!
    while(ticket_counter <= MAX_TICKETS)
    {
	
	int x = 0;
	while(x<3){
		//fprintf(stderr, "ticket %d\n", shmData->ticket[x]);
		if(shmData->ticket[x] != 100 && previous_num[x] != shmData->ticket[x]){
			fprintf(fp, "ticket %d - %s\n", shmData->ticket[x], getTimeStamp());
			fprintf(stderr, "ticket %d - %s\n", shmData->ticket[x], getTimeStamp());
		}
	previous_num[x] = shmData->ticket[x];
	x++;
	}

	x = 0;
	while(x<3){
		if(shmData->ticket[x] != 100 && shmData->isTaken[x] == true){
			fprintf(fp, "ticket %d taken - %s\n", shmData->ticket[x], getTimeStamp());
			fprintf(stderr, "ticket %d taken - %s\n", shmData->ticket[x], getTimeStamp());
			ticket_counter++;
			sleep(MAX_SLEEP);
				//reset
				if(ticket_counter<MAX_TICKETS) {
				shmData->ticket[x] = ticket_counter;
				shmData->isTaken[x] = false;
				} else {
					shmData->ticket[x] = 100;
				}
		}
	x++;
	}
	
    }

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

void s_pause(sem_t * semiphore){
	while(sem_wait(semiphore) != 0){}
}

void s_resume(sem_t * semiphore){
	while(sem_post(semiphore) != 0){}
}
