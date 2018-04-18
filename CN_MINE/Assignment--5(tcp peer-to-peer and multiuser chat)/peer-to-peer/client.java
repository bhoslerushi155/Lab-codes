import java.io.*;
import java.net.*;

public class client{
	public static void main(String args[]) throws IOException{
		Socket s=new Socket("localhost",7000);
		String str="",reply="";

		DataInputStream din=new DataInputStream(s.getInputStream());
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		while(!str.equals("quit") && !reply.equals("quit")){	
			reply=br.readLine();	
			dout.writeUTF(reply);
			dout.flush();
			str=din.readUTF();
			System.out.println("Server : "+str);
		}
		s.close();
	}
}