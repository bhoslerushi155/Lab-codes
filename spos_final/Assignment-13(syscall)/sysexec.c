#include<stdio.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<unistd.h>

int main(){
    char* ls_arg[]={"ls" , "-l" , NULL};
    //{process_name , flags , NULL for termanitaion}

    pid_t pid;
    pid=fork();

    if(pid==-1){
        printf("fork failed ..... \n");
    }
    else if(pid==0){
        printf("child process running with pid = %d...\n",getpid());
        execvp(ls_arg[0],ls_arg);
    }
    else if(pid>0){
        wait(NULL);
        printf("parent process running with pid = %d...\n",getpid());
    }
}
