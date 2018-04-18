import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class client implements Runnable{
	
	static InetAddress inetAddress=null;
	static int port=-1;
	static DatagramSocket cs=null;
	
	public static void main(String args[]) {
		try {
			cs=new DatagramSocket();
			byte[] sendData;
			String send="";
			String name="";
			DatagramPacket sendPacket=null;
			inetAddress=InetAddress.getByName("localhost");
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			port=7000;


			System.out.println("enter name :");
			name=br.readLine();
			
			send="name";
			sendData=new byte[1024];
			sendData=send.getBytes();
			sendPacket=new DatagramPacket(sendData , sendData.length , inetAddress  ,port);
			cs.send(sendPacket);
			
			
			sendData=new byte[1024];
			sendData=name.getBytes();
			sendPacket=new DatagramPacket(sendData , sendData.length , inetAddress , port);
			cs.send(sendPacket);
			send="";
			System.out.println("you can start communication ....press 'quit' to exit");
			new Thread(new client()).start();
			while(!send.equalsIgnoreCase("quit")) {
				send=br.readLine();
				sendData=new byte[1024];
				sendData=send.getBytes();
				sendPacket=new DatagramPacket(sendData , sendData.length , inetAddress , port);
				cs.send(sendPacket);
			}
			
			br.close();
			cs.close();
			return;
		} catch (Exception e) {
			return;
		}
		
	}

	@Override
	public void run() {
		byte[] recvData;
		String recv="";
		DatagramPacket recvPacket=null;

		while(!recv.startsWith("BYE")) {	
			try {
				recvData=new byte[1024];
				recvPacket=new DatagramPacket(recvData , recvData.length);
				cs.receive(recvPacket);
				recv=new String(recvPacket.getData());
				System.out.println(recv);
			} catch (IOException e) {	
				return;
			}
		}
		
	}
}
