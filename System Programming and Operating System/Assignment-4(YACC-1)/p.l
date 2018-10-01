%{
    extern int yyerror(char * str);
    #include "y.tab.h" 
%}

%%

"int" {return BUILTIN; }
","   {return COMMA; }
";"   {return SC;}
"\n"  {return NL;}

[a-zA-Z]+[a-zA-Z0-9]*  {return ID;}

%%
extern int yywrap() {
return 1;
}
