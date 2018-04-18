package temp;

import java.util.Scanner;
import java.math.*;

public class subnet {
	public static void main(String args[]) {
			Scanner sc =new Scanner(System.in);
			String input=null;
			subnet obj=new subnet();
			int noOfSubnet=-1;
			System.out.println("enter ip address ");
			input=sc.nextLine();
			System.out.println("enter no of subnets");
			noOfSubnet=sc.nextInt();
			
			String buf[]=input.split("\\.");
			
			if(!obj.checkIp(buf)) {
				System.out.println("not a valid ip address ");
				return ;
			}
			int t=Integer.parseInt(buf[0]);
			int cl,bit;
			if(t>0 && t<128) {
				cl=1;
				bit=obj.getNoOfBits(noOfSubnet);
				if(!obj.validate(cl,bit)) {
					System.out.println("subnetting not possible ");
					return ;
				}
				System.out.println("subnet mask is:"+obj.returnSubnetMask(cl, bit));
				
			}
			else if(t>=128 && t<192) {
				cl=2;
				bit=obj.getNoOfBits(noOfSubnet);
				if(!obj.validate(cl,bit)) {
					System.out.println("subnetting not possible ");
					return ;
				}
				System.out.println("subnet mask is:"+obj.returnSubnetMask(cl, bit));

			}
			else if(t>=192 && t<234) {
				cl=3;
				bit=obj.getNoOfBits(noOfSubnet);
				if(!obj.validate(cl,bit)) {
					System.out.println("subnetting not possible ");
					return ;
				}
				System.out.println("subnet mask is : "+obj.returnSubnetMask(cl, bit));

			}
			else {
				cl=4;
				System.out.println("subnetting not possible ");
					return ;
				
			}
			
			
			
	}
	
	public boolean checkIp(String ip[]) {
		int temp;
		for(int i=0;i<4;i++) {
			temp=Integer.parseInt(ip[i]);
			if(temp>255 || temp<0) {
				return false;
			}
		}
		return true;	
	}
	
	public int getNoOfBits(int noOfSubnet) {
		int ret=-1;
		ret=(int)Math.ceil(Math.log(noOfSubnet)/Math.log(2));
		return ret;
	}
	public boolean validate(int cl,int bit) {
		if(cl==1 && bit>22) {
			return false;
		}
		else if(cl==2 && bit>14 ) {
			return false;
		}
		else if(cl==3 && bit>6) {
			return false;
		}
		else if(cl==4) {
			return false ;
		}		
		return true;
	}
	
	public String returnSubnetMask(int cl,int bit) {
		String mask="";
		String sub="";
		int temp=bit;
		int count=0;
		while(temp>0) {
			count++;
			if(temp>=8) {
				sub+=".255";
				temp-=8;
			}
			else {
				int t=0;
				while(temp>0) {	
					t+=Math.pow(2, 8-temp);
					temp--;
				}
				sub+=".";
				sub+=Integer.toString(t);
			}
			
		}
		
		if(cl==1) {
			mask+="255";
		}
		else if(cl==2) {
			mask+="255.255";
		}
		else if(cl==3) {
			mask+="255.255.255";
		}
		
		
		if(cl==1) {
			mask+=sub;
			if(count==1) {
				mask+=".0.0";
			}
			else if(count==2) {
				mask+=".0";
			}
		}
		else if(cl==2) {
			mask+=sub;
			if(count==1) {
				mask+=".0";
			}
		}
		else if(cl==3) {
			mask+=sub;
		}
		return mask;
	}
}
