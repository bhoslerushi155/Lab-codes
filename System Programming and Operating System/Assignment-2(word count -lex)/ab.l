%{
#include<stdio.h>
#include<string.h>
int lines=0,chars=0,words=0;
%}

%%
[a-zA-Z0-9]+ {words++,chars+=strlen(yytext);}

\n {lines++;}
. {chars++;}
%%

void main(){
	yyin=fopen("a.java","r");
	yylex();
	printf("words : %d\nlines : %d\nchars : %d\n",words,lines,chars);
}

int yywrap(){
return 1;
}

