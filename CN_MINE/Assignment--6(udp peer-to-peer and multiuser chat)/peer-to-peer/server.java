import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;



public class server{
	public static void main(String args[]) throws IOException{
		 int port =7001;
		DatagramSocket ds=new DatagramSocket(port);

		byte[] buffer ,buffer1;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		String str="",reply="";

		while(!str.equals("quit") && !reply.equals("quit")){
			buffer1=new byte[50];
			DatagramPacket packet=new DatagramPacket(buffer1 , buffer1.length);
			ds.receive(packet);
			
			System.out.println("client : "+new String(packet.getData()));

			reply=br.readLine();
			buffer=reply.getBytes();
			DatagramPacket packet1=new DatagramPacket(buffer , buffer.length , packet.getAddress(), packet.getPort());
			ds.send(packet1);
		}
	}
}