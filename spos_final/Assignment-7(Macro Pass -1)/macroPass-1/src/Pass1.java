import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class Pass1 {
    static ArrayList<String> SC;
    static ArrayList<String> IC;
    static ArrayList<String> MDT;
    static ArrayList<String> MNT;
    static ArrayList<String> argList;
    static int mdtPtr,mntPtr;

    public static void main(String[] args) throws Exception {
        SC=new ArrayList<String>();
        IC=new ArrayList<String>();
        MDT=new ArrayList<String>();
        MNT=new ArrayList<String>();
        argList=new ArrayList<String>();
        mdtPtr=mntPtr=0;
        Pass1 obj=new Pass1();
        obj.readFile();
        obj.convert();
        obj.display();
       obj.writeFile();
    }

    public void convert(){
        String line;
        for(int i=0;i<SC.size();i++){
            line=SC.get(i);
            line=line.trim();
            if(line.equals("MACRO")){
                i++;
                line=SC.get(i);
                line=line.trim();
                argList.add(line);
                String arg[],temp;
                arg=line.split(" ");
                temp=new String(arg[0]+" "+String.valueOf(mdtPtr));
                MNT.add(temp);
                mntPtr++;
                do {
                    line=SC.get(i);
                    line=line.trim();
                    i++;
                    String one=new String("");
                    int t;
                    String[] token={};
                    token=line.split(" ");
                    for(int j=0;j<token.length;j++){
                        if((t=has(arg,token[j]))!=-1){
                            one+="#"+String.valueOf(t)+" ";
                        }
                        else{
                            one+=token[j]+" ";
                        }
                    }
                    MDT.add(String.valueOf(mdtPtr++)+" "+one);
                }while(!line.trim().equals("MEND"));
                i--;
            }
            else{
                IC.add(line);
            }
        }
    }
    public int has(String[] arg,String par){
        for(int i=1;i<arg.length;i++){
            if(arg[i].trim().equals(par.trim())) {
                return i;
            }
            i++;
        }
        return -1;
    }
 public void writeFile() throws Exception{
        String path="src/ic.txt";
        FileOutputStream fo=new FileOutputStream(path);
        for(String line:IC){
            line+="\n";
            fo.write(line.getBytes());
        }
        fo.close();

        path="src/mdt.txt";
        fo=new FileOutputStream(path);
        for(String line:MDT){
            line+="\n";
            fo.write(line.getBytes());
        }
        fo.close();

        path="src/mnt.txt";
        fo=new FileOutputStream(path);
        for(String line:MNT){
            line+="\n";
            fo.write(line.getBytes());
        }
        fo.close();

        path="src/argList.txt";
        fo=new FileOutputStream(path);
        for(String line:argList){
            line+="\n";
            fo.write(line.getBytes());
        }
        fo.close();
    }
    public void display(){
        System.out.println("***********MDT**********");
        for(String temp:MDT){
            System.out.println(temp);
        }
        System.out.println("***********MNT**********");
        for(String temp:MNT){
            System.out.println(temp);
        }
        System.out.println("***********ArgList**********");
        for(String temp:argList){
            System.out.println(temp);
        }
        System.out.println("***********IC**********");
        for(String temp:IC){
            System.out.println(temp);
        }
    }

    public void readFile(){
        String path="src/source.asm";
        String line;
        try {
            BufferedReader br =new BufferedReader(new FileReader(path));
            while((line=br.readLine())!=null){
                SC.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
