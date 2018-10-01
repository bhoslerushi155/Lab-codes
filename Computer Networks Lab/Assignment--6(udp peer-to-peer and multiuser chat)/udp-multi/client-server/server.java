import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class server {
	public static void main(String args[]) {
		try {
			int max=10;
			structure obj[]=new structure[max];
			int serverPort=7000;
			InetAddress serverAddr=InetAddress.getByName("localhost");
			DatagramSocket ss=new DatagramSocket(serverPort,serverAddr);
			System.out.println("server started...");
			byte[] sendData;
			byte[] recvData;
			String recv="";
			String send="";
			String name="";
			String tp="";
			DatagramPacket recvPacket=null;
			DatagramPacket sendPacket=null;
			InetAddress inetAddress=null;
			int port=-1;
			
			while(true) {
				recvData=new byte[1024];
				recvPacket=new DatagramPacket(recvData , recvData.length);
				ss.receive(recvPacket);
				recv=(new String(recvPacket.getData())).trim();
				
				if(recv.equals("name")) {
					int ind=0;
					for(int i=0;i<max;i++) {
						if(obj[i]==null) {
							recvPacket=new DatagramPacket(recvData,recvData.length);
							ss.receive(recvPacket);
							name=(new String(recvPacket.getData())).trim();
							port=recvPacket.getPort();
							inetAddress=recvPacket.getAddress();
							obj[i]=new structure(inetAddress, port, name);
							ind=i;
							break;
						}
					}
					for(int i=0;i<max ;i++) {
						if(obj[i]!=null && i!=ind) {
							String tst;
							tst="new user "+obj[ind].name+" connected";
							sendData=tst.getBytes();
							sendPacket=new DatagramPacket(sendData,sendData.length , obj[i].inetAdress,obj[i].port);
							ss.send(sendPacket);
						}
					}
				}
				
				else if(recv.equalsIgnoreCase("quit")) {
					inetAddress=recvPacket.getAddress();
					port=recvPacket.getPort();
					String n="";
					for(int i=0;i<max;i++) {
						if(obj[i]!=null && obj[i].port==port) {
							name=obj[i].name;
							tp="BYE "+name+" !!!\n";
							sendData=new byte[1024];
							sendData=tp.getBytes();
							sendPacket=new DatagramPacket(sendData , sendData.length , obj[i].inetAdress , obj[i].port);
							ss.send(sendPacket);
							n+=obj[i].name;
							obj[i]=null;
							break;
						}
					}
					for(int i=0;i<max ;i++) {
						if(obj[i]!=null) {
							String str="user : '"+n+"' disconnected\n";
							sendData=str.getBytes();
							sendPacket=new DatagramPacket(sendData, sendData.length,obj[i].inetAdress,obj[i].port);
							ss.send(sendPacket);
						}
					}
				}
				else {
					inetAddress=recvPacket.getAddress();
					port=recvPacket.getPort();
					String t="",nm="" ;
					for(int i=0;i<max;i++) {
						if(obj[i]!=null && obj[i].port==port) {
							nm=obj[i].name;
							break;
						}
					}
					t+=nm;
					t+=" : ";
					t+=recv;
					for(int i=0;i<max;i++) {
						if(obj[i]!=null) {
							if( obj[i].port!=port) {
								sendData=new byte[1024];
								sendData=t.getBytes();
								sendPacket=new DatagramPacket(sendData , sendData.length , obj[i].inetAdress , obj[i].port );
								ss.send(sendPacket);
							}
							
						}
					}
				}
			}

		} catch (Exception e) {
			return;
		}
	}
}
