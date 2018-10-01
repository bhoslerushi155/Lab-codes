import java.io.*;
import java.util.ArrayList;

public class Pass2 {
    static ArrayList<String> MC;
    static ArrayList<String> IC;
    static ArrayList<String> ST;
    static ArrayList<String> LT;

    public static void main(String[] args) {
        MC=new ArrayList<String>();
        IC=new ArrayList<String>();
        ST=new ArrayList<String>();
        LT=new ArrayList<String>();
        Pass2 obj=new Pass2();
        try {
            obj.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        obj.convert();
        obj.display();
        try {
            obj.WriteFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void display(){
        System.out.println("*********** Machine Code ****************\n");
        for(String line:MC){
            System.out.println(line);
        }
    }
    public void WriteFile() throws IOException {
        String path="src/machine.txt";
        FileOutputStream fo=new FileOutputStream(path);
        for(String line:MC){
            fo.write(line.getBytes());
            fo.write( "\n".getBytes());
        }
    }
    public void convert(){
        String sp[];
        for(String line :IC){
            String machine=" ";
            sp=line.split(" ");
            if(line.contains("AD")){
                continue;
            }
            for(String one:sp){
                if(one.startsWith("(IS") || one.startsWith("(RG") || one.startsWith("(DL") || one.startsWith("(CC")){
                    String sub=one.substring(one.indexOf(",")+1 , one.length()-1);
                    machine+=sub+" ";
                }
                else if(one.startsWith("(S")){
                    String sub=one.substring(one.indexOf(",")+1 , one.length()-1);
                    int index=Integer.parseInt(sub);
                    String addr=(ST.get(index).split(" "))[1];
                    machine+=addr+" ";
                }
                else if(one.startsWith("(L")){
                    String sub=one.substring(one.indexOf(",")+1 , one.length()-1);
                    int index=Integer.parseInt(sub);
                    String addr=(LT.get(index).split(" "))[1];
                    machine+=addr+" ";
                }
                else if(one.startsWith("(C")){
                    String sub=one.substring(one.indexOf(",")+1 , one.length()-1);
                    int cst=Integer.parseInt(sub);
                    machine+=cst+" ";
                }
                else{
                    machine+=one+" ";
                }

            }
            MC.add(machine);

        }
    }

    public void readFile() throws IOException {
        String path= "src/intermediate.txt";
        String line=new String();
        BufferedReader br= new BufferedReader(new FileReader(path));
        while((line=br.readLine())!=null){
            IC.add(line);
        }
        br.close();

        path="src/symbolTable.txt";
        br=new BufferedReader(new FileReader(path));
        while((line=br.readLine())!=null){
            ST.add(line);
        }
        br.close();

        path="src/literal.txt";
        br=new BufferedReader(new FileReader(path));
        while((line=br.readLine())!=null){
            LT.add(line);
        }
        br.close();
    }
}
