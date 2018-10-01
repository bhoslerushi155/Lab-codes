#include<pthread.h>
#include<stdio.h>
#include<sys/types.h>

pthread_t thread1,thread2;

int add1(int a[3])
{
        a[2]=a[1]+a[0];
        printf("Result from thread1 is: %d \n",a[2]);
}

int add2(int b[3])
{
        b[2]=b[1]+b[0];
        printf("Result from thread2 is: %d \n",b[2]);
}

main()
{
        int arr[4],a[3],b[3],i,ans=0;
        pthread_t thread1,thread2;
        printf("Enter Four Numbers: ");
        for(i=0;i<4;i++)
        {
                scanf("%d",&arr[i]);
        }
        a[0]=arr[0];
        a[1]=arr[1];
        b[0]=arr[2];
        b[1]=arr[3];
        
        pthread_create(&thread1 ,NULL, (void *)add1,a);
        pthread_create(&thread2 ,NULL, (void *)add2,b);
        pthread_join(thread1,NULL);
        pthread_join(thread2,NULL);
        ans=a[2]+b[2];
        printf("Final Result: %d \n", ans);
        
}

