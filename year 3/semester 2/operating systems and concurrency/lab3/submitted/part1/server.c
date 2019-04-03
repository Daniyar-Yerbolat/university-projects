#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "shm.h"

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

    // Remember the condition value!!!
    while(ticket_counter <= MAX_TICKETS)
    {
	shmData->ticket = ticket_counter;
	shmData->isTaken = false;
	shmData->soldOut = false;

	fprintf(fp, "ticket %d - %s\n", ticket_counter, getTimeStamp());
	fprintf(stderr, "ticket %d - %s\n", ticket_counter, getTimeStamp());

        // Fill in here
	while(shmData->isTaken != true){sleep(MAX_SLEEP);}
	fprintf(fp, "ticket %d taken - %s\n", ticket_counter, getTimeStamp());
	fprintf(stderr, "ticket %d taken - %s\n", ticket_counter, getTimeStamp());
	ticket_counter++;
    }
	shmData->soldOut = true;
fprintf(fp, "tickets sold out - %s\n", getTimeStamp());
fprintf(stderr, "tickets sold out - %s\n", getTimeStamp());

    fprintf(stderr, "Shared Memory Area destroyed\n");
    clearSHM(shmData);
    closeSHM(shmFd);
    destroySHM(SHNAME);
    fclose(fp);

    return EXIT_SUCCESS;
}
