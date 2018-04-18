%{

#include<stdio.h>
#include<stdlib.h>

int yylex();
void yyerror(const char* str);

%}

%token WHILE  NUM EQ EQQ LT O ID OP CL OPCL CLCL SC NL

%%

start : WHILE OP bool CL NL OPCL NL statements CLCL   {printf("\n\nValid Statement\n\n"); exit(0);}

bool : NUM EQQ NUM
    | NUM EQQ ID
    | ID EQQ NUM
    | ID EQQ ID
    | NUM O NUM
    | NUM O ID
    | ID O NUM
    | ID O ID



statements : statements one NL
            | one NL


one : ID EQ ID SC
    |ID EQ NUM SC
    |ID EQ NUM O NUM SC
    |ID EQ  NUM O ID SC
    | ID EQ ID O NUM SC
    | ID EQ ID O ID SC


%%


int main(){
    yyparse();
    return 0;
}

void yyerror(const char* str){
    printf("\n\n%s\n\n",str);
}
