%{

#include<stdio.h>
#include "y.tab.h"


%}

%%
"while"  {return WHILE;}
[{]     {return OPCL;}
[}]     {return CLCL;}
[;]     {return SC;}
[0-9]+   {return NUM;}
[=]      {return EQ;}
"=="     {return EQQ;}
[><]     {return LT;}
[+*-/]   {return O;}
[a-zA-Z]* {return ID; }
[(]      {return OP;}
[)]      {return CL;}
"\n"     {return NL;}
%%




int yywrap(){
    return 1;
}
