person(jake).
person(jill).
person(john).
person(joan).

likes(jake,tomato).
likes(john,cheese).
likes(joan,cheese).
likes(joan,tomato).
likes(jill,cheese).
likes(jill,tomato).
likes(X, pizza):- likes(X,tomato), likes(X,cheese).

knows(jake,jill).
knows(jill,john).
knows(john,joan).
knows(joan,jake).
knows(X,X).


know(X, F):- likes(Y, F), knows(X, Y).



/** Questions
* likes(jake,pizza).
*
* likes(X, pizza), write(X), nl, fail.
* or 
* findall(X, likes(X,pizza), L).
* which puts all facts that are true to the list.
* or just press ; , which means pretty much says to Prolog that this value is incorrect
* and it should give you another value.
*
* findall(X, know(X, pizza), L).
* this one will print all 4 names. Only joan and jill like pizza, but
* jake knows jill, and john knows joan
* and everyone knows themselves.
*/

place(aberdeen).
place(dundee).
place(edinburgh).
place(glasgow).
place(kirkcaldy).
place(standrews).

distance(aberdeen, dundee, 60).
distance(dundee, edinburgh, 60).
distance(standrews, edinburgh, 45).
distance(dundee, standrews, 10).
distance(dundee, aberdeen, 60).
distance(standrews, kirkcaldy, 30).
distance(kirkcaldy, edinburgh, 35).
distance(glasgow, edinburgh, 45).
distance(X,X,0).

miles(X,Y,Z):- distance(X,Y,Z).
miles(X,Y,Z):- distance(Y,X,Z).


distance2(X,Y,Z):- miles(X,Y,Z), Z is Z.
distance2(X,Y,Z):- miles(X, W, Z2),  distance2(W, Y, Z3), Z is (Z2+Z3).

/** Questions
* distance2(edinburgh, standrews, Z).
*
* distance2(aberdeen, glasgow, Z).
* this function has one flaw. It will pick the first fact that abides the query.
* aberdeen to glasgow gives 165 which is the shortest
* but abardeen to kirkcaldy will give 155, but it can be 105.
* all my credit goes to the trace - very useful for debugging code.
*/