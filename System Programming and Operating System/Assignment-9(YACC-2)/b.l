%{
#include "y.tab.h"
extern int yyerror(char * e);
extern int yylval;
%}
%%
"while"  {return WHILE;}
"do"     {return DO;}
[0-9]+   {return NUM;}
[=]      {return eq;} 
"=="     {return eq1;}
[><]     {return lt;}
[+*-/]   {return o;}
[a-zA-Z]* {return ID; } 
[(]      {return op;}
[)]      {return cl;}
%%

extern int yywrap(){
 return(1);
}
