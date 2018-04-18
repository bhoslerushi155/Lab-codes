

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class reciever extends Thread{
	static MulticastSocket ms=null;
	static String name=null;
	static byte[]  recv=null;
	reciever(String name){
		this.name=name;
	}
	public void run() {
		try {

			ms=new MulticastSocket(7003);
			InetAddress inetAddress=InetAddress.getByName("225.4.5.6");
			ms.joinGroup(inetAddress);
			String ip="";
			while(!ip.equals("quit")) {
				recv=new byte[1024];
				DatagramPacket packet=new DatagramPacket(recv,recv.length);
				ms.receive(packet);
				String tmp=new String(packet.getData());
				if(!tmp.startsWith(name)) {
					System.out.println(tmp);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
