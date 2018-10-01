import java.io.*;
import java.util.ArrayList;

public class Pass {
    String fname="src/MOT.txt";
    static ArrayList<String[]> MOT;
    static ArrayList<String[]> ST;
    static ArrayList<String[]> LT;
    static ArrayList<Integer> PT;
    static ArrayList<String> IC;
    int stPtr=0,ltPtr=0,locCtr;


    public static void main(String[] args) {
        Pass obj=new Pass();
        MOT=new ArrayList<String[]>();
        ST=new ArrayList<String[]>();
        LT=new ArrayList<String[]>();
        PT=new ArrayList<Integer>();
        IC=new ArrayList<String>();
        MOT=obj.readMOT();
        obj.convert();
        obj.display();
        try {
            obj.writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void convert(){
        String path="src/asm.txt";
        try {
            int h=-1,pre=-1;
            String line="";
            String arr[]={};
            String intermediate;
            BufferedReader br =new BufferedReader(new FileReader(path));
            do{
                line=br.readLine();
                h=-1;
                arr=line.split(" ");
                intermediate="";
                intermediate+=String.valueOf(locCtr) + " ";
                for(int i=0;i<arr.length;i++){

                    if(arr[i].equals("START")){
                        PT.add(0);
                        int t=this.has(arr[i]);
                        intermediate+=" ("+MOT.get(t)[1]+","+MOT.get(t)[2]+") ";
                        locCtr=0;
                        if(arr.length>1){
                            locCtr=Integer.parseInt(arr[1]);
                        }
                    }

                    else if(arr[i].equals("ORIGIN")){
                        if(arr[i].startsWith("$")){
                            String t=arr[i].substring(1);
                            int in=Integer.parseInt(t);
                            locCtr=in;
                        }
                    }

                    else if(arr[i].startsWith("=")){
                        intermediate+= " (L,"+ ltPtr+") ";
                        String[] holder={arr[i],""};
                        LT.add(holder);
                        ltPtr++;
                    }

                    else if(arr[i].equals("LTORG") || arr[i].equals("END")){
                        int t=this.has(arr[i]);
                        intermediate+=" ("+MOT.get(t)[1]+","+MOT.get(t)[2]+") ";
//                        if(arr[i].equals("LTORG")) {
                               PT.add(ltPtr);
//                        }
                        String[] a={};
                        int siz=PT.size();
                        int si=LT.size();
                        for(int k=PT.get(siz-2);k<si;k++){
                            a=LT.get(k);
                            a[1]= String.valueOf(locCtr++);
                            LT.remove(k);
                            LT.add(k,a);
                        }
                    }

                    else if(arr[i].endsWith(":")){
                        String[] ar={arr[i].substring(0,arr[i].length()-1) , String.valueOf(locCtr)};
                        ST.add(ar);
                    }
                    else if((h=this.has(arr[i]))!=-1){
                        intermediate+=" ("+MOT.get(h)[1]+","+MOT.get(h)[2]+") ";
                        String[] ar={};
                        ar=MOT.get(h);
                        if(!MOT.get(h)[3].equals("-") ){
                            locCtr+=Integer.parseInt(MOT.get(h)[3]);
                        }
                    }
                    else if(arr[i].equals(",")){
                        continue;
                    }
                    else{
                        try{
                            int cnst=Integer.parseInt(arr[i]);
                            intermediate+=" (C,"+cnst+") ";
                        }
                        catch (Exception e){
                            if(arr.length > i+1 && (arr[i+1].equals("DS") || arr[i+1].equals("DC"))){
//                                locCtr++;
                                String[] ar={arr[i] , String.valueOf(locCtr)};
                                ST.add(ar);

                            }
                            else{
                                intermediate+=" (S,"+stPtr+") ";

                            }
//                            else{
//                                String[] ar={arr[i] ,"-"};
//                                ST.add(ar);
//                            }

                        }


                    }
                }


//                locCtr++;
                IC.add(intermediate);
            }while(!line.equals("END"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void display(){
         System.out.println("--------------LT------------");
         for(String[] str:LT){
             for(String st:str){
                 System.out.print(st +"\t");
             }
             System.out.print("\n");
         }

        System.out.println("--------------ST------------");
        for(String[] str:ST){
            for(String st:str){
                System.out.print(st +"\t");
            }
            System.out.print("\n");
        }

        System.out.println("--------------PT------------");
        for(int in:PT){
            System.out.println(in +"\t");
        }

        System.out.println("--------------IC------------");
        for(String in:IC){
            System.out.println(in +"\t");
        }

    }
    public ArrayList<String[]> readMOT(){
        ArrayList<String[]> temp=new ArrayList<String[]>();
        try {
            String tmp=null;
            String arr[]={};
            BufferedReader br =new BufferedReader(new FileReader(fname));
            while((tmp=br.readLine())!=null){
                arr=tmp.split(" ");
                temp.add(arr);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int has(String ab){
        int i=0;
        for(String[] arr: MOT){
            if(arr[0].equals(ab)){
                return i;
            }
            i++;
        }
        return -1;
    }

    public void writeToFile() throws IOException {
        String path="src/literal.txt";
        FileOutputStream fo=new FileOutputStream(path);
        for(String[] str:LT){
            for(String st:str){
                fo.write(st.getBytes());
                fo.write(" ".getBytes());
            }
           fo.write("\n".getBytes());
        }
        fo.close();

        path="src/symbolTable.txt";
        fo=new FileOutputStream(path);

        for(String[] str:ST){
            for(String st:str){
                fo.write(st.getBytes());
                fo.write(" ".getBytes());
            }
            fo.write("\n".getBytes());
        }
        fo.close();

        path="src/pool.txt";
        fo=new FileOutputStream(path);

        for(int in:PT){
            String s=String.valueOf(in);
            s+="\n";
            fo.write(s.getBytes());
        }
        fo.close();

        path="src/intermediate.txt";
        fo=new FileOutputStream(path);

        for(String in:IC){
            fo.write(in.getBytes());
            fo.write("\n".getBytes());
        }
        fo.close();
    }
}