%{
#include<stdio.h>
extern int yylex();
extern int yywrap();
extern int yyparse();
extern int yyerror(char* s);
%}
%token WH IF DO FOR OP CP OCB CCB CMP SC ASG ID NUM COMMA OPR
%%
start: swh | mwh
swh: WH OP cmpn CP stmt { printf("VALID SINGLE STATEMENT WHILE LOOP\n"); };
cmpn: ID CMP ID | ID CMP NUM;
stmt: ID ASG ID OPR ID SC | ID ASG ID OPR NUM SC | ID ASG NUM OPR ID SC | ID
ASG NUM OPR NUM SC | ID ASG ID SC | ID ASG NUM SC | start { printf("NESTED INSIDE A "); };
/* a = b + c; | a = b + 3; | a = 2 + b; | a
= 3 + 4; | a = b; | a = 3; |*/
mwh: WH OP cmpn CP OCB stmtlist CCB { printf("VALID MULTI STATEMENT WHILE LOOP\n"); };
stmtlist: stmt stmtlist | stmt;
%%
int yyerror(char *str)
{
printf("%s", str);
}
main()
{
yyparse();
}
