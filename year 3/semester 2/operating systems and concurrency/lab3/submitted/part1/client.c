#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>


#include "shm.h"

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

    // Remember the condition value!!!
    while(shmData->soldOut != true)
    {
	shmData->isTaken = true;
	fprintf(fp, "ticket %d acquired - %s\n", shmData->ticket, getTimeStamp());
	fprintf(stderr, "ticket %d acquired - %s\n", shmData->ticket, getTimeStamp());
	while(shmData->isTaken == true && shmData->soldOut == false){sleep(MAX_SLEEP);}
    }

	fprintf(fp, "tickets sold out - %s\n", getTimeStamp());
	fprintf(stderr, "tickets sold out - %s\n", getTimeStamp());

    clearSHM(shmData);
    closeSHM(shmFd);
    fclose(fp);

    return EXIT_SUCCESS;
}
