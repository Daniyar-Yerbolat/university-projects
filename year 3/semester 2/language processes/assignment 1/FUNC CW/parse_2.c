#include <stdio.h>
#include <stdlib.h>

#include "tokens.h"
#include "nodes.h"

#include <stdbool.h>

int symb;
bool main_exists = false;

extern printSymb();
extern char* showSymb(int);
extern int yylex(void);
extern FILE* yyin;

extern void prettytree(NODE*,int);
extern char * yytext;

lex()
{  
  // printSymb();
   symb = yylex();
}

NODE * newInt(int v)
{  NODE * n;
   n = (NODE *)malloc(sizeof(NODE));
   n->tag = INT;
   n->f.value = v;
   return n;
}


NODE * newId(char * i)
{  NODE * n;
   char * cur = strdup(i);
   n = (NODE *)malloc(sizeof(NODE));
   n->tag = ID;
   n->f.id = cur;
   return n;
}

NODE * newNode(int tag)
{  NODE * n;
   n = (NODE *)malloc(sizeof(NODE));
   n->tag = tag;
   n->f.b.n1 = NULL;
   n->f.b.n2 = NULL;
   return n;
}

showTree(NODE * tree,int depth)
{  int i;
   if(tree==NULL)
    return;
   for(i=0;i<depth;i++)
    putchar('-');
   switch(tree->tag)
   {  case ID: printf("%s\n",tree->f.id);
               return;
      case INT: printf("%d\n",tree->f.value);
               return;
      default: printf("%s\n",showSymb(tree->tag));
               showTree(tree->f.b.n1,depth+1);
               showTree(tree->f.b.n2,depth+1);
   }
}

error(char * rule,char * message)
{  printf("%s: found %s\n",rule,showSymb(symb));
   printf("%s: %s\n",rule,message);
   exit(0);
}



NODE * program()
{  extern NODE * methods();
   NODE * p;
   p = newNode(SEMI);
   p->f.b.n1 = methods();
   return p;
}

NODE * methods()
{  extern NODE * method();
   NODE * m = newNode(SEMI);
   m = method();
	
if(symb==METHOD){
	NODE * m1;
        m1 = m;
        m = newNode(SEMI);
        m->f.b.n1 = m1;
        m->f.b.n2 = methods();
}
   return m;
}


NODE * method()
{
extern NODE * other_functions( NODE *);
extern NODE * main_function( NODE *);
   NODE * m = newNode(METHOD);
	
      	if(symb==METHOD){
		lex();
	} else{error("method","method expected");}

	if(symb == ID){
		//other functions
		m->f.b.n1 = newId(yytext);	
		m = other_functions(m);
	} else if(symb == TMAIN){
		//main function
		m->f.b.n1 = newId(yytext);	
		m = main_function(m);
		main_exists = true;
	} else {error("method","id expected");}
   return m;
}


NODE * other_functions(NODE * node){
	extern NODE * args(int);
	extern NODE * statements();

	lex();

	if(symb == LBRA){
		lex();
	} else {error("method","( expected");}
	
	
	if(symb == ID){
		NODE * m1;
		m1 = node;
		node = newNode(SEMI);
		node->f.b.n1 = m1;
		node->f.b.n2 = args(ID);
	}
	
	if(symb == RBRA){
		lex();
	} else {error("method",") expected");}

	if(symb == VARS){
		lex();
		NODE * m1;
		m1 = node;
		node = newNode(SEMI);
		node->f.b.n1 = m1;
		node->f.b.n2 = args(VARS);
	}
	
	if(symb == TBEGIN){
		lex();
	} else {error("method","begin expected");}

	NODE * t = statements();
	

	if(symb == TRETURN){
		NODE * a = newNode(SEMI);
		NODE * r = newNode(TRETURN);
		lex();
			if(symb == ID){
				r->f.b.n1 = newId(yytext);
				lex();
			
				if(symb == COMMA){
				lex();
					if(symb == ID){
						r->f.b.n2 = newId(yytext);
						lex();
					}else{error("return","id expected");}
				}
				
			}else{error("return","id expected");}
		if(symb == SEMI){
			lex();
		} else{error("method","; expected");}
		a->f.b.n1 = r;
		a->f.b.n2 = t;
		node->f.b.n2 = a;
	} else {
	NODE * m2;
	m2 = node;
	node = newNode(SEMI);
	node->f.b.n1 = m2;
	node->f.b.n2 = node->f.b.n2 = t;		
	}

	if(symb == ENDMETHOD){
		lex();
	} else {error("method","endmethod expected");}

	return node;
}

NODE * main_function(NODE * node){
	extern NODE * args(int);
	extern NODE * statements();

	lex();
	
	if(symb == LBRA){
		lex();
	} else {error("method","( expected");}
	
	if(symb == RBRA){
		lex();
	} else {error("method",") expected");}

	if(symb == VARS){
		lex();
		NODE * m1;
		m1 = node;
		node = newNode(SEMI);
		node->f.b.n1 = m1;
		node->f.b.n2 = args(VARS);
	}
	
	if(symb == TBEGIN){
		lex();
	} else {error("method","begin expected");}
	

	NODE * m2;
	m2 = node;
	node = newNode(SEMI);
	node->f.b.n1 = m2;
	node->f.b.n2 = statements();

	if(symb == ENDMETHOD){
		lex();
	} else {error("method","endmethod expected");}

	return node;
}

NODE * args(int arg_or_var){
   	NODE * p;

	if(arg_or_var == ID){
		p = newNode(SEMI);
	} else if(arg_or_var == VARS){
		p = newNode(VARS);
	}


	if(symb == ID){
		p->f.b.n1 = newId(yytext);
		lex();
	} else {error("args","id expected");}


	if(symb == COMMA){
		lex();
		NODE * p1;
		p1 = p;
		p = newNode(SEMI);
		p->f.b.n1 = p1;
		p->f.b.n2 = args(arg_or_var);
	}
	return p;
}

NODE * statements(){
	extern NODE * statement();
	NODE * s = newNode(SEMI);
	s = statement();
		
		// while has no ; at the end
		if(symb==SEMI){lex();}

		if(symb==ID || symb == READ || symb == WRITE || symb==IF || symb==TWHILE ){
			NODE * s1;
			s1 = s;
			s = newNode(SEMI);
			s->f.b.n1 = s1;
			s->f.b.n2 = statements();
		}
	return s;
}

NODE * statement(){
	extern NODE * assign();
	extern NODE * s_if();
	extern NODE * s_while();
	extern NODE * rw();
	
	switch(symb){
		case ID:
			return assign();
			break;
		case IF:
			return s_if(); 
			break;
		case TWHILE: 
			return s_while();
			break;
		case READ:
			return rw();
			break;
		case WRITE:
			return rw();
			break;		
		default: 
			error("statement","not valid");
	}
}

NODE * assign(){
	extern NODE * exp();
	NODE * a = newNode(ASSIGN);
	
	if(symb==ID){
		a->f.b.n1 = newId(yytext);
		lex();
	} else {error("assign","id expected");}

	if(symb==ASSIGN){
		lex();
	} else {error("assign",":= expected");}

	a->f.b.n2 = exp();
	
	return a;
}

//parameters
NODE * exps(){
	extern NODE * exp();
	NODE * e = newNode(SEMI);
	e = exp();	
   	if(symb==COMMA){
		lex();
		NODE * e1;
		e1 = e;
		e = newNode(SEMI);
		e->f.b.n1 = e1;
		e->f.b.n2 = exps();
	}

	return e;
}

NODE * exp(){
	extern NODE * exps();
	extern NODE * exps_2parameters();
	NODE * e = newNode(SEMI);

	if(symb==ID){
		e->f.b.n1 = newId(yytext);
		lex();
		if(symb==LBRA){
			lex();
				if(symb==ID || symb==INT){
					e->f.b.n2 = exps();
				}
			if(symb==RBRA){lex();} else {error("expression",") expected");}		
		}
		
	} else if (symb==PLUS || symb==MINUS || symb==TIMES || symb==DIVIDE){
		e->f.b.n1 = newId(yytext);
		lex();

		if(symb==LBRA){lex();} else {error("expression","( expected");}			

		e->f.b.n2 = exps_2parameters();

		if(symb==RBRA){lex();} else {error("expression",") expected");}			
	} else if (symb==INT){
		e->f.b.n1 = newInt(atoi(yytext));
		lex();
	}

	return e;
}

NODE * s_if(){
	extern NODE * cond();
	extern NODE * statements();
	NODE * i;
	NODE * t;
	i = newNode(IF);

	if(symb==IF){
		lex();
	} else {error("if","if expected");}

	i->f.b.n1 = cond();

	if(symb==THEN){
		lex();
	} else {error("if","then expected");}

	t = statements();


	if(symb==ELSE){
		lex();
		i->f.b.n2 = newNode(ELSE);
		i->f.b.n2->f.b.n1 = t;
	      	i->f.b.n2->f.b.n2 = statements();
	} else {
	    i->f.b.n2 = t;
	}

	if(symb==ENDIF){
		lex();
	} else {error("if","endif expected");}

	return i;

}

NODE * cond(){
	extern NODE * bop();
	extern NODE * exps();
	extern NODE * exps_2parameters();

	NODE * c = newNode(SEMI);
	
	c->f.b.n1 = bop();

	if(symb == LBRA){
		lex();
	} else {error("cond","( expected");}

	c->f.b.n2 = exps_2parameters();

	if(symb == RBRA){
		lex();
	} else {error("cond",") expected");}

	return c;
}

NODE * bop(){
	switch(symb){
		case LESS:
			lex();
			return newNode(LESS);
			break;
		case LESSEQ:
			lex();
			return newNode(LESSEQ);
			break;
		case EQ:
			lex();
			return newNode(EQ);
			break;
		case NEQ:
			lex();
			return newNode(NEQ);
			break;		
		default: 
			error("boolean operation","less, lessEq, eq or nEq expected");
	}
}

NODE * exps_2parameters(){
	extern NODE * exp();
	NODE * e = newNode(SEMI);

	e->f.b.n1 = exp();
	
   	if(symb==COMMA){
		lex();
	} else {error("exprs","2 parameters expected");}
	
	e->f.b.n2 = exp();
	
	return e;
}


NODE * s_while(){
	extern NODE * cond();
	extern NODE * statements();
	NODE * w = newNode(TWHILE);

	if(symb==TWHILE){
		lex();
	} else {error("while","while expected");}

	w->f.b.n1 = cond();

	if(symb==TBEGIN){
		lex();
	} else {error("while","begin expected");}

      	w->f.b.n2 = statements();

	if(symb==ENDWHILE){
		lex();
	} else {error("while","endwhile expected");}

	return w;
}


NODE * rw(){
	extern NODE * exp();
	NODE * rw;
	if(symb==READ){
		rw = newNode(READ);
		lex();
		if(symb==ID){
			rw->f.b.n1 = newId(yytext);
			lex();
		} else {error("read","id expected");}
	} else if (symb==WRITE){
		rw = newNode(WRITE);
		lex();
		rw->f.b.n1 = exp();
	} else {error("rw","read or write expected");}

	return rw;
}



main(int c,char ** argv)
{  
   if((yyin=fopen(argv[1],"r"))==NULL){  
      printf("can't open %s\n",argv[1]);
      exit(0);
   }
   symb = yylex();
   NODE* ast = program();
   showTree(ast,1);

	if(main_exists == false){
		error("main","main method expected");
	}
   fclose(yyin);
}
