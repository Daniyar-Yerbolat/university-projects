#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>

struct node {
    int value;
    struct node * next;
};

static struct node * top_node = NULL;
static int push_counter = 0;
static int pop_counter = 0;

int push(int value)
{
    struct node* tmp = (struct node*) malloc(sizeof(struct node));
    if(tmp == NULL){exit(0);}
    
    tmp->value = value;
    
    if(top_node == NULL){
    	tmp->next = NULL;
	} else{
		tmp->next = top_node;
	}
    
    top_node = tmp;
    
    return ++push_counter;
}

int pop(int* val)
{
	*val = 0;
	if(top_node == NULL){return pop_counter;}
    struct node* tmp = top_node;
    *val = top_node->value;
    top_node = top_node->next;
    free(tmp);
    return ++pop_counter;
}

int top()
{
	if (top_node == NULL){return 0;}
   return top_node->value;
}
