import java.util.Scanner;

public class Bankers {
    int nr,np;
    int allocation[][],max[][];
    int available[];
    String name[];
    int finish[];
    static int count;

    public static void main(String[] args){
        Bankers obj=new Bankers();
        obj.np=0;
        obj.nr=0;
        count=0;
        obj.get();
        obj.banker();
    }


    public void banker(){
        int seq[]=new int[np];
        int index=-1;
        for(int i=0;i<np;i++){
            finish[i]=0;
        }
        while(count<np && (index=check())!=-1){
            seq[count]=new Integer(index);
            count++;
            finish[index]=1;
            for(int i=0;i<nr;i++){
                available[i]+=allocation[index][i];
                allocation[index][i]=0;
                max[index][i]=0;
            }

        }
        if(count<np){
            System.out.println("System is not in safe state ....");
        }
        else{
            System.out.println("System is in safe state ....");
            System.out.println("Safe sequence is as follows");
            for(int i=0;i<np;i++){
                System.out.println(name[seq[i]]);
            }
        }
    }

    public int check(){
        int need[]=new int[nr],j,flag=0;
        for(int i=0;i<np;i++){
            if(finish[i]==0){
                flag=0;
                for(j=0;j<nr;j++){
                    need[j] = max[i][j] - allocation[i][j];
                    if(need[j]>available[j]){
                        flag=1;
                    }
                }
                if(flag==0){
                    return i;
                }

            }
        }
        return -1;
    }

    public void get(){
        Scanner sc;
        sc=new Scanner(System.in);
        System.out.println("Enter no of processes ");
        np=sc.nextInt();
        System.out.println("Enter no of resource types ");
        nr=sc.nextInt();

        allocation=new int[np][nr];
        max=new int[np][nr];
        available=new int[nr];
        name=new String[np];
        finish=new int[np];
        int temp[]=new int[nr];

        System.out.println("Enter name of processes");
        for(int i=0;i<np;i++){
            name[i]=sc.next();
        }

        System.out.println("Enter allocation matrix");
        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                allocation[i][j]=sc.nextInt();
            }
        }

        System.out.println("Enter max matrix");
        for(int i=0;i<np;i++){
            for(int j=0;j<nr;j++){
                max[i][j]=sc.nextInt();
            }
        }

        System.out.println("Enter max resources matrix");
        for(int i=0;i<nr;i++){
            temp[i]=sc.nextInt();
            int t=0;
            for(int j=0;j<np;j++){
                t+=allocation[j][i];
            }
            available[i]=temp[i]-t;

        }

    }
}
