%{


%}

digit [0-9]+
letter [a-zA-Z]+
float [0-9]+[.][0-9]+
id ({letter}({letter}|{digit})*)
space [' ']
keyword (class|static|return|out|if|while|for|else|void)
dataType (int|float|string|char)
arith_op (\+|\-|\*|\/|\%)
logical_op (\&\&|\|\||\=\=)
package (import{space}java(\.{id})*(\.\*)?)
accessSpecifier (public|private|protected)
array_name ({id}\[\])
user_def_classname (class{space}{id})
user_def_func_call ({id}\((.*)\))
predef_classname (System|String|Integer)
predef_func (main|println)
expression (({id}|{digit}){space}*({arith_op}|{logical_op}){space}*({id}|{digit}))
assignment_stat ({id}{space}*\={space}*({digit}|{id}|{expression}))
literal (\"(.*)\")
end (;)
skip ({end}|\(|\)|\{|\}|\;|\.)


%%

[\t ]+

{keyword} { printf("%s : is a keyword\n", yytext); }

{skip} { printf(" \n", yytext); }

{dataType} { printf("%s : is a data type\n", yytext); }

{digit} { printf("%s : is a number\n", yytext); }

{package} { printf("%s : is a package\n", yytext); }

{accessSpecifier} { printf("%s : is an access specifier\n", yytext); }

{array_name} { printf("%s : is an array name\n", yytext); }

{literal} { printf("%s : is a literal\n", yytext); }

{predef_classname} { printf("%s : is a pre defined classname\n", yytext); }

{user_def_classname} { printf("%s : is a user defined classname\n", yytext); }

{predef_func} { printf("%s : is a pre defined function\n", yytext); }

{user_def_func_call} { printf("%s : is a user defined function\n", yytext); }

{arith_op} { printf("%s : is a arithmetic operator\n", yytext); }


{logical_op} { printf("%s : is a logical operator\n", yytext); }

{expression} { printf("%s : is an expression\n", yytext); }

{assignment_stat} { printf("%s : is an assignment statment\n", yytext); }

{id} { printf("%s : is an identifier\n", yytext); }


.|\n {ECHO;}

%%

main()
{
	yyin=fopen("Lab5_java.txt","r");
	yylex();
	yywrap();
}

int yywrap(void)
{
	return 1;
}
