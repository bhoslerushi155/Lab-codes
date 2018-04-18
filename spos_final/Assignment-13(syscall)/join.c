#include<stdio.h>
#include<unistd.h>
#include<pthread.h>

void add1(int a[3]){
    a[2]=a[1] + a[0];
    printf("Result of first thread is : %d\n",a[2]);
}

void add2(int a[3]){
    a[2]=a[1] + a[0];
    printf("Result of second thread is : %d\n",a[2]);
}

int main(){
    pthread_t thread1 ,thread2;
    int a1[3],a2[3],temp[4];
    printf("enter four numbers\n");
    for(int i=0;i<4;i++){
        scanf("%d",&temp[i]);
    }
    a1[0]=temp[0];
    a1[1]=temp[1];
    a2[0]=temp[2];
    a2[1]=temp[3];

    pthread_create(&thread1,NULL,(void*)add1,a1);
    pthread_create(&thread2,NULL,(void*)add2,a2);
    pthread_join(thread1,NULL);
    pthread_join(thread2,NULL);
    int result=a1[2] + a2[2];
    printf("Final result is : %d\n",result);
    return 0;
}
