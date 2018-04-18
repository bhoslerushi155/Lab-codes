import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {
	public static void main(String args[]) throws UnknownHostException, IOException {
		Socket cs=new Socket("localhost",7000);
		Scanner sc=new Scanner(System.in);
		String input="",reply="",ch="";
		
		DataOutputStream dout=new DataOutputStream(cs.getOutputStream());
		DataInputStream din =new DataInputStream(cs.getInputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while(!ch.equalsIgnoreCase("n")) {
			System.out.println("enter url or ip");
			input=sc.next();
			if(Character.isDigit(input.charAt(0))) {
				dout.writeUTF("ip");
				dout.writeUTF(input);
				reply=din.readUTF();
				if(reply.equals("nofd")) {
					System.out.println("recoard not found ");
				}
				else {
					System.out.println("Given ip : "+input);
					System.out.println("url : "+reply);
				}
				
			}
			else {
				dout.writeUTF("url");
				dout.writeUTF(input);
				reply=din.readUTF();
				if(reply.equals("nofd")) {
					System.out.println("recoard not found ");
				}
				else {
					System.out.println("Given url : "+input);
					System.out.println("ip : "+reply);
				}
			}
			System.out.println("do you want to continue(y/n)");
			ch=sc.next();
		}
	}
}
