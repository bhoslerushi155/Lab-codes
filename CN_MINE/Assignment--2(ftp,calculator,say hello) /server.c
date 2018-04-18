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
	
	struct sockaddr_in server_addr,client_addr;
	char recv_data[1024],send_data[1024];
	int sockfd,bytes_recv,connectfd,true=1;
	socklen_t size;

	sockfd=socket(AF_INET,SOCK_STREAM,0);

	if(sockfd==-1){
		printf("error creating socket...");
		return 1;
	}

	bzero((char *) &server_addr, sizeof(server_addr));
	server_addr.sin_family=AF_INET;
	server_addr.sin_port=htons(7002);
	server_addr.sin_addr.s_addr=INADDR_ANY;

	if((bind(sockfd,(struct sockaddr*)&server_addr,sizeof(server_addr)))==-1){
		printf("error binding...");
		return 1;
	}

	if(listen(sockfd,5)==-1){
		printf("error listening...");
		return 1;
	}

	printf("server waiting for client...\n");

	size=sizeof(client_addr);
	connectfd=accept(sockfd,(struct sockaddr*)&client_addr,&size);

	printf("got connected to client...\n");

	FILE *fp;
	int ch=0;
	long itr;
	long siz;
	char buff[2],buf[1024],fname[50];
	char choice[2],temp[10],op[2];
	int num1,num2;
	double result;
	char res[10];
	while(ch!=-1){
		recv(connectfd,choice,2,0);
		choice[1]='\0';
		switch(choice[0]){
			case '1':
				recv(connectfd,recv_data,1024,0);
				printf("received data is : %s\n",recv_data);
				printf("enter reply :");
				scanf("%s",send_data);
				send(connectfd,send_data,1024,0);


			break;
			case '2':
				recv(connectfd,fname,50,0);
				fp=fopen(fname,"rb");
				siz=fsize(fname);
				sprintf(buf,"%lu",siz);
				send(connectfd,buf,1024,0);
				itr=1;
				while(itr*2 <= siz){
					bzero((char*)&buff,2);
					fread(buff,2,1,fp);
					send(connectfd,buff,2,0);
					itr++;
				}
				fclose(fp);
			break;
			case '3':
				bzero(temp,10);
				recv(connectfd,temp,10,0);
				printf("received data : %s\n",temp);
				num1=atoi(temp);
				bzero(op,2);
				recv(connectfd,op,2,0);
				printf("received data : %s\n",op);
				bzero(temp,10);
				recv(connectfd,temp,10,0);
				printf("received data : %s\n",temp);
				num2=atoi(temp);

				switch(op[0]){
					case '+':
						result=num1+num2;
					break;
					case '-':
						result=num1-num2;
					break;
					case '*':
						result=num1*num2;
					break;
					case '/':
						result=num1/num2;
					break;
				}
				printf("result is : %f\n",result);
				sprintf(res,"%f",result);
				send(connectfd,res,10,0);
			break;
			default:
				ch=-1;
			break;
		}
	}
	







	close(sockfd);

	return 0;
}
