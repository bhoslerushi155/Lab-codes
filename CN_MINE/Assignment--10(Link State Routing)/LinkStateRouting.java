import java.util.Scanner;

public class LinkStateRouting{

	int[][] mat,path;
	int n,v;
	int [] distance;

	public void getData(){
		System.out.println("Enter no of vertices :");
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		mat=new int[n][n];
		path=new int[n][n];
		distance=new int[n];
		for(int i=0;i<n;i++){
			distance[i]=-1;
			for(int j=0;j<n;j++){
				mat[i][j]=99999;
				path[i][j]=-1;
			}
		}
		int t,st,en;
		do{
			System.out.println("Enter starting vertex :");
			st=sc.nextInt();
			System.out.println("Enter end Vetrex :");
			en=sc.nextInt();
			System.out.println("Enter cost :");
			t=sc.nextInt();
			mat[st][en]=mat[en][st]=t;
			System.out.println("Add edge (1/0)?");
			t=sc.nextInt();
		}while(t==1);
	} 

	public void shortestPath(){
		distance=new int[n];
		Scanner sc=new Scanner(System.in);
		int visited[] =new int[n];
		int start;
		System.out.println("Enter start vertex :");
		start=sc.nextInt();
		
		for(int i=0;i<n;i++){
			distance[i]=mat[start][i];
			visited[i]=0;
			path[i][0]=start;
		}
		
		distance[start]=0;
		visited[start]=1;
		
		int current=choose(distance,visited);
		path[current][1]=current;
		for(int i=0;i<n-1;i++){

			current=choose(distance,visited);
			visited[current]=1;
			
			for(int j=0;j<n;j++){
				if(visited[j]==0 && (distance[current]+mat[current][j] < distance[j])){	
					distance[j]=distance[current]+mat[current][j];
					int k;
					for(k=0;k<n ;k++){
						path[j][k]=path[current][k];
					}
					path[j][k]=j;
				}
				
			}

		}
	}

	public int choose(int[] distance,int[] visited){
		int min=99999,ret=-1;
		for(int i=0;i<n;i++){
			if(visited[i]==0){
				if(distance[i]<min){
					min=distance[i];
					ret=i;
				}
			}
		}
		return ret;
	}
	public void display(){
		for(int i=0;i<n;i++){
			System.out.println("\nDistance of vertex "+i+" from start is "+distance[i]);
			System.out.print("shortest path is : ");
			int j=0;
			while(path[i][j]!=-1){
				System.out.print(path[i][j]);
				if(path[i][j+1]!=-1){
					System.out.print("-->");
				}
				j++;
			}
			System.out.println();
		}
	}

	public static void main(String[] args){
		int choice,ch=0;
		LinkStateRouting obj=new LinkStateRouting();
		Scanner sc=new Scanner(System.in);
		while(ch!=-1){	
			System.out.println("1. Enter data\n2. Display Shortest path\n3. Exit");
			choice=sc.nextInt();
			switch(choice){
				case 1:
					obj.getData();
				break;
				case 2:
					obj.shortestPath();
					obj.display();
				break;
				default:
					ch=-1;
				break;
			}
		}
	}
}