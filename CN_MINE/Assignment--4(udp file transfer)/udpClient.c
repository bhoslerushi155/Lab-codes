#include<stdio.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<arpa/inet.h>
#include<string.h>
#include<stdlib.h>
long fsize(char * name){
	FILE *f;
	f=fopen(name,"r");
	fseek(f,0,SEEK_END);
	long size=(long)ftell(f);
	fclose(f);
	return size;
}


int main(){
	struct sockaddr_in server_addr;
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

	size=sizeof(server_addr);
	FILE *fp;
	long siz;
	char fname[50],newFname[50];
	long itr;
	printf("enter name of file to traansfer..\n");
	scanf("%s",fname);
	printf("enter new file name\n");
	scanf("%s",newFname);
	fp=fopen(newFname,"wb");
	sendto(sockfd,fname,50,0,(struct sockaddr*)&server_addr,size);
	recvfrom(sockfd,temp,1024,0,(struct sockaddr*)&server_addr,&size);
	siz=atoi(temp);
	itr=1;
	while(itr*1024 < siz){
		bzero((char*)buff,1024);
		recvfrom(sockfd,buff,1024,0,(struct sockaddr * )&server_addr,&size);
		fwrite(buff,1024,1,fp);
		itr++;
	}

	bzero((char*)&buff,1024);
	recvfrom(sockfd,buff,(siz%1024),0,(struct sockaddr*)&server_addr,&size);
	fwrite(buff,(siz%1024),1,fp);
	fclose(fp);
	return 0;
}