%{
#include "y.tab.h"
extern int yyerror(char *str);
extern int yyparse();
%}

// rules in the form of regular expressions
%%
"while" return WH;
"do" return DO;
"if" return IF;
"for" return FOR;
"(" return OP;
")" return CP;
"{" return OCB;
"}" return CCB;
"<" |
">" |
"<=" |
">=" |
"==" |
"!=" return CMP;
"+" |
"-" |
"*" |
"/" return OPR;
"=" return ASG;
"++" return INCR;
"--" return DECR;
([a-zA-Z])("_"|[a-zA-Z0-9])* return ID;
[0-9]+ return NUM;
";" return SC;
"," return COMMA;
" " {}
%%

int yywrap()
{
    return 1;
}

