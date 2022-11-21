%{
#include "y.tab.h"
void yyerror(char *error);
%}

%%
[0-9]+ {yylval.intval=atoi(yytext); return NUMBER; }
"sin" (return SIN;)
"cos" (return COS;)
"tan" (return TAN;)
[a-z] {(yylval.fchar = *yytext - 'a'); return NAME;}
[\t ];      // do nothing
\n return 0;
. {return yytext[0];}       // if none of the tokens match
%%

int yywrap()
{
    return 1;
}

