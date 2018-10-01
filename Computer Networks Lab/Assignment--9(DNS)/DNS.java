
import java.net.*;
import java.util.Scanner;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;


public class DNS {
	public static void main(String args[]) {
		try {
			InetAddress inetAddress;
			Scanner sc=new Scanner(System.in);
			System.out.println("enter ip or url ");
			String input=sc.next();
			
			if(Character.isDigit(input.charAt(0))) {
				byte[] b=new byte[4];
				String[] str=new String[4];
				str=input.split("[.]");
				for(int i=0;i<str.length;i++) {
					b[i]=new Integer(str[i]).byteValue();
				}
				inetAddress=InetAddress.getByAddress(b);
			}
			else {
				inetAddress=InetAddress.getByName(input);
			}
			
			System.out.println("URL :"+inetAddress.getHostName());
			System.out.println("IP :"+inetAddress.getHostAddress());
			
		}
		catch(UnknownHostException e) 
		{
			System.out.println("ERROR : "+e);
		}
		catch(NamingException e) 
		{
			System.out.println("Error :"+e);
		}
	}
}
