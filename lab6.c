#include<stdio.h>
#include<stdlib.h>
void E();
int i = 0;
char str[20],tp;
void advance()
{
i++;
tp=str[i];
}
void F()
{
if(tp=='a')
{
advance();
}
else
{
if(tp=='(')
{
advance();
E();
if(tp==')')
{
advance();
}
else
{
printf("String is not accepted\n");
exit(1);
}
}
else {
printf("\nString is not accepted");
exit(1);
}
}
}
void TP()
{
if(tp=='*')
{
advance();

F();
TP();
}
}
void T()
{
F();
TP();
}
void EP()
{
if(tp=='+')
{
advance();
T();
EP();
}
}

void E()
{
T();
EP();
}
int main()
{
printf("Enter the string: ");
scanf("%s", &str[i]);
tp = str[i];
E();
if(tp=='\0')
{
printf("\nString is accepted");
}
else
{
printf("\nString is not accepted");
}
exit(0);
}