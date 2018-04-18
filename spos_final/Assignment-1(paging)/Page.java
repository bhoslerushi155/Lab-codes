import java.util.Scanner;


public class Page {
	char[] page;
	int[] count;
	int frameSize;
	int pageFaultCount;
	int counter;
	String input;

	void initPage(int n){
		page=new char[n];
		count =new int[n];
		for(int i=0;i<n;i++){
			page[i]='-';
			count[i]=0;
		}
		counter=0;
		pageFaultCount=0;
	}

	void get(){
		Scanner sc=new Scanner(System.in);
		System.out.println("enter frame size");
		frameSize=sc.nextInt();
		System.out.println("enter input string");
		input=sc.next();
		initPage(input.length());
	}

	void LRU(){
		int len=input.length();
		int fault=0;
		int flag=0;
		for(int i=0;i<len;i++){
			fault=0;
			flag=0;
			for(int j=0;j<frameSize;j++){
				//page fault
				if(page[j]=='-'){
					flag=1;
					page[j]=input.charAt(i);
					pageFaultCount++;
					fault=1;
					count[j]=++counter;
					break;
				}
				//page hit
				else if(page[j]==input.charAt(i)){
					flag=1;
					count[j]=++counter;
					break;
				}
			}
			//replace page using LRU
			if(flag!=1){
				int temp=999999;
				int index=-1;
				for(int j=0;j<frameSize;j++){
					if(temp>count[j]){
						temp=count[j];
						index=j;
					}
				}
				page[index]=input.charAt(i);
				count[index]=++counter;
				pageFaultCount++;
				fault=1;
			}

			//display iterations
			System.out.print(input.charAt(i)+" ");
			System.out.print("[ ");
			for(int j=0;j<frameSize;j++){
				System.out.print(page[j]);
				if(j<frameSize-1){
					System.out.print(" , ");
				}
			}
			System.out.print(" ]");
			if(fault==1){
				System.out.println(" * " );
			}
			else{
				System.out.println();
			}
		}
	}

	void optimal(){
		int len=input.length();
		int flag=0,fault=0;
		for(int i=0;i<len;i++){
			flag=0;
			fault=0;
			for(int j=0;j<frameSize;j++){
				//page fault
				if(page[j]=='-'){
					flag=1;
					page[j]=input.charAt(i);
					pageFaultCount++;
					fault=1;
					break;
				}
				//page hit
				else if(page[j]==input.charAt(i)){
					flag=1;
					break;
				}

			}
			//page replacement by optimal algorithm
			if(flag!=1){
				int ct=0,tmp=0,index=-1;
				for(int k=0;k<frameSize;k++){
					tmp=0;
					for(int j=i+1;j<len;j++){
						tmp++;
						if(input.charAt(j)==page[k]){
							break;
						}
					}
					if(ct<tmp){
						ct=tmp;
						index=k;
					}
				}
				page[index]=input.charAt(i);
				pageFaultCount++;
				fault=1;

			}

			//display iterations
			System.out.print(input.charAt(i)+" ");
			System.out.print("[ ");
			for(int j=0;j<frameSize;j++){
				System.out.print(page[j]);
				if(j<frameSize-1){
					System.out.print(" , ");
				}
			}
			System.out.print(" ]");
			if(fault==1){
				System.out.println(" * " );
			}
			else{
				System.out.println();
			}
		}
	}

	void display(){
		System.out.println("no of page faults : " + pageFaultCount);
	}
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		Page obj=new Page();
		int ch=-1,choice=-1;
		while(ch==-1){
			System.out.println("1. LRU\n2. Optimal\n3. Exit");
			choice=sc.nextInt();
			switch(choice){
				case 1:
					obj.get();
					obj.LRU();
					obj.display();
					break;
				case 2:
					obj.get();
					obj.optimal();
					obj.display();
				break;
				default:
					ch=0;
					break;
			}
		}



	}


}
//----INPUT------
//70123034230321201701

/*
 * 1. LRU
2. Optimal
3. Exit
1
enter frame size
3
enter input string
70123034230321201701
7 [ 7 , - , - ] *
0 [ 7 , 0 , - ] *
1 [ 7 , 0 , 1 ] *
2 [ 2 , 0 , 1 ] *
3 [ 2 , 3 , 1 ] *
0 [ 2 , 3 , 0 ] *
3 [ 2 , 3 , 0 ]
4 [ 4 , 3 , 0 ] *
2 [ 4 , 3 , 2 ] *
3 [ 4 , 3 , 2 ]
0 [ 0 , 3 , 2 ] *
3 [ 0 , 3 , 2 ]
2 [ 0 , 3 , 2 ]
1 [ 1 , 3 , 2 ] *
2 [ 1 , 3 , 2 ]
0 [ 1 , 0 , 2 ] *
1 [ 1 , 0 , 2 ]
7 [ 1 , 0 , 7 ] *
0 [ 1 , 0 , 7 ]
1 [ 1 , 0 , 7 ]
no of page faults : 12
1. LRU
2. Optimal
3. Exit
2
enter frame size
3
enter input string
70123034230321201701
7 [ 7 , - , - ] *
0 [ 7 , 0 , - ] *
1 [ 7 , 0 , 1 ] *
2 [ 2 , 0 , 1 ] *
3 [ 2 , 0 , 3 ] *
0 [ 2 , 0 , 3 ]
3 [ 2 , 0 , 3 ]
4 [ 2 , 4 , 3 ] *
2 [ 2 , 4 , 3 ]
3 [ 2 , 4 , 3 ]
0 [ 2 , 0 , 3 ] *
3 [ 2 , 0 , 3 ]
2 [ 2 , 0 , 3 ]
1 [ 2 , 0 , 1 ] *
2 [ 2 , 0 , 1 ]
0 [ 2 , 0 , 1 ]
1 [ 2 , 0 , 1 ]
7 [ 7 , 0 , 1 ] *
0 [ 7 , 0 , 1 ]
1 [ 7 , 0 , 1 ]
no of page faults : 9
1. LRU
2. Optimal
3. Exit
3
*/
