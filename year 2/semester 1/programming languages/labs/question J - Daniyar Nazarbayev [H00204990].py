# answers to other questions are in .docx file.

# Question J #5

def even(n):
    return [x for x in range(n*2) if x%2==0]

# after thinking for a while i realized that after every even number there is
# and odd number, and after every odd number there is an even number
# and that's how i made the program display first n even numbers.
# i simply multiplied n by 2

# Question J #6

def divide3(llist):
    return [x for x in llist if x%3==0]

# Question J #7

def ziplists(list1, list2):
    return [(list1[x],list2[x]) for x in range(len(list1))]
        
# Question J #8

# Low complexity, but can run out of memory when the input is 100,000,000.
def penta_square(bound):
    pentagonals = [x*(3*x-1)//2 for x in range(bound)]
    squares = set([x*x for x in range(bound)])
    return squares.intersection(pentagonals)

# High complexity - O(n2).
def penta_square2(bound):
    return [x*(3*x-1)//2 for x in range(bound) for y in range((x*(3*x-1)//2)+1) if x*(3*x-1)//2==y*y]

# Identical to the 2nd function - O(n2).
def penta_square3(bound):
    pentagonals = [x*(3*x-1)//2 for x in range(bound)]
    return [y*y for x in pentagonals for y in range(x+1) if x==y*y]

# Okaish complexity, O(n).
import math
def penta_square4(bound):
    return [x*(3*x-1)//2 for x in range(bound) if (math.sqrt(x*(3*x-1)//2)).is_integer()]

# Question J #9

def nest(n):
    if (n==0):
        return []
    else:
        return [nest(n-1)]

def unnest(llist):
    counter = 0
    while(type(llist) is list and len(llist)>0):
        counter = counter + 1
        llist = llist[0]
    return counter

import copy
def add_nests(list1, list2):
    temp1 = copy.copy(list1)
    temp2 = copy.copy(list2)
    while(len(list2)>0):
        emptylist = [] # [].append([[]])  should give [[[]]]
        list2 = list2[0]
        emptylist.append(list1)
        list1 = emptylist[:] # deep copy
    output = list1[:]
    list1 = temp1
    list2 = temp2
    return output


def mult_nests(list1, list2):
    temp1 = copy.copy(list1)
    temp2 = copy.copy(list2)
    if(list2==[] or list1==[]):
        return []

    lcopy = list1[:]
    list1 = [] # for multiplying by 1.
    while(len(list2)>0):
        list2 = list2.pop(0)
        list1 = add_nests(list1, lcopy)
    output = list1[:]
    list1 = temp1
    list2 = temp2
    return output
