#include<stdio.h>
#include<jni.h>
#include "JNITest.h"
JNIEXPORT void JNICALL Java_JNITest_greet
(JNIEnv *env,jobject obj)
{
  int a,b,result;
  char op;
   printf("\tCalculator\n");
   printf("Enter first number\n");
   scanf("%d",&a);
   printf("\nEnter 2nd number\n");
   scanf("%d",&b);
  printf("\nEnter operation\n");
   scanf(" %c",&op);
switch(op)
      {
         case '+':
            result=a+b;
           break;
         case '-':
            result=a-b;
           break;
         case '*':
            result=a*b;
           break;
         case '/':
            result=a/b;
            break;
      }
   printf("\nResult is:%d",result);
}
