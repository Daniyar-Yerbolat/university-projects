#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <time.h>
#include <stdlib.h>

#include <stdbool.h>

#define N_THREADS 3
#define BUFFER_SIZE 200
#define N_DATA 100000
#define WORKLOAD1 100000
#define WORKLOAD2 100000
#define WORKLOAD3 100000
#define OUTPUT

/*******************************************************************************
 **  
 ** Here, the buffer implementation:
 **  
 ******************************************************************************/

struct timespec start[N_DATA], stop[N_DATA];
#define BILLION 1E9

typedef struct buffer buffer_t;

struct buffer {
  volatile int head;
  volatile int tail;
  int size;
  volatile int *elems;
bool mutex;
};

buffer_t *createBuffer( int size)
{
  buffer_t *buf;

  buf = (buffer_t *)malloc( sizeof(buffer_t));
  buf->head = 0;
  buf->tail = 0;
  buf->size = size+1;
  buf->elems = (int *)malloc( (size+1)*sizeof(int));
buf->mutex = false;

  return( buf);
}

int pop( buffer_t* buf, int *data)
{
  int res;
	// || buf->mutex == true
  if(buf->head == buf->tail) {
      res = 0;  
  } else {
	buf->mutex = true;
    *data = buf->elems[buf->head];
    buf->head = (buf->head+1) % buf->size;
    res = 1;
	buf->mutex = false;
  }

  return( res);
}


int push( buffer_t* buf, int data)
{
  int nextTail;
  int res;

  nextTail = (buf->tail + 1) % buf->size;
  if(nextTail != buf->head )   {
    buf->elems[buf->tail] = data;
    buf->tail = nextTail;
    res = 1;
  } else {
    res = 0;
  }

  return( res);
}


/*******************************************************************************
 **  
 ** Now, the thread functions for the pipelining:
 **  
 ******************************************************************************/

typedef struct threadArgs threadArgs_t;

struct threadArgs {
  int tid;
  buffer_t *in_buf;
  buffer_t *out_buf;
  int workload;
};

int workUnit( int data)
{
  if( data < 0)
    data++;

  return( data);
}

int process( int tid, int data, int  workload)
{
  int i;

#ifdef OUTPUT
    printf( "[%d] processing item %d!\n", tid, data);
#endif

  for( i=0; i<workload; i++)
    data = workUnit( data);

#ifdef OUTPUT
    printf( "[%d] item %d done!\n", tid, data);
#endif

  return( data);
}

void * pipeline( void *arg)
{
  int data;
  int workload;
  int suc;
  buffer_t *in;
  buffer_t *out;
  int tid;

  in = ((threadArgs_t *)arg)->in_buf;
  out = ((threadArgs_t *)arg)->out_buf;
  tid = ((threadArgs_t *)arg)->tid;

int x = 0;
while(1){
	while(pop(in, &data) == 1){
		data = process(tid, data, WORKLOAD1);
		data = process(tid, data, WORKLOAD2);
		data = process(tid, data, WORKLOAD3);
		while(push(out, data)==0){}	
	}
}

}




/*******************************************************************************
 ** 
 ** main
 ** 
 ******************************************************************************/

//gcc ./pipe_template.c -o pipeline -lpthread
int main()
{
	
  int i, suc;
  int data;


  threadArgs_t args[N_THREADS];
  pthread_t threads[N_THREADS];
  buffer_t *in, *out;

  in = createBuffer( N_DATA+1);
  out = createBuffer( N_DATA+1);

printf("Starting threads\n");

int x = 0;
while(x<N_THREADS){
args[x].tid = x+1;
args[x].in_buf = in;
args[x].out_buf = out;

pthread_create(&threads[x], NULL, pipeline, (void*) &args[x]);
x++;
}

printf("Filling first buffer\n");

x = 0;
srand(time(NULL));   
while(x < N_DATA){
	data = rand();
	if(push(in, data) == 1){
	printf("input buffer : data %d is %d\n", x+1, data);
	x++;
	}
}

x = 0;
while(x < N_DATA){
	if(pop(out, &data) == 1){
	printf("out buffer : data %d is %d\n", x+1, data);
	x++;
	}
}

  return(0);
}

