%{

%}

digit [0-9]
letter [a-zA-Z]
space [' ' ]
package (import{space}java(\.{id})*(\.\*)?)
access_specifier (public|private|protected)
keywords (class|static|throws|try|catch)
datatypes (int|float|double|char|boolean|void)
user_defined_classname (class{space}{id})
predefined_classnames (Scanner|BufferedReader|String)
predefined_functions ({access_specifier}{space}static{space}{datatypes}{id}(.*))
operators (\+|-|\*|\\)
function (({id}\(\)\;))
array_names ({id}\[\])
loops ((for|while|do){space}*\(\))
conditional_statements ((if|else|else{space}if)\(.*\))
string_constant ("{({letter}|{digit}|{space})*")
expression (({id}|digit+){operators}({id}|digit+))
assignment_statement ({id}=({id}|digit+))
increment ("++")
decrement ("--")
post_increment ({id}{increment})
pre_increment ({increment}{id})
post_decrement ({id}{decrement})
pre_decrement ({decrement}{id})
id ({letter}({letter}|{digit})*)
%%

[\t]		/* ignore whitespace*/

{package} { printf("%s: is a package\n",yytext); }
{function} { printf("%s: is a function\n",yytext); }
{access_specifier} {printf("%s: is an access specifier\n",yytext);}
{keywords} {printf("%s: is a Keyword\n",yytext);}
{datatypes} {printf("%s: is a Datatype\n",yytext);}
{user_defined_classname} {printf("%s: is a User Defined Classname\n",yytext);}
{predefined_classnames} {printf("%s: is a Pre-Defined Classname\n",yytext);}
{predefined_functions} {printf("%s: is a Function\n",yytext);}
{operators} {printf("%s: is an operator\n", yytext);}
{array_names} {printf("%s: is an array name\n", yytext);}
{loops} {printf("%s: is a loop statement\n", yytext);}
{string_constant} {printf("%s: is a string constant\n", yytext);}
{conditional_statements} {printf("%s: is a conditional statement\n", yytext);}
{assignment_statement} {printf("%s: is an assignment statement\n", yytext);}
{post_increment} {printf("%s: is an post increment statement\n", yytext);}
{pre_increment} {printf("%s: is an pre increment statement\n", yytext);}
{post_decrement} {printf("%s: is an post decrement statement\n", yytext);}
{pre_decrement} {printf("%s: is an pre decrement statement\n", yytext);}


.|\n { ; /*normal default anyway*/ }
%%

int main()
{
	yyin = fopen("sampleinput.txt", "r");
	yylex();
	yywrap();
}

int yywrap(void)
{
	return 1;
}
