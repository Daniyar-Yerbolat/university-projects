#include <stdio.h>
#include <stdlib.h>
#include "tokens.h"

extern int symb;
extern char * yytext;

char * showSymb(int symb)
{  switch(symb){
	case SEMI: return ";";
	case ASSIGN: return ":=";
	case IF: return "if";
	case ENDIF: return "endif";
	case THEN: return "then";
	case ELSE: return "else";
	case INT: return "INT";
	case ID: return "ID";
	case TBEGIN: return "begin";
	case TWHILE: return "while";
	case ENDWHILE: return "endwhile";
	case COMMA: return ",";
	case LBRA: return "(";
	case RBRA: return ")";
	case VARS: return "vars";
	case METHOD: return "method";
	case ENDMETHOD: return "endmethod";
	case TRETURN: return "return";
	case READ: return "read";
	case WRITE: return "write";
	case LESS: return "less";
	case LESSEQ: return "lessEq";
	case EQ: return "eq";
	case NEQ: return "nEq";
	case PLUS: return "plus";
	case MINUS: return "minus";
	case DIVIDE: return "divide";
	case TIMES: return "times";
	case TMAIN: return "main";
	case EOF: return "EOF";
      default: printf("bad symbol: %d",symb);
   }
}

void printSymb()
{  char * s;
   printf("%s ",showSymb(symb));
   if(symb==ID || symb==INT){
    if(yytext == NULL)
      printf("Error: yytext is null");
    else
      printf("%s\n",yytext);
   } else
    printf("\n");
}
