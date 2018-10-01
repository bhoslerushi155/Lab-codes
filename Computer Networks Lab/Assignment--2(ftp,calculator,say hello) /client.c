#include<stdio.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<unistd.h>
#include<string.h>
#include<arpa/inet.h>
#include<stdlib.h>

unsigned long fsize(char* file)
{
    FILE * f = fopen(file, "r");
    fseek(f, 0, SEEK_END);
    unsigned long len = (unsigned long)ftell(f);
    fclose(f);
    return len;
}


int main(){
	struct sockaddr_in server_addr;
	char send_data[1024],recv_data[1024];
	int sockfd,connectfd;

	if((sockfd=socket(AF_INET,SOCK_STREAM,0))==-1){
		printf("error creating socket...");
		return 1;
	}

	server_addr.sin_family=AF_INET;
	server_addr.sin_port=htons(7002);
	server_addr.sin_addr.s_addr=inet_addr("127.0.0.1");

	if((connectfd=connect(sockfd,(struct sockaddr*)&server_addr,sizeof(server_addr)))==-1){
		printf("error connecting...");
		return 1;
	}

	FILE* fp;
	int ch=0,itr;
	long siz;
	char buff[2],buf[1024];
	char choice[2],fname[50],newFname[50];
	char num1[10],num2[10],op[2],result[10];
	while(ch!=-1){
		printf("Enter choice\n");
		printf("1. say hello\n");
		printf("2. file transfer\n");
		printf("3. calculator\n");
		printf("4. Exit\n");
		scanf("%s",choice);

		send(sockfd,choice,2,0);
		choice[1]='\0';
		switch(choice[0]){
			case '1':
				printf("enter messege to be sent : ");
				scanf("%s\n",send_data);
				send(sockfd,send_data,1024,0);
				recv(sockfd,recv_data,1024,0);
				printf("recieved data is : %s\n",recv_data);
			break;
			case '2':
				printf("enter name of the file to be transfered (with extentions)...\n");
				scanf("%s",fname);
				send(sockfd,fname,50,0);
				recv(sockfd,buf,1024,0);
				siz=atoi(buf);
				printf("enter new name for the file (eith extentions)...\n");
				scanf("%s",newFname);
				fp=fopen(newFname,"wb");
				itr=1;
				while(itr*2 <= siz){
					bzero((char* )&buf,2);
					recv(sockfd,buf,2,0);
					fwrite(buf,2,1,fp);
					itr++;
				}
				fclose(fp);
			break;
			case '3':
				bzero(num1,10);
				printf("enter first number :");
				scanf("%s",num1);
				send(sockfd,num1,10,0);
				bzero(op,2);
				printf("enter operator :");
				scanf("%s",op);
				op[1]='\0';
				send(sockfd,op,2,0);
				bzero(num2,10);
				printf("enter second number :");
				scanf("%s",num2);
				send(sockfd,num2,10,0);
				bzero(result,10);
				recv(sockfd,result,10,0);
				printf("result of %s %s %s = %s\n",num1,op,num2,result);

			break;

			default:
				ch=-1;
			break;
		}
	}

	close(sockfd);
	return 0;

}
