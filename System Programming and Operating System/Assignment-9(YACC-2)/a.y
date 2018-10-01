%{ #include<math.h>
   #include<stdio.h>
   extern int yyparse();
   extern int yylex();
   extern int yyerror(char *e);
%}
%token NUM
%token ID
%token WHILE
%token DO
%token eq
%token eq1
%token o
%token op
%token lt 
%token cl
%start s
%%
s : WHILE op exp cl DO s1 {printf("\nValid") ; exit(0);};
exp :exp lt ID            {printf("\nPartial Valid") ; exit(0);};
     |exp eq1 ID
     |exp eq1 NUM
     |exp lt NUM
     |ID
     |NUM
     ;
s1 :ID eq f		  {printf("\nPartial Valid 1") ; exit(0);};
   ;
f : f o ID		  {printf("\nPartial Valid2") ; exit(0);};
   |f o NUM
   |NUM
   |ID
   ;
%% 
int main(){
 yyparse();
}

extern int yyerror(char *e){
 printf("Syntax Error.\n"); 
}
