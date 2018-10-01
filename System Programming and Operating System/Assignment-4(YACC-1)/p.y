%{

#include<stdio.h>
extern int yylex();
extern int yywrap();
%}

%token ID BUILTIN SC COMMA NL

%%

start: BUILTIN varlist SC NL {printf("\nValid\n");}
varlist:varlist COMMA ID {printf("varlist\t");}
| ID {printf("ID\t");}
%%
int yyerror(char *str ) {printf("Error\n");}

int main(){

yyparse();
}

