import java.net.InetAddress;

public class structure {
	InetAddress inetAdress=null;
	int port=-1;
	String name="";
	structure(InetAddress inetAddress , int port , String name){
		this.inetAdress=inetAddress;
		this.port=port;
		this.name=name;
	}
}
