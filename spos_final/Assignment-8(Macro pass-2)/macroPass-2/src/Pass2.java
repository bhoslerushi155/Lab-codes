import java.io.*;
import java.util.ArrayList;

public class Pass2 {
    static ArrayList<String> IC;
    static ArrayList<String[]> ALA;
    static ArrayList<String> MDT;
    static ArrayList<String> MNT;
    static ArrayList<String> OUT;

    public static void main(String[] args) {
        ALA=new ArrayList<String[]>();
        IC=new ArrayList<String>();
        MDT=new ArrayList<String>();
        MNT=new ArrayList<String>();
        OUT=new ArrayList<String>();

        Pass2 obj=new Pass2();
        obj.read();
        obj.convert();
        obj.display();
        obj.writeFile();
    }

    public void convert(){
        String argList=new String("");
        String[] list={};
        int index=-1;
        String in=new String("");
        for(String line:IC){

            String[] token=line.trim().split(" ");
            if((index=has(MNT,token[0]))!=-1){
//                for(String ln[]:ALA){
//                    if(ln[0].startsWith(token[0])){
//                        list=ln;
//                    }
//                }

                for(int i=index+1;!(in=MDT.get(i)).contains("MEND");i++){
                    String out=new String("");
                    int flag=0;
                    for(String tkn:in.split(" ")){
                        if(flag==0){
                            flag=1;
                            continue;
                        }
                        if(tkn.startsWith("#")){
                            int k=Integer.parseInt(tkn.substring(1));
                            out+=token[k];
                            out+=" ";
                        }
                        else{
                          out+=tkn;
                          out+=" ";
                        }

                    }
                    OUT.add(out);
                }
            }
            else{
                OUT.add(line);
            }
        }

    }
    public int has(ArrayList<String> mnt,String name){
        for(String line:mnt){
            if(line.trim().startsWith(name)) {
                return Integer.parseInt(line.trim().split(" ")[1]);
            }
        }
        return -1;
    }
    public void display(){
        System.out.println("********OUTPUT***********\n");
        for(String line:OUT){
            System.out.println(line);

        }
    }
    public void read(){
        String path="src/ic.txt";
        String line;
        try {
            BufferedReader br =new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){
                IC.add(line);
            }
            br.close();

            path="src/argList.txt";
            br =new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){
                String[] tmp=line.trim().split(" ");
                ALA.add(tmp);
            }
            br.close();

            path="src/mnt.txt";
            br =new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){
                MNT.add(line);
            }
            br.close();

            path="src/mdt.txt";
            br =new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){
                MDT.add(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeFile(){
        FileOutputStream fo= null;
        try {
            fo = new FileOutputStream("src/out.txt");
            for(String line:OUT){
                line+="\n";
                fo.write(line.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
