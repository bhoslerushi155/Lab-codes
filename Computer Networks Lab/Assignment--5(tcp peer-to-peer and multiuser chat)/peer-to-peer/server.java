import java.io.*;
import java.net.*;

public class server{
	public static void main(String args[]) throws IOException{
		ServerSocket ss=new ServerSocket(7000);
		Socket s=ss.accept();

		String str="",reply="";

		DataInputStream din=new DataInputStream(s.getInputStream());
		DataOutputStream dout =new DataOutputStream(s.getOutputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		while(!str.equals("quit") && !reply.equals("quit")){
			str=din.readUTF();
			System.out.println("Client : "+str);
			reply=br.readLine();
			dout.writeUTF(reply);
			dout.flush();
		}

		s.close();
		ss.close();
	}
}