#include<stdio.h>
#include<sys/types.h>
pid_t num_pid;

main()
{
	num_pid=fork();
	
	if(num_pid==0)
	{
	        printf("This is the child process %d \n",getpid());
	}
	if(num_pid>0)
	{
	        printf("This is the Parent process %d ",getpid());
	}
}

