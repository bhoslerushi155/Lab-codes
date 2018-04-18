%{

#include<stdio.h>
#include "y.tab.h"

%}

%%
"int"  {return BLT;}
(^[a-zA-Z][a-zA-Z0-9]*) {return ID;}
","    {return CM;}
";"    {return SC;}
"\n"    {return NL;}
.      {}

%%

int yywrap(){
    return 1;
}
