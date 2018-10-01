#include<stdio.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<unistd.h>
#include<string.h>
#include<arpa/inet.h>

int main(){
	struct sockaddr_in server_addr,client_addr;
	char recv_data[7];
	int sockfd,bytes_recv,connectfd,true=1;
	socklen_t size;

	sockfd=socket(AF_INET,SOCK_STREAM,0);

	if(sockfd==-1){
		printf("error creating socket...");
		return 1;
	}

	if (setsockopt(sockfd,SOL_SOCKET,SO_REUSEADDR,&true,sizeof(int)) == -1) {
        perror("Setsockopt");
        return 1;
    }

	bzero((char *) &server_addr, sizeof(server_addr));
	server_addr.sin_family=AF_INET;
	server_addr.sin_port=htons(7001);
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

	recv(connectfd,recv_data,7,0);

	int temp;
	int c1,c2,c3;

	printf("recieved data is:%s\n",recv_data);

	c1=recv_data[0]^recv_data[2]^recv_data[4]^recv_data[6];
	c2=recv_data[1]^recv_data[2]^recv_data[5]^recv_data[6];
	c3=recv_data[3]^recv_data[4]^recv_data[5]^recv_data[6];

	temp=c3*4+c2*2+c1;

	if(temp==0){
		printf("No error in message...\n");
	}
	else{
		printf("Error on position : %d\n",temp);
		if(recv_data[temp]=='1'){
			recv_data[temp]='0';
		}
		else{
			recv_data[temp]='1';
		}
		printf("correct messege is : %s",recv_data);
	}

	

	close(sockfd);

	return 0;
}
