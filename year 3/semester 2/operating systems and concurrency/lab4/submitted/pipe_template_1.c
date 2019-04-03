#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>
#include <time.h>
#include <stdlib.h>

#define N_THREADS 3
#define BUFFER_SIZE 200
#define N_DATA 100000
#define WORKLOAD1 100000
#define WORKLOAD2 100000
#define WORKLOAD3 100000
//#define OUTPUT

/*******************************************************************************
 **  
 ** Here, the buffer implementation:
 **  
 ******************************************************************************/

typedef struct buffer buffer_t;

struct buffer {
  volatile int head;
  volatile int tail;
  int size;
  volatile int *elems;
};

buffer_t *createBuffer( int size)
{
  buffer_t *buf;

  buf = (buffer_t *)malloc( sizeof(buffer_t));
  buf->head = 0;
  buf->tail = 0;
  buf->size = size+1;
  buf->elems = (int *)malloc( (size+1)*sizeof(int));

  return( buf);
}

int pop( buffer_t* buf, int *data)
{
  int res;

  if(buf->head == buf->tail) {
      res = 0;  
  } else {
    *data = buf->elems[buf->head];
    buf->head = (buf->head+1) % buf->size;
    res = 1;
  }

  return( res);
}


int push( buffer_t* buf, int data)
{
  int nextTail;
  int res;

  nextTail = (buf->tail + 1) % buf->size;
  if(nextTail != buf->head)   { 
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
  workload = ((threadArgs_t *)arg)->workload;

while(1){
	while(pop(in, &data) == 1){
		process(tid, data, workload);
		while(push(out, data)==0){}
	}
}

}

int main()
{
	
  int i, suc;
  int data;

  threadArgs_t args[N_THREADS];
  pthread_t threads[N_THREADS];
  buffer_t *in, *inter1, *inter2, *out;

  in = createBuffer( N_DATA+1);
  inter1 = createBuffer( BUFFER_SIZE);
  inter2 = createBuffer( BUFFER_SIZE);
  out = createBuffer( N_DATA+1);

printf("Starting threads\n");

args[0].tid = 1;
args[0].in_buf = in;
args[0].out_buf = inter1;
args[0].workload = WORKLOAD1;

args[1].tid = 2;
args[1].in_buf = inter1;
args[1].out_buf = inter2;
args[1].workload = WORKLOAD2;

args[2].tid = 3;
args[2].in_buf = inter2;
args[2].out_buf = out;
args[2].workload = WORKLOAD3;

int x = 0;
while(x<N_THREADS){
pthread_create(&threads[x], NULL, pipeline, (void*) &args[x]);
x++;
}

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


