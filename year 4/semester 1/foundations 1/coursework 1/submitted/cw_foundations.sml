(* M *)
datatype LEXP = APP of LEXP * LEXP | LAM of string * LEXP | ID of string;
(* A - de Bruijn indices *)
datatype BEXP = BAPP of BEXP * BEXP | BLAM of BEXP | BID of int;
(* M' - item notation *)
datatype IEXP = IAPP of IEXP * IEXP | ILAM of string * IEXP | IID of string;
(* A' - de Bruijn indices item notation *)
datatype IBEXP = IBAPP of IBEXP * IBEXP | IBLAM of IBEXP | IBID of int;

(* Task 3 *)	
	
val vx = (ID "x");
val vy = (ID "y");
val vz = (ID "z");
val t1 = (LAM("x",vx));
val t2 = (LAM("y",vx));
val t3 = (APP(APP(t1,t2),vz));
val t4 = (APP(t1,vz));
val t5 = (APP(t3,t3));
val t6 = (LAM("x",(LAM("y",(LAM("z",(APP(APP(vx,vz),(APP(vy,vz))))))))));
val t7 = (APP(APP(t6,t1),t1));
val t8 = (LAM("z", (APP(vz,(APP(t1,vz))))));
val t9 = (APP(t8,t3));

val Ivx = (IID "x");
val Ivy = (IID "y");
val Ivz = (IID "z");
val It1 = (ILAM("x",Ivx));
val It2 = (ILAM("y",Ivx));
val It3 = (IAPP(Ivz, IAPP(It2,It1)));
val It4 = (IAPP(Ivz,It1));
val It5 = (IAPP(It3,It3));
val It6 = (ILAM("x",(ILAM("y",(ILAM("z",(IAPP((IAPP(Ivz,Ivy),IAPP(Ivz,Ivx))))))))));
val It7 = (IAPP(It1,IAPP(It1,It6)));
val It8 = (ILAM("z", (IAPP((IAPP(Ivz,It1),Ivz)))));
val It9 = (IAPP(It3,It8));

val Bvx = (BID 1);
val Bvy = (BID 2);
val Bvz = (BID 3);
val Bt1 = (BLAM(Bvx));
val Bt2 = (BLAM(BID 2));
val Bt3 = (BAPP(BAPP(Bt1,Bt2),Bvz));
val Bt4 = (BAPP(Bt1,Bvz));
val Bt5 = (BAPP(Bt3,Bt3));
val Bt6 = (BLAM (BLAM (BLAM (BAPP(BAPP(BID 3,BID 1),BAPP(BID 2,BID 1))))));
val Bt7 = (BAPP(BAPP(Bt6,Bt1),Bt1));
val Bt8 = (BLAM (BAPP(BID 1,(BAPP(Bt1,BID 1)))));
val Bt9 = (BAPP(Bt8,Bt3));

val IBvx = (IBID 1);
val IBvy = (IBID 2);
val IBvz = (IBID 3);
val IBt1 = (IBLAM IBvx);
val IBt2 = (IBLAM(IBID 2));
val IBt3 = (IBAPP(IBvz,IBAPP(IBt2,IBt1)));
val IBt4 = (IBAPP(IBvz,IBt1));
val IBt5 = (IBAPP(IBt3,IBt3));
val IBt6 = (IBLAM (IBLAM (IBLAM (IBAPP((IBAPP(IBID 1,IBID 2),IBAPP(IBID 1,IBID 3)))))));
val IBt7 = (IBAPP(IBt1,IBAPP(IBt1,IBt6)));
val IBt8 = (IBLAM (IBAPP(IBAPP(IBID 1,IBt1),IBID 1)));
val IBt9 = (IBAPP(IBt3,IBt8));

(* Task 7 *)

val w = (APP(LAM("x",APP(ID "x",ID "x")),LAM("x",APP(ID "x",ID "x"))));

val Iw = (IAPP(ILAM("x",IAPP(IID "x",IID "x")),ILAM("x",IAPP(IID "x",IID "x"))));

val Bw = (BAPP(BLAM(BAPP(BID 1, BID 1)),BLAM(BAPP(BID 1, BID 1))));

val IBw = (IBAPP(IBLAM(IBAPP(IBID 1, IBID 1)),IBLAM(IBAPP(IBID 1, IBID 1))));

(* Task 5 

val listorder = ["x","y","z", "x'", "y'", "z'", "x1", "y1", "z1", "x2", "y2", "z2"]
*)

val listorder = ["x","y","z", "x'", "y'", "z'", "x1", "y1", "z1", "x2", "y2", "z2"]

(* M -> M' *)	
fun itran (ID id) = (IID id)
	| itran (LAM(id,e)) = ILAM(id,(itran e))
	| itran (APP(e1,e2))= IAPP((itran e2), (itran e1));
	
(* give the function the index of the element and the list, and it gives back the element *)
fun find (1, l1::l2) = l1
|	find (_, []) = raise Fail "no index like that"
|	find (num : int, l1::l2) = find(num-1, l2);

(* 
	input - (1) the value of the element, (2) the list
	output is index of the element in that list 
	inverse of find
*)
fun find_int (_ : string, []) = 0
|	find_int (ch : string, l1::l2) = if ch<>l1 then 1+find_int(ch, l2) else 1;

(* 
	free variables function for de Bruijn notation
	usage - BfreeVars(term,0)
	note that you have to set the depth initially to 0
*)  
fun BfreeVars ((BID id2), depth: int)       = if id2>depth then [id2-depth] else []
  | BfreeVars ((BAPP(e1,e2)), depth: int)   = BfreeVars(e1, depth) @ BfreeVars(e2, depth)
  | BfreeVars ((BLAM e1), depth: int) = (BfreeVars (e1, depth+1));
  
fun IBfreeVars ((IBID id2), depth: int)       = if id2>depth then [id2-depth] else []
  | IBfreeVars ((IBAPP(e1,e2)), depth: int)   = IBfreeVars(e1, depth) @ IBfreeVars(e2, depth)
  | IBfreeVars ((IBLAM e1), depth: int) = (IBfreeVars (e1, depth+1));

(* 
	return the maximum element in an int list
	usage - requires setting max initially to 0
	ex: getMax(list,0)
*)  
fun getMax (l1::l2, max: int) = if l1>max then getMax(l2, l1) else getMax(l2, max)
|	getMax ([], max : int) = max;

val test = BLAM(BAPP(BAPP(BID 1,BID 3),BLAM(BAPP(BLAM(BID 4),BID 1))))
val test2 = (BLAM(BAPP(BAPP(BID 1, BLAM(BAPP(BID 2, BID 1))),BID 3)));
val test3 = BLAM(BAPP(BID 1, BID 2));
val test4 = BLAM (BAPP ( BAPP (BID 1, BLAM (BAPP(BID 2, BID 1))),  BID 3));

(* gives the first n elements of the list *)
fun First_N_Elems (0, l1::l2) = []
|	First_N_Elems (num : int, l1::l2) = [l1] @ First_N_Elems(num-1, l2)
|	First_N_Elems (_, []) = [];

(* 
	free variables function for normal lambda calculus syntax
	copied from the data-files.sml
*)
fun freeVars (ID id2)       = [id2]
  | freeVars (APP(e1,e2))   = freeVars e1 @ freeVars e2
  | freeVars (LAM(id2, e1)) = List.filter (fn x => not (x = id2)) (freeVars e1)
  
fun BnumberLam (BID m) = 0
|	BnumberLam (BAPP(e1,e2)) = (BnumberLam e1) + (BnumberLam e2)
|	BnumberLam (BLAM (e))= 1+ (BnumberLam e);

fun IBnumberLam (IBID m) = 0
|	IBnumberLam (IBAPP(e1,e2)) = (IBnumberLam e1) + (IBnumberLam e2)
|	IBnumberLam (IBLAM (e))= 1+ (IBnumberLam e);
  

(* 
	translates the list from alphabetic to numeric, according to their order in listorder list 
	* had to implement this because i couldn't get List.map to work.
*)  
fun translate_list(l1::l2) = find_int(l1, listorder)::translate_list(l2)
|	translate_list([]) = [];

(* w: M -> A

usage:

val n = getMax(translate_list(freeVars(term)),0)

omega(First_N_Elems(n,listorder),term);

* replace term if you want to copy and paste
*)

fun omega (stack : string list, ID id) = BID(find_int(id,stack))
	| omega (stack : string list, LAM(id,e)) = BLAM(omega(id::stack, e))
	| omega (stack : string list, APP(e1,e2)) = BAPP((omega(stack, e1)),(omega(stack,e2)));


(* w1: A -> M

usage:

val n = getMax(BfreeVars(term, 0),0)

omega1(n+1,First_N_Elems(n,listorder),term)

* replace term if you want to copy and paste
*)

fun omega1 (n : int, l, (BID id)) = (ID(find(id,l)))
	| omega1 (n : int, l, (BLAM e)) = let val x=find(n,listorder) in LAM(x,omega1(n+1,x::l,e)) end
	| omega1 (n : int, l, (BAPP(e1,e2)))= APP(omega1(n,l,e1),omega1(n+BnumberLam(e1),l,e2));
(*
use "C:\\Users\\daniel_laptop\\Downloads\\foundations 1\\cw_foundations.sml";
val _ = (printIEXP It9); print "\n";

w2: M -> A' 

usage:

val n = getMax(translate_list(freeVars(term)),0)

omega2(First_N_Elems(n,listorder),term);

* replace term if you want to copy and paste

*)


fun omega2 (stack : string list, ID id) = IBID(find_int(id, stack))
	| omega2 (stack : string list, LAM(id,e)) = IBLAM(omega2(id::stack, e))
	| omega2 (stack : string list, APP(e1,e2)) =  IBAPP((omega2(stack, e2)),(omega2(stack,e1)));
	
(* w3: A' -> M' 

usage:

val n = getMax(BfreeVars(term, 0),0)

omega3(n+1,First_N_Elems(n,listorder),term)

* replace term if you want to copy and paste

*)
fun omega3 (n : int, l, (IBID id)) = (IID(find(id,l)))
	| omega3 (n : int, l, (IBLAM e)) = let val x=find(n,listorder) in ILAM(x,omega3(n+1,x::l,e)) end
	| omega3 (n : int, l, (IBAPP(e1,e2)))= IAPP(omega3 (n, l, e1), omega3 (n+IBnumberLam(e1), l, e2));
	
(* V1: M' -> M 
	the most basic translation function
	just copies the data from one dataset to another.
*)
fun tran (IID id) = (ID id)
	| tran (ILAM(id,e)) = LAM(id,(tran e))
	| tran (IAPP(e1,e2))= APP((tran e2),(tran e1));	

	
(* Task 4 and 6 *)

(*
printBEXP Bvx;
printBEXP Bvy;
printBEXP Bvz;
printBEXP Bt1;
printBEXP Bt2;
printBEXP Bt3;
printBEXP Bt4;
printBEXP Bt5;
printBEXP Bt6;
printBEXP Bt7;
printBEXP Bt8;
printBEXP Bt9;

printIEXP Ivx;
printIEXP Ivy;
printIEXP Ivz;
printIEXP It1;
printIEXP It2;
printIEXP It3;
printIEXP It4;
printIEXP It5;
printIEXP It6;
printIEXP It7;
printIEXP It8;
printIEXP It9;

printIBEXP IBvx;
printIBEXP IBvy;
printIBEXP IBvz;
printIBEXP IBt1;
printIBEXP IBt2;
printIBEXP IBt3;
printIBEXP IBt4;
printIBEXP IBt5;
printIBEXP IBt6;
printIBEXP IBt7;
printIBEXP IBt8;
printIBEXP IBt9;

printIEXP(omega3(getMax(IBfreeVars(IBvx, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBvx, 0),0),listorder),IBvx));
printIEXP(omega3(getMax(IBfreeVars(IBvy, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBvy, 0),0),listorder),IBvy));
printIEXP(omega3(getMax(IBfreeVars(IBvz, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBvz, 0),0),listorder),IBvz));
printIEXP(omega3(getMax(IBfreeVars(IBt1, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBt1, 0),0),listorder),IBt1));
printIEXP(omega3(getMax(IBfreeVars(IBt2, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBt2, 0),0),listorder),IBt2));
printIEXP(omega3(getMax(IBfreeVars(IBt3, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBt3, 0),0),listorder),IBt3));
printIEXP(omega3(getMax(IBfreeVars(IBt4, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBt4, 0),0),listorder),IBt4));
printIEXP(omega3(getMax(IBfreeVars(IBt5, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBt5, 0),0),listorder),IBt5));
printIEXP(omega3(getMax(IBfreeVars(IBt6, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBt6, 0),0),listorder),IBt6));
printIEXP(omega3(getMax(IBfreeVars(IBt7, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBt7, 0),0),listorder),IBt7));
printIEXP(omega3(getMax(IBfreeVars(IBt8, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBt8, 0),0),listorder),IBt8));
printIEXP(omega3(getMax(IBfreeVars(IBt9, 0),0)+1,First_N_Elems(getMax(IBfreeVars(IBt9, 0),0),listorder),IBt9));

printLEXP(tran(Ivx));
printLEXP(tran(Ivy));
printLEXP(tran(Ivz));
printLEXP(tran(It1));
printLEXP(tran(It2));
printLEXP(tran(It3));
printLEXP(tran(It4));
printLEXP(tran(It5));
printLEXP(tran(It6));
printLEXP(tran(It7));
printLEXP(tran(It8));
printLEXP(tran(It9));

printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(vx)),0),listorder),vx));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(vy)),0),listorder),vy));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(vz)),0),listorder),vz));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(t1)),0),listorder),t1));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(t2)),0),listorder),t2));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(t3)),0),listorder),t3));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(t4)),0),listorder),t4));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(t5)),0),listorder),t5));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(t6)),0),listorder),t6));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(t7)),0),listorder),t7));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(t8)),0),listorder),t8));
printIBEXP(omega2(First_N_Elems(getMax(translate_list(freeVars(t9)),0),listorder),t9));

printLEXP(omega1(getMax(BfreeVars(Bvx, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bvx, 0),0),listorder),Bvx));
printLEXP(omega1(getMax(BfreeVars(Bvy, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bvy, 0),0),listorder),Bvy));
printLEXP(omega1(getMax(BfreeVars(Bvz, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bvz, 0),0),listorder),Bvz));
printLEXP(omega1(getMax(BfreeVars(Bt1, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bt1, 0),0),listorder),Bt1));
printLEXP(omega1(getMax(BfreeVars(Bt2, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bt2, 0),0),listorder),Bt2));
printLEXP(omega1(getMax(BfreeVars(Bt3, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bt3, 0),0),listorder),Bt3));
printLEXP(omega1(getMax(BfreeVars(Bt4, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bt4, 0),0),listorder),Bt4));
printLEXP(omega1(getMax(BfreeVars(Bt5, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bt5, 0),0),listorder),Bt5));
printLEXP(omega1(getMax(BfreeVars(Bt6, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bt6, 0),0),listorder),Bt6));
printLEXP(omega1(getMax(BfreeVars(Bt7, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bt7, 0),0),listorder),Bt7));
printLEXP(omega1(getMax(BfreeVars(Bt8, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bt8, 0),0),listorder),Bt8));
printLEXP(omega1(getMax(BfreeVars(Bt9, 0),0)+1,First_N_Elems(getMax(BfreeVars(Bt9, 0),0),listorder),Bt9));

printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(vx)),0),listorder),vx));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(vy)),0),listorder),vy));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(vz)),0),listorder),vz));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(t1)),0),listorder),t1));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(t2)),0),listorder),t2));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(t3)),0),listorder),t3));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(t4)),0),listorder),t4));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(t5)),0),listorder),t5));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(t6)),0),listorder),t6));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(t7)),0),listorder),t7));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(t8)),0),listorder),t8));
printBEXP(omega(First_N_Elems(getMax(translate_list(freeVars(t9)),0),listorder),t9));
*)
	
fun printLEXP (ID v) =
    print v
  | printLEXP (LAM (v,e)) =
    (print "(\\";
     print v;
     print ".";
     printLEXP e;
     print ")")
  | printLEXP (APP(e1,e2)) =
    (print "(";
     printLEXP e1;
     print " ";
     printLEXP e2;
     print ")");

fun printIEXP (IID v) =
    print v
  | printIEXP (ILAM (v,e)) =
    (print "[";
     print v;
     print "]";
     printIEXP e)
  | printIEXP (IAPP(e1,e2)) =
    (print "<";
     printIEXP e1;
     print ">";
     printIEXP e2);
	 
fun printBEXP (BID v) =
    print(Int.toString v)
  | printBEXP (BLAM e) =
    (print "(\\";
     printBEXP e;
     print ")")
  | printBEXP (BAPP(e1,e2)) =
    (print "(";
     printBEXP e1;
     print " ";
     printBEXP e2;
     print ")");
	 
fun printIBEXP (IBID v) =
    print(Int.toString v)
  | printIBEXP (IBLAM e) =
    (print "[]";
     printIBEXP e)
  | printIBEXP (IBAPP(e1,e2)) =
    (print "<";
     printIBEXP e1;
     print ">";
     printIBEXP e2);

(*************************************************** - Tasks 8, 9, 10 *)

fun free id1 (ID id2) = if (id1 = id2) then  true else false|
    free id1 (APP(e1,e2))= (free id1 e1) orelse (free id1 e2) | 
    free id1 (LAM(id2, e1)) = if (id1 = id2) then false else (free id1 e1);

(* finds new variables which are fresh  in l and different from id*)
    
fun findme id l = (let val id1 = id^"1"  in if not (List.exists (fn x => id1 = x) l) then id1 else (findme id1 l) end);

(*does substitution avoiding the capture of free variables*)

fun subs e id (ID id1) =  if id = id1 then e else (ID id1) |
    subs e id (APP(e1,e2)) = APP(subs e id e1, subs e id e2)|
    subs e id (LAM(id1,e1)) = (if id = id1 then LAM(id1,e1) else
                                   if (not (free id e1) orelse not (free id1 e))
				       then LAM(id1,subs e id e1)
                                   else (let val id2 = (findme id ([id1]@ (freeVars e) @ (freeVars e1)))
					 in LAM(id2, subs e id (subs (ID id2) id1 e1)) end));
					 
(*Finds a beta redex*)
fun is_redex (APP(LAM(_,_),_)) =
      true
  | is_redex _ =
      false;

fun is_var (ID id) =  true |
    is_var _ = false;


(*the function below adds lambda id to a list of terms *)
fun addlam id [] = [] |
    addlam id (e::l) = (LAM(id,e))::(addlam id l);

(*similar to above but adds app backward *)
fun addbackapp [] e2 = []|
    addbackapp (e1::l) e2 = (APP(e1,e2)):: (addbackapp l e2);

(*similar to above but adds app forward *)
fun addfrontapp e1 [] = []|
    addfrontapp e1  (e2::l) = (APP(e1,e2)):: (addfrontapp e1 l);

(*prints elements from a list putting an arrow in between*)
fun printlistreduce [] = ()|
    printlistreduce (e::[]) = (printLEXP e) |
    printlistreduce (e::l) = (printLEXP e; print "-->\n" ; (printlistreduce l));


(* 
Solution to task 8 and 9 - the counter

usage - count_reduce(rireduce(term)) or count_reduce(loreduce(term)) or count_reduce(countprintnewrireduce(term))
 *)
	
fun count_reduce [] = 0
|	count_reduce (e::[]) = 0
|	count_reduce (e::l) = 1+count_reduce(l);

fun debuglist n l = (print n; print ": "; printlistreduce l; print "\n");

(*beta-reduces a redex*)

fun red (APP(LAM(id,e1),e2)) = subs e2 id e1;

fun has_redex (ID id) = false |
    has_redex (LAM(id,e)) = has_redex e|
    has_redex (APP(e1,e2)) = if (is_redex  (APP(e1,e2))) then true else
                              ((has_redex e1) orelse (has_redex e2));

fun one_rireduce (ID id) = (ID id)|
    one_rireduce (LAM(id,e)) = LAM(id, (one_rireduce e))|
    one_rireduce (APP(e1,e2)) = if (has_redex e2) then (APP(e1, (one_rireduce e2))) else
                                if (is_redex (APP(e1,e2))) then (red (APP(e1,e2))) else
                                          if (has_redex e1) then (APP((one_rireduce e1), e2)) else
                                              (APP(e1,e2));



fun rireduce (ID id) =  [(ID id)] |
    rireduce (LAM(id,e)) = (addlam id (rireduce e)) |
    rireduce (APP(e1,e2)) = (let val l1 = (rireduce e2)
				val e3 = (List.last l1)
                                val l2 = (addfrontapp e1 l1)
				val e4 = (APP(e1,e3))
                                val l3 =  if (is_redex e4) then (rireduce (red e4)) else 
					  if is_var(e1) then [e4] else
					      (rireduce (APP(one_rireduce e1, e3)))
			    in l2 @ l3
                            end);


(* task 9 duplicates solution *)
fun countprintnewrireduce (ID id) =  [(ID id)] |
    countprintnewrireduce (LAM(id,e)) = (addlam id (countprintnewrireduce e)) |
    countprintnewrireduce (APP(e1,e2)) = (
let 
	val l1 = (countprintnewrireduce e2)
	val e3 = (List.last l1)
    val l2 = (addfrontapp e1 l1)
	val e4 = (APP(e1,e3))
    val l3 =  if (is_redex e4) then (countprintnewrireduce (red e4)) else if has_redex(e1) 
														then (countprintnewrireduce (APP(one_rireduce e1, e3))) 
														else []
in l2 @ l3
end);


fun printrireduce e = (let val tmp =  (rireduce e)
		      in printlistreduce tmp end);

fun one_loreduce (ID id) = (ID id)|
    one_loreduce (LAM(id,e)) = LAM(id, (one_loreduce e))|
    one_loreduce (APP(e1,e2)) = if (is_redex (APP(e1,e2))) then (red (APP(e1,e2))) else
                                 if (has_redex e1) then APP(one_loreduce e1, e2) else
                                 if (has_redex e2) then APP(e1, (one_loreduce e2)) else (APP(e1,e2));


fun loreduce (ID id) =  [(ID id)] |
    loreduce (LAM(id,e)) = (addlam id (loreduce e)) |
    loreduce (APP(e1,e2)) = (let val l1 = if (is_redex (APP(e1,e2))) then  (loreduce (red (APP(e1,e2)))) else 
				 if (has_redex e1) then (loreduce (APP(one_loreduce e1, e2))) else 
				 if (has_redex e2) then  (loreduce (APP(e1, (one_loreduce e2)))) else []
				 in [APP(e1,e2)]@l1
			      end);


fun printloreduce e = (let val tmp =  (loreduce e)
		      in printlistreduce tmp end);

			  
(*
count_reduce(rireduce(vx));
count_reduce(rireduce(vy));
count_reduce(rireduce(vz));
count_reduce(rireduce(t1));
count_reduce(rireduce(t2));
count_reduce(rireduce(t3));
count_reduce(rireduce(t4));
count_reduce(rireduce(t5));
count_reduce(rireduce(t6));
count_reduce(rireduce(t7));
count_reduce(rireduce(t8));
count_reduce(rireduce(t9));

count_reduce(loreduce(vx));
count_reduce(loreduce(vy));
count_reduce(loreduce(vz));
count_reduce(loreduce(t1));
count_reduce(loreduce(t2));
count_reduce(loreduce(t3));
count_reduce(loreduce(t4));
count_reduce(loreduce(t5));
count_reduce(loreduce(t6));
count_reduce(loreduce(t7));
count_reduce(loreduce(t8));
count_reduce(loreduce(t9));

count_reduce(countprintnewrireduce(vx));
count_reduce(countprintnewrireduce(vy));
count_reduce(countprintnewrireduce(vz));
count_reduce(countprintnewrireduce(t1));
count_reduce(countprintnewrireduce(t2));
count_reduce(countprintnewrireduce(t3));
count_reduce(countprintnewrireduce(t4));
count_reduce(countprintnewrireduce(t5));
count_reduce(countprintnewrireduce(t6));
count_reduce(countprintnewrireduce(t7));
count_reduce(countprintnewrireduce(t8));
count_reduce(countprintnewrireduce(t9));

val x = APP(LAM("x",APP(APP(APP(ID "x",ID "x"),ID "x"),ID "x")), APP(LAM("x",ID "x"),ID "x"));

*)