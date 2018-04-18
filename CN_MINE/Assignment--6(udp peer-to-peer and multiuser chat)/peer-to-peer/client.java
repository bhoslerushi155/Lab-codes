import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;


public class client{
	public static void main(String args[]) throws IOException{
		int port =7001;
		DatagramSocket ds=new DatagramSocket();
		byte[] buffer,buffer1;
		InetAddress inetAddress=InetAddress.getByName("localhost");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		String str="",reply="";

		while(!str.equals("quit") && !reply.equals("quit")){
			reply=br.readLine();
			buffer=reply.getBytes();
			DatagramPacket packet=new DatagramPacket(buffer,buffer.length,inetAddress,port);
			ds.send(packet);

			buffer1=new byte[50];
			DatagramPacket packet1=new DatagramPacket(buffer1,buffer1.length);
			ds.receive(packet1);
			
			System.out.println("server : "+ new String(packet1.getData()));
		}
	}
}