#include<stdio.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<unistd.h>
#include<string.h>
#include<arpa/inet.h>

int main(){
	struct sockaddr_in server_addr;
	char send_data[1024];
	int sockfd,connectfd;

	if((sockfd=socket(AF_INET,SOCK_STREAM,0))==-1){
		printf("error creating socket...");
		return 1;
	}

	server_addr.sin_family=AF_INET;
	server_addr.sin_port=htons(7001);
	server_addr.sin_addr.s_addr=inet_addr("127.0.0.1");

	if((connectfd=connect(sockfd,(struct sockaddr*)&server_addr,sizeof(server_addr)))==-1){
		printf("error connecting...");
		return 1;
	}

	printf("enter data: \n");
	scanf("%s",send_data);

	printf("entered data is : %s\n",send_data);

	send(sockfd,send_data,1024,0);

	close(sockfd);
	return 0;

}
