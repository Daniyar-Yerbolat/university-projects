all: server client

ifdef OSX
CC      = gcc
CCFLAGS = -Wall
LIBS    = 

else
CC      = gcc
CCFLAGS = -Wall
LIBS    = -lrt -pthread
endif

server: server.c shm.h shm.o
	$(CC) $(CCFLAGS) shm.o -o $@ $< $(LIBS)

client: client.c shm.h shm.o
	$(CC) $(CCFLAGS) shm.o -o $@ $< $(LIBS)

shm.o: shm.c	
	$(CC) $(CCFLAGS) -c $< $(LIBS)

clean:
	$(RM) *.out
