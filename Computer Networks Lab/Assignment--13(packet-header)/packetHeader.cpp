#include<iostream>
#include<fstream>

using namespace std;

int main(){
	int desMac[6];
	int srcMac[6];
	string NLP;
	string TLP;
	int srcIp[4];
	int desIp[4];
	int srcPort;
	int desPort;
	int index=0;
	//int flag=0;
	int tp=0;

	int portHolder[2];
	int NLPHolder[2];
	char byteHolder[2];

	ifstream in;

	in.open("input",ios::in);

	while(!in.eof()){

		for(int i=0;i<2;i++){
			in.get(byteHolder[i]);
		}

		if(byteHolder[0]!='\n' && byteHolder[1]!='\n'){
			int x,y,decimal;
			if(byteHolder[0]>='a' && byteHolder[0]<='f'){
				x=byteHolder[0]-87;
			}
			else{
				x=byteHolder[0]-48;
			}
			if(byteHolder[1]>='a' && byteHolder[1]<='f'){
				y=byteHolder[1]-87;
			}
			else{
				y=byteHolder[1]-48;
			}
			decimal=16*x + y;

			if(index>=0 && index<=5){
				desMac[tp]=decimal;
				tp++;
			}
			if(index==6){
				tp=0;
			}
			if(index>=6 && index<=11){
				srcMac[tp]=decimal;
				tp++;
			}
			if(index==12){
				tp=0;
			}
			if(index>=12 && index<=13){
				NLPHolder[tp]=decimal;
				tp++;
			}
			
			if(index==14){
				tp=0;
				if(NLPHolder[0]==8 && NLPHolder[1]==0){
					NLP="IP";
				}
				else if(NLPHolder[0]==8 && NLPHolder[1]==6){
					NLP="ARP";
				}
				else if(NLPHolder[0]==128 && NLPHolder[1]==53){
					NLP="RARP";
				}
				else if(NLPHolder[0]==129 && NLPHolder[1]==55){
					NLP="ICMP";
				}
			}
			if(index==23 && NLP=="IP"){
				if(decimal==6){
					TLP="TCP";
				}
				if(decimal==17){
					TLP="UDP";
				}
			}
			if(index>=26 && index<=29){
				srcIp[tp]=decimal;
				tp++;
			}
			if(index==30){
				tp=0;
			}
			if(index>=30 && index<=33){
				desIp[tp]=decimal;
				tp++;
			}
			if(index==34){
				tp=0;
			}
			if(index>=34 && index<=35){
				portHolder[tp]=decimal;
				tp++;
			}
			if(index==36){
				tp=0;
				srcPort=16*portHolder[0] + portHolder[1];
			}
			if(index>=36 && index<=37){
				portHolder[tp]=decimal;
				tp++;
			}
			if(index==37){
				tp=0;
				desPort=16*portHolder[0] + portHolder[1];
			}


		}

		index++;
		if(byteHolder[0]=='\n' || byteHolder[1]=='\n'){
			cout<<"\n\n";
			cout<<"DESTINATION MAC : "<<desMac[0]<<":"<<desMac[1]<<":"<<desMac[2]<<":"<<desMac[3]<<":"<<desMac[4]<<":"<<desMac[5]<<endl;
			cout<<"SOURCE MAC : "<<srcMac[0]<<":"<<srcMac[1]<<":"<<srcMac[2]<<":"<<srcMac[3]<<":"<<srcMac[4]<<":"<<srcMac[5]<<endl;

			cout<<"NETWORK LAYER PROTOCLO : "<<NLP<<endl;
			if(NLP=="IP"){
				cout<<"TRANSPORT LAYER PROTOCOL : "<<TLP<<endl;
				cout<<"SOURCE IP : "<<srcIp[0]<<":"<<srcIp[1]<<":"<<srcIp[2]<<":"<<srcIp[3]<<endl;
				cout<<"DESTINATION IP : "<<desIp[0]<<":"<<desIp[1]<<":"<<desIp[2]<<":"<<desIp[3]<<endl;
				cout<<"SOURCE PORT : "<<srcPort<<endl;
				cout<<"DESTINATION PORT : "<<desPort<<endl;
				if(desPort==21){
					cout<<"APPLICATION : FTP"<<endl;
				}
				else if(desPort==23){
					cout<<"APPLICATION : TELNET"<<endl;
				}
				else if(desPort==25){
					cout<<"APPLICATION : SMTP"<<endl;
				}
				if(desPort==53){
					cout<<"APPLICATION : DNS"<<endl;
				}
				else if(desPort==80){
					cout<<"APPLICATION : HTTP"<<endl;
				}

			}

			index=0;
		}
		
	}
	return 0;
}