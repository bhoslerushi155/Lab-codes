

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.*;
public class publisher {
	static MulticastSocket ms=null;
	static InetAddress inetAddress=null;
	static byte[] send=null;
	static int port=7000;
	public static void main(String[] args) {
		try {
			String name="";
			System.setProperty("java.net.preferIPv4Stack", "true");
			inetAddress=InetAddress.getByName("225.4.5.6");
			ms=new MulticastSocket();
			ms.setNetworkInterface(NetworkInterface.getByInetAddress(
                                 InetAddress.getByName( "127.0.0.1" )));
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			String input="",str="";
			
			
			System.out.println("enter name");
			name=br.readLine();
			System.out.println("*** you can start communication ***");
			str+="new user "+name+" joined\n";
			send=new byte[1024];
			send=str.getBytes();
			DatagramPacket packet=new DatagramPacket(send , send.length , inetAddress,port);
			ms.send(packet);
			
			
			reciever thread=new reciever(name);
			thread.start();
			input=br.readLine();	
			while(!input.equals("quit")) {	
				str="";
				str+=name;
				str+=" : ";
				send=new byte[1024];
				str+=input;
				send=str.getBytes();
				packet=new DatagramPacket(send , send.length , inetAddress ,port);
				ms.send(packet);
				input=br.readLine();	
			}
			
			send=new byte[1024];
			str="";
			str+="user "+name +" left\n";
			send=str.getBytes();
			packet=new DatagramPacket(send, send.length,inetAddress,port);
			ms.send(packet);
			System.out.println("BYE "+name+" !!!");
			ms.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
