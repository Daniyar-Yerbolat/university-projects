%{
#include "tokens.h"
%}

DIGIT    [0-9]
IDENT	[a-zA-Z][A-Za-z0-9]*

%%

";" 		{ return SEMI;}
":=" 		{ return ASSIGN;}
"if"		{ return IF;}
"endif" 	{ return ENDIF;}
"then"		{ return THEN;}
"else"		{ return ELSE;}
"begin"		{ return TBEGIN;}
"while" 	{ return TWHILE;}
"endwhile" 	{ return ENDWHILE;}
"(" 		{ return LBRA;}
","		{ return COMMA;}
")" 		{ return RBRA;}
"vars"		{ return VARS;}
"method" 	{ return METHOD;}
"endmethod" 	{ return ENDMETHOD;}
"return" 	{ return TRETURN;}
"read"		{ return READ;}
"write"		{ return WRITE;}


"less" 		{return LESS;}
"lessEq" 	{return LESSEQ;}
"eq" 		{return EQ;}
"nEq" 		{return NEQ;}
"plus" 		{return PLUS;}
"minus" 	{return MINUS;}
"divide" 	{return DIVIDE;}
"times"		{return TIMES;}

"main" 		{return TMAIN;}


{DIGIT}+	{ return INT;}
{IDENT}		{ return ID;}
<<EOF>>		{ return EOF;}

[ \t\n]+          /* eat up whitespace */



%%

int yywrap() { return EOF; }
