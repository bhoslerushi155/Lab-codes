import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class fileWriter {
	public static void main(String args[]) throws FileNotFoundException, IOException {
			data obj=new data();
			String ans="";
			Scanner sc=new Scanner(System.in);
			while(!ans.equals("n")) {
				System.out.println("enter url :");
				obj.url=sc.next();
				System.out.println("enter ip");
				obj.ip=sc.next();
				ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream("recoard.txt",true));
				os.writeObject(obj);
				os.close();
				System.out.println("do you want to continue(y/n)");
				ans=sc.next();
			}
	}
}
