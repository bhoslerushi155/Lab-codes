#include<stdio.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<unistd.h>
#include<string.h>
#include<arpa/inet.h>

int main(){
	struct sockaddr_in server_addr;
	char send_data[7],temp[4];
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
	char data[4];
	printf("enter 4 bits one by one: \n");
	
	scanf("%s",&data[0]);
	scanf("%s",&data[1]);
	scanf("%s",&data[2]);
	scanf("%s",&data[3]);

	printf("entered data is : %s\n",data);

	send_data[2]=data[0];
	send_data[4]=data[1];
	send_data[5]=data[2];
	send_data[6]=data[3];

	send_data[0]=send_data[2]^send_data[4]^send_data[6];
	send_data[1]=send_data[2]^send_data[5]^send_data[6];
	send_data[3]=send_data[4]^send_data[5]^send_data[6];

	printf("Encoded message is :%s\n",send_data);

	send(sockfd,send_data,7,0);

	close(sockfd);
	return 0;

}
