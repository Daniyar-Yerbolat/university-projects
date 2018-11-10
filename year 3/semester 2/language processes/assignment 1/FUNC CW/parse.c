#include <stdio.h>
#include <stdlib.h>

#include "tokens.h"

#include <stdbool.h>

int symb;
bool main_exists = false;

extern printSymb();
extern char* showSymb(int);
extern int yylex(void);
extern FILE* yyin;

rule(char * name,int depth)
{  int i;
   for(i=0;i<depth;i++)
    printf(" ");
   printf("%s\n",name);
}

error(char * rule,char * message)
{  printf("%s: found %s\n",rule,showSymb(symb));
   printf("%s: %s\n",rule,message);
   exit(0);
}

lex()
{  printSymb();
   symb = yylex();
}

program(int depth)
{  rule("program",depth);
   methods(depth+1);
}

methods(int depth){
	rule("methods", depth);
	method(depth+1);
	      	if(symb==METHOD){
	       		methods(depth+1);
		}
}

method(int depth){
	rule("method", depth);

      	if(symb==METHOD){
		lex();
	} else{error("method","method expected");}
	
	if(symb == ID){
		lex();
		//other functions
		other_functions(depth);
	} else if(symb == TMAIN){
		lex();
		//main function
		main_function(depth);
		main_exists = true;
	} else {error("method","id expected");}
}

other_functions(int depth){

	if(symb == LBRA){
		lex();
	} else {error("method","( expected");}
	
	
	if(symb == ID){
		args(depth+1);
	}
	
	if(symb == RBRA){
		lex();
	} else {error("method",") expected");}

	if(symb == VARS){
		lex();
		args(depth+1);
	}
	
	if(symb == TBEGIN){
		lex();
	} else {error("method","begin expected");}

	statements(depth+1);
	

	if(symb == TRETURN){
		lex();
			if(symb == ID){
				lex();
			
				if(symb == COMMA){
				lex();
					if(symb == ID){
						lex();
					}else{error("return","id expected");}
				}
				
			}else{error("return","id expected");}
		if(symb == SEMI){
			lex();
		} else{error("method","; expected");}
	}

	if(symb == ENDMETHOD){
		lex();
	} else {error("method","endmethod expected");}
}


main_function(int depth){
	
	if(symb == LBRA){
		lex();
	} else {error("method","( expected");}
	
	if(symb == RBRA){
		lex();
	} else {error("method",") expected");}

	if(symb == VARS){
		lex();
		args(depth+1);
	}
	
	if(symb == TBEGIN){
		lex();
	} else {error("method","begin expected");}

	statements(depth+1);

	if(symb == ENDMETHOD){
		lex();
	} else {error("method","endmethod expected");}
}

args(int depth){
	rule("args", depth);

	if(symb == ID){
		lex();
	} else {error("args","id expected");}

	if(symb == COMMA){
		lex();
		args(depth+1);
	}
}

statements(int depth){
	rule("statements", depth);
	statement(depth+1);
		
		// while has no ; at the end
		if(symb==SEMI){lex();}

		if(symb==ID || symb == READ || symb == WRITE || symb==IF || symb==TWHILE ){
	       		statements(depth+1);}
}

statement(int depth){
	rule("statement", depth);
	
	switch(symb){
		case ID:
			assign(depth+1);
			break;
		case IF:
			s_if(depth+1); 
			break;
		case TWHILE: 
			s_while(depth+1);
			break;
		case READ:
			rw(depth+1);
			break;
		case WRITE:
			rw(depth+1);
			break;		
		default: 
			error("statement","not valid");
	}
}

assign(int depth){
	rule("assign", depth);
	
	if(symb==ID){
		lex();
	} else {error("assign","id expected");}

	if(symb==ASSIGN){
		lex();
	} else {error("assign",":= expected");}

	exp(depth+1);
}

exps(int depth){
	rule("expressions", depth);
	exp(depth+1);
   	if(symb==COMMA){
		lex();
	      	exps(depth+1);
	}
}

exp(int depth){
	rule("expression", depth);
	
	if(symb==ID){
		lex();
		if(symb==LBRA){
			lex();
				if(symb==ID || symb==INT){
					exps(depth+1);
				}
			if(symb==RBRA){lex();} else {error("expression",") expected");}		
		}
		
	} else if (symb==PLUS || symb==MINUS || symb==TIMES || symb==DIVIDE){
		lex();

		if(symb==LBRA){lex();} else {error("expression","( expected");}			

		exps_2parameters(depth+1);

		if(symb==RBRA){lex();} else {error("expression",") expected");}			
	} else if (symb==INT){
		lex();
	}
}



s_if(int depth){
	rule("if", depth);

	if(symb==IF){
		lex();
	} else {error("if","if expected");}

	cond(depth+1);

	if(symb==THEN){
		lex();
	} else {error("if","then expected");}

	statements(depth+1);

	if(symb==ELSE){
		lex();
		statements(depth+1);
	}

	if(symb==ENDIF){
		lex();
	} else {error("if","endif expected");}

}

cond(int depth){
	rule("condition", depth);
	bop(depth+1);

	if(symb == LBRA){
		lex();
	} else {error("cond","( expected");}

	exps_2parameters(depth+1);

	if(symb == RBRA){
		lex();
	} else {error("cond",") expected");}
}

bop(int depth){
	rule("boolean operation", depth);
	if(symb== LESS || symb== LESSEQ || symb== EQ || symb== NEQ){
		lex();
	}else{error("boolean operation","less, lessEq, eq or nEq expected");}
}

exps_2parameters(int depth){
	rule("expressions", depth);

	exp(depth+1);
	
   	if(symb==COMMA){
		lex();
	} else {error("exprs","2 parameters expected");}
	
	exp(depth+1);
}


s_while(int depth){
	rule("while", depth);

	if(symb==TWHILE){
		lex();
	} else {error("while","while expected");}

	cond(depth+1);

	if(symb==TBEGIN){
		lex();
	} else {error("while","begin expected");}

      	statements(depth+1);

	if(symb==ENDWHILE){
		lex();
	} else {error("while","endwhile expected");}
}

rw(int depth){
	rule("rw", depth);
	if(symb==READ){
		lex();
		if(symb==ID){
			lex();
		} else {error("read","id expected");}
	} else if (symb==WRITE){
		lex();
		exp(depth+1);
	} else {error("rw","read or write expected");}
}



main(int c,char ** argv)
{  
   if((yyin=fopen(argv[1],"r"))==NULL){  
      printf("can't open %s\n",argv[1]);
      exit(0);
   }
   symb = yylex();
   program(1);

	if(main_exists == false){
		error("main","main method expected");
	}
   fclose(yyin);
}
