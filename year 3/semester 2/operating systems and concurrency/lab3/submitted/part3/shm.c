#include <stdbool.h>
#include <time.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

#include "shm.h"

// https://www.softprayog.in/programming/interprocess-communication-using-posix-shared-memory-in-linux

/* Generate a human-readable timestamp */
char * getTimeStamp() {
    time_t ltime = time(NULL);
    return strtok(ctime(&ltime), "\n");
}

/* Create Shared Memory Segment
 *
 * Function creates a named SHM file descriptor on the filesystem.
 *
 * @param shname Name of SHM
 * @return file descriptor
 */
int createSHM(char * shname)
{
int fd = shm_open(shname, O_CREAT | O_RDWR, S_IRUSR | S_IWUSR);
if (fd == -1){perror("shm_open");}

if (ftruncate(fd, sizeof(struct SHM)) == -1){perror("ftruncate");}

return fd;
}

/* Load Shared Memory
 *
 * Function loads an existing named SHM, or gracefully fails
 * when the SHM does not exist.
 *
 * @param shname Name of SHM region
 * @return file descriptor
 */
int loadSHM(char * shname)
{
int fd = shm_open(shname, O_RDWR, S_IRUSR | S_IWUSR);
if (fd == -1){perror("shm_open");}

if (ftruncate(fd, sizeof(struct SHM)) == -1){perror("ftruncate");}

return fd;
}

/* Access Existing SHM
 *
 * From an existing SHM file descriptor, allocate the SHMstruct and
 * return its pointer.
 *
 * @param fd File descriptor of existing SHM
 * @return Pointer to SHMstruct
 */
struct SHM * accessSHM(int fd) {
	struct SHM * temp = mmap(NULL, sizeof(struct SHM), 
PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
	if (temp == MAP_FAILED){perror("mmap");}
	return temp;
}

/* Initialise SHM
 *
 * From an existing SHM file descriptor, initilise the SHMstruct variable.
 *
 * HINT: use accessSHM()
 *
 * @param fd File descriptor of SHM
 * @return Pointer to SHMstruct
 */
struct SHM * initSHM(int fd, struct SHM *data) {
	struct SHM * temp = accessSHM(fd);
	memcpy (temp, data, sizeof (struct SHM));
	return temp;
}

/* De-allocate SHMstruct
 *
 * Function de-allocates an already existing SHMstruct.
 *
 * @param shm Pointer to SHMstruct
 */
void clearSHM(struct SHM * shm)
{
	if(munmap(shm, sizeof(struct SHM)) == -1){perror("munmap");}
}

/* Close SHM file descriptor
 *
 * Function closes an existing SHM file descriptor.
 *
 * @param fd File descriptor of SHM
 */
void closeSHM(int fd)
{
	if(close(fd) == -1){perror("close shm");}
}

/* Unlink SHM
 *
 * Function destroys an existing SHM assuming that its
 * allocated memory has been de-allocated.
 *
 * @param shname Name of SHM
 */
void destroySHM(char * shname)
{
	if(shm_unlink(shname) == -1){perror("shm destroy");}
}
