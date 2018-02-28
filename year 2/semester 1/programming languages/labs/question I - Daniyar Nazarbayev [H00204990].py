# Question I, #1

def factors (n):
    llist = []
    prime_list = [2, 3, 5, 7, 11, 13, 17]
    while(n>2):
        x = 0
        while(len(prime_list) > x):
            if(n%prime_list[x] == 0):
                llist.append(prime_list[x])
                n = n / prime_list[x]
                break
            x = x + 1
    return llist

# Question I, #2

def largest (llist):
    curr_large = 0
    x = 0
    while(len(llist) > x):
        if (llist[x] > curr_large):
            curr_large = llist[x]
        x = x + 1
    return curr_large

# Question I, #3

def largest_factor (llist):
    return largest(llist)

# Question I, #4

def firstbigfib (n):
    a = 1 # index 0
    b = 1 # index 1
    temp = 0
    count = 1 # because indexing starts from 0
    while(len(str(b))<n):
        temp = b
        b = b + a
        a = temp
        count = count + 1
    return count

# for reference, use this table
# http://jwilson.coe.uga.edu/EMAT6680Su12/Ferra/MFWU12/WU12.3.png

# Question I, #5

def firstbigf (f, n):
    x = 0
    while (len(str(f(x))) != n):
        x = x + 1
    return x

# Question I, #6

# In general, it is said that python is only optimized for single threading.

# that said, i assume, we can divide and conquer.
# Make each core work on an instance of the function
# each having a different starting value for x.

# Question I, #7

def triples():
    z = 0
    lset = set()
    while True:
        x = 1
        while(x<z):
            y = 1
            while(y<z):
                if(x*x + y*y == z*z):
                    if((x,y,z) not in lset and (y,x,z) not in lset):
                        lset.add((x,y,z))
                        yield (x,y,z)
                y = y + 1
            x = x + 1
        z = z + 1

# this was a very tricky exercise.
# I finished the first 6 exercises under 15 mins or so.
# but this took some time.
# The exercise states that the program should get all the triples, like there

# http://www.tsm-resources.com/alists/trip.html

# yet no matter how hard i tried, the program won't be able to get them all.

# when my methods stopped working, i went to the internet
# to look for the universal formula of the triples.
# i found this - 2mn, m**2 - n**2, m**2 + n**2

# that said, this formula is only able to give primitive triples,
# "and majority of the multiple triples" - quoting what was said there.
# this formula wouldn't be able to give something like (9,12,15) for example.

# then after thinking for some time, i decided to create 3 nested loops.
# i would only increment var z, and would initiate x and y to 1 during each
# iteration of the first loop.
# what this does is checks every possible combination of x and y for that z
# starting from (1,1,z)
# and by definition, neither x or y can be equal or more than z.
# this has very high complexity, but it does what the exercise asks for.

# i also added a set to my function, so that i won't repeat my triples
# like (3,4,5) and (4,3,5)
        
        
    
