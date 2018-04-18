import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class handleCommunication extends Thread{
	DataInputStream din=null;
	DataOutputStream dout=null;
	handleCommunication[] threads=null;
	String name=null;
	int max=10;
	public handleCommunication(Socket cs , handleCommunication[] threads) throws IOException {
		din=new DataInputStream(cs.getInputStream());
		dout=new DataOutputStream(cs.getOutputStream());
		this.threads=threads;
	}
	public void run() {
		int i;
		try {
			String input="";
			dout.writeUTF("enter name");
			dout.flush();
			name=din.readUTF();
			
			for(i=0;i<max;i++) {
				if(threads[i]!=this && threads[i]!=null) {
					threads[i].dout.writeUTF("new user : "+name +" connected");
					threads[i].dout.flush();
				}
			}
			
			while(!input.equals("quit")) {
				input=din.readUTF();
				for( i=0;i<max;i++) {
					if(threads[i]!=this  && threads[i]!=null) {
						threads[i].dout.writeUTF(name + " : " + input);
						threads[i].dout.flush();
					}
				}
			}
			
			for( i=0;i<max;i++) {
				if(threads[i]!=this && threads[i]!=null) {
					threads[i].dout.writeUTF("user : "+name+" left");
					threads[i].dout.flush();
				}
				if(threads[i]==this) {
					threads[i].dout.writeUTF("bye.....");
					threads[i].dout.flush();
					threads[i]=null;
				}
			}
			dout.close();
			din.close();
			
		}catch(Exception e) {
			
		}	
	}
}
