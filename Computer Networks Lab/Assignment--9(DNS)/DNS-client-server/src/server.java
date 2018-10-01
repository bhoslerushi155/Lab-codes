import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	public static void main(String args[]) throws IOException, ClassNotFoundException {

		ServerSocket ss=new ServerSocket(7000);
		System.out.println("server started");
		Socket s=ss.accept();
		System.out.println("client connected");
		data obj=null;
		String type="";
		String input="";
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		DataInputStream din=new DataInputStream(s.getInputStream());
		int flag=0;
		while(!input.equalsIgnoreCase("quit")) {
			ObjectInputStream oin=new ObjectInputStream(new FileInputStream("recoard.txt"));
			flag=0;
			type=din.readUTF();
			input=din.readUTF();
			while( (obj=(data)oin.readObject() )!= null)  {
				if(type.equals("url") && obj.url.equals(input)) {
					dout.writeUTF(obj.ip);
					flag=1;
					break;
				}
				else if(type.equals("ip") && obj.ip.equals(input)) {
					dout.writeUTF(obj.url);
					flag=1;
					break;
				}
			}
			if(flag==0) {
				dout.writeUTF("nofd");
			}
			oin.close();
		}
		
	}
}
