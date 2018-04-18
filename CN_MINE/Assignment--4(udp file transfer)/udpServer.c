#include<stdio.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<arpa/inet.h>
#include<string.h>

long fsize(char * name){
	FILE *f;
	f=fopen(name,"r");
	fseek(f,0,SEEK_END);
	long size=(long)ftell(f);
	fclose(f);
	return size;
}


int main(){
	struct sockaddr_in server_addr,client_addr;
	char send_data[1024],recv_data[1024],buff[1024],temp[1024];
	int sockfd;
	socklen_t size;

	if((sockfd=socket(AF_INET,SOCK_DGRAM,0))==-1){
		printf("error creating socket...\n");
		return 1;
	}

	bzero((char*)& server_addr,sizeof(server_addr));
	server_addr.sin_family=AF_INET;
	server_addr.sin_port=htons(7000);
	server_addr.sin_addr.s_addr=inet_addr("127.0.0.1");

	if(bind(sockfd,(struct sockaddr * )&server_addr,sizeof(server_addr))==-1){
		printf("error binding ...\n");
		return 1;
	}

	size=sizeof(client_addr);
	FILE *fp;
	long siz,itr;	
	recvfrom(sockfd,temp,1024,0,(struct sockaddr *)&client_addr,&size);
	fp=fopen(temp,"rb");
	siz=fsize(temp);
	sprintf(temp,"%lu",siz);
	sendto(sockfd,temp,1024,0,(struct sockaddr*)&client_addr,size);
	itr=1;
	while(itr*1024 < siz){
		bzero((char*)buff,1024);
		fread(buff,1024,1,fp);
		sendto(sockfd,buff,1024,0,(struct sockaddr * )&client_addr,size);
		itr++;
	}

	bzero((char*)buff,1024);
	fread(buff,(siz%1024),1,fp);
	sendto(sockfd,buff,(siz%1024),0,(struct sockaddr* )&client_addr,size);
	fclose(fp);
	return 0;

}