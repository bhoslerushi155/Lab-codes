%{

#include<stdio.h>
#include<stdlib.h>

int yylex();
void yyerror(const char* str);

%}

%token BLT CM SC NL ID

%%
start : BLT varlist SC NL   {printf("\n\nValid Statement\n\n"); exit(0);}

varlist : varlist CM ID
        | ID

%%


int main(){
    yyparse();
    return 0;
}

void yyerror(const char* str){
    printf("\n\n%s\n\n",str);
}
