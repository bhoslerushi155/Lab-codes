#include<stdio.h>
#include<sys/types.h>

int main(int args, char *argv[])
{
        char *ps_args[]={"ps"};
        pid_t pid;
        pid=fork();
        
        if(pid==0)
	{
	        printf("This is the child process %d \n",getpid());
	        execvp(ps_args[0], ps_args,getpid());
	}
	else if(pid>0)
	{
	        wait();
	        printf("This is the Parent process %d ",getpid());
	}
	else
	{printf("FORK Failed!");}
}
