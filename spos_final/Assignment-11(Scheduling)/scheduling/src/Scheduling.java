import org.omg.CORBA.INTERNAL;

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
                    obj.SJF();
                    obj.dispPremptive();
                    break;
                case 3:
                    obj.sort();
                    obj.priorityScheduling();
                    obj.disp();
                    break;
                case 4:
                    obj.RR();
                    obj.dispPremptive();
                    break;
                default:
                    flag=-1;
                    break;
            }
        }
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

    public void SJF(){
        System.out.println("enter quantum");
        int qnt=sc.nextInt();
        ArrayList<Integer> remaining=new ArrayList<Integer>();
        ArrayList<Integer> completed=new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            remaining.add(BT.get(i));
            completed.add(0);
        }
        int current=AT.get(0),count=0;

        while(count<n){
            int min=99999,index=-1;
            for(int i=0;i<n;i++){
                if(remaining.get(i)<=min  && completed.get(i)==0 && AT.get(i)<=current){
                    min=remaining.get(i);
                    index=i;
                }
            }

            if(index!=-1 && remaining.get(index)>qnt){
                ArrayList<Integer> tmp=new ArrayList<>();
                tmp.add(current);
                tmp.add(current+qnt);
                current+=qnt;
                int temp=remaining.get(index);
                temp-=qnt;
                remaining.remove(index);
                remaining.add(index,temp);
                slots.add(tmp);
                pname.add(process.get(index));
            }
            if(index!=-1 && remaining.get(index)<=qnt){
                ArrayList<Integer> tmp=new ArrayList<>();
                tmp.add(current);
                tmp.add(current+remaining.get(index));
                current+=remaining.get(index);
                remaining.remove(index);
                remaining.add(index,0);
                slots.add(tmp);
                pname.add(process.get(index));
                completed.set(index,1);
                count++;
            }
        }

    }

    public void RR(){
        System.out.println("enter quantum");
        int qnt=sc.nextInt();
        ArrayList<Integer> remaining=new ArrayList<Integer>();
        ArrayList<Integer> completed=new ArrayList<Integer>();
        ArrayList<Integer> pri=new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            remaining.add(BT.get(i));
            completed.add(0);
            pri.add(-1);
        }
        int current=AT.get(0),count=0;
        int ct=1;
        pri.remove(0);
        pri.add(0,ct);
        while(count<n){
            int min=99999,index=-1;
            for(int i=0;i<n;i++){
                if(pri.get(i)<=min  && completed.get(i)==0 && AT.get(i)<=current){
                    min=pri.get(i);
                    index=i;
                }
            }

            if(index!=-1 && remaining.get(index)>qnt){
                ArrayList<Integer> tmp=new ArrayList<>();
                tmp.add(current);
                tmp.add(current+qnt);
                current+=qnt;
                int temp=remaining.get(index);
                temp-=qnt;
                remaining.remove(index);
                remaining.add(index,temp);
                slots.add(tmp);
                pname.add(process.get(index));
            }
            else if(index!=-1 && remaining.get(index)<=qnt){
                ArrayList<Integer> tmp=new ArrayList<>();
                tmp.add(current);
                tmp.add(current+remaining.get(index));
                current+=remaining.get(index);
                remaining.remove(index);
                remaining.add(index,0);
                slots.add(tmp);
                pname.add(process.get(index));
                completed.set(index,1);
                count++;
            }

            for(int i=0;i<n;i++){
                if(pri.get(i)==-1 && AT.get(i)<=current){
                    pri.remove(i);
                    pri.add(i,++ct);
                }
            }
            if(remaining.get(index)>0){
                pri.remove(index);
                pri.add(index,++ct);
            }

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

    public void disp(){
        float wt=0,cwt=0,tt=0,ctt=0;
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
            wt=ft-AT.get(i)-BT.get(i);
            cwt+=wt;
            System.out.print(wt+"\t");
            tt=ft-AT.get(i);
            ctt+=tt;
            System.out.println(tt);

        }
        System.out.println("Average WT = "+cwt/n);
        System.out.println("Average TAT = "+ctt/n);
    }

    public void dispPremptive(){
        float wt=0,cwt=0,tt=0,ctt=0;
        System.out.println("******Slots and processes ******\nstart\tend\tprocess");
        int len=slots.size();
        for(int i=0;i<len;i++){
            System.out.print(slots.get(i).get(0)+"\t");
            System.out.print(slots.get(i).get(1)+"\t");
            System.out.println(pname.get(i)+"\t");


        }

        System.out.println("\n\nprocess\tST\tFT\tWT\tTAT");
        for(int i=0;i<n;i++){
            String pro=process.get(i);
            int ft=slots.get(pname.lastIndexOf(pro)).get(1);
            System.out.print(pro+"\t\t");
            System.out.print(slots.get(pname.indexOf(pro)).get(0)+"\t");
            System.out.print(ft+"\t");
            wt=ft-AT.get(i)-BT.get(i);
            cwt+=wt;
            System.out.print(wt+"\t");
            tt=ft-AT.get(i);
            ctt+=tt;
            System.out.println(tt);

        }

        System.out.println("Average WT = "+cwt/n);
        System.out.println("Average TAT = "+ctt/n);
    }
}



