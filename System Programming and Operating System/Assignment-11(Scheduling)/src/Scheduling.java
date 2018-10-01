import java.util.*;
import java.io.*;

public class Scheduling {
	static ArrayList<String> process;
	static ArrayList<Integer> AT;
	static ArrayList<Integer> BT;
	static ArrayList<ArrayList<Integer>> slots;
	static ArrayList<String> pname;
	static ArrayList<Integer> priority;
	static Scanner sc;
	static int n;
	static int ch;
	public static void main(String[] args) {
		process=new ArrayList<String>();
		AT=new ArrayList<Integer>();
		BT=new ArrayList<Integer>();
		slots=new ArrayList<ArrayList<Integer>>();
		pname=new ArrayList<String>();
		priority=new ArrayList<Integer>();

		int flag=0;
		sc=new Scanner(System.in);
		Scheduling obj=new Scheduling();
		while(flag==0){
			System.out.println("\n************Enter choice***********");
			System.out.println("1. FCFS");
			System.out.println("2. SJS(pre-emptive)");
			System.out.println("3. Proirity(non-premptive)");
			System.out.println("4. Round Robin");
			System.out.println("5. Exit");
			ch=sc.nextInt();
			obj.accept();
			switch(ch){
			case 1:
				obj.FCFS();
				obj.disp();
				break;
			case 2:

				break;
			case 3:
				obj.sort();
				obj.priorityScheduling();
				obj.disp();
				break;
			case 4:
				break;
			default:
				flag=-1;
				break;
			}
		}
	}
	public void accept(){
		System.out.println("enter no of processes");
		n=sc.nextInt();
		String temp=new String();
		System.out.println("enter name of processes");
		for(int i=0;i<n;i++){
			temp=sc.next();
			process.add(temp);
		}

		int temp1;
		System.out.println("enter arrival time");
		for(int i=0;i<n;i++){
			temp1=sc.nextInt();
			AT.add(temp1);
		}

		System.out.println("enter burst time");
		for(int i=0;i<n;i++){
			temp1=sc.nextInt();
			BT.add(temp1);
		}
		if(ch==3){
			System.out.println("enter priority");
			for(int i=0;i<n;i++){
				temp1=sc.nextInt();
				priority.add(temp1);
			}
		}
	}
	public void swapI(ArrayList<Integer> arr,int i,int j){
		int temp=arr.get(i);
		arr.set(i,arr.get(j));
		arr.set(j,temp);
	}
	public void swapS(ArrayList<String> arr,int i,int j){
		String temp=arr.get(i);
		arr.set(i,arr.get(j));
		arr.set(j,temp);
	}
	public void FCFS(){
		int curr=0;
		ArrayList<Integer> temp;
		for(int i=0;i<n;i++){
			if(AT.get(i)>curr){
				curr=AT.get(i);
			}
			temp=new ArrayList<Integer>();
			temp.add(curr);
			temp.add(curr+=BT.get(i));
			slots.add(temp);
			pname.add(process.get(i));
		}
	}
	public void priorityScheduling(){
		ArrayList<Integer> completed=new ArrayList<Integer>();
		for(int k=0;k<n;k++){
			completed.add(0);
		}
		int i,j,curr=0,index=0;

		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				if(completed.get(j)==0 && AT.get(j)<=curr){
                    index=j;
					completed.set(j,1);
					break;
				}
			}
			ArrayList<Integer> temp=new ArrayList<Integer>();
			temp.add(curr);
			temp.add(curr+BT.get(index));
            curr+=BT.get(index);
			slots.add(temp);
			pname.add(process.get(index));
		}
	}
	public void sort(){
		for(int i=0;i<n-1;i++){
			for(int j=0;j<n-1;j++){
				if(priority.get(j)>priority.get(j+1)){
					swapI(priority,j,j+1);
					swapI(AT,j,j+1);
					swapI(BT,j,j+1);
					swapS(process,j,j+1);
				}
			}
		}
	}

	public void disp(){
		System.out.println("start\tend\tprocess");
		for(int i=0;i<n;i++){
			System.out.print(slots.get(i).get(0)+"\t");
			System.out.print(slots.get(i).get(1)+"\t");
			System.out.println(pname.get(i));
		}
		System.out.println("\n\nprocess\tFT\tWT\tTAT");
		for(int i=0;i<n;i++){
			String pro=process.get(i);
			int ft=slots.get(pname.lastIndexOf(pro)).get(1);
			System.out.print(pro+"\t");
			System.out.print(ft+"\t");
			System.out.print(ft-AT.get(i)-BT.get(i)+"\t");
			System.out.println(ft-AT.get(i));

		}
	}
}



