#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "stack.h"

static int mockUp = 0;
static int *stackTop = &mockUp;

static int default_stack[DEFAULT_STACK_SIZE];
static int *stack = default_stack;
static int stack_size = DEFAULT_STACK_SIZE;

static int pop_counter = 0;
static int push_counter = 0;

int getStackSize()
{
  return stack_size;
}

void setStackSize( int size)
{
	int old_stack_size = getStackSize();
	stack_size = size;
	int * new_stack = calloc(size, sizeof(int));
	
	if(old_stack_size > size){
		//printf("Number of elements in the old stack is %d. Number of elements in the new stack is %d. Will result in lose of elements. Operation cancelled.\n", (push_counter-pop_counter), size);
		//exit(0);
	}
	
	int x = 0;
	while(x < old_stack_size && x < size){
		new_stack[x] = stack[x];
		printf("%d\n", new_stack[x]);
		x++;
	}
	
	(size != 0 && old_stack_size != 0) ? (stackTop = &new_stack[x-1]) : (stackTop = &mockUp);

	if(stack != default_stack){
		free(stack);
	}
	
	stack = new_stack;
}

void deleteStack()
{
	stack = default_stack;
	stackTop = &mockUp;
}

int top()
{
   return *stackTop;
}

int pop( int *val)
{
	*val = 0;
	if (stackTop >= &stack[0] && stackTop <= &stack[getStackSize()]) {
		*val = *stackTop;
		(stackTop-1 < &stack[0]) ? (stackTop = &mockUp) : (stackTop = stackTop - 1);
		return ++pop_counter;
	} else{
		return pop_counter;
	}
}

int push( int val)
{
if(stackTop == &mockUp && getStackSize() != 0){
	stackTop = &stack[0];
	*stackTop = val;
    return ++push_counter;
} else if(stackTop >= &stack[0] && stackTop <= &stack[getStackSize()]){
    if (stackTop+1 <= &stack[getStackSize()]){
    	stackTop = stackTop + 1;
    	*stackTop = val;
    	return ++push_counter;
	}
}
return push_counter;
}
