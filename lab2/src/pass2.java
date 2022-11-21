import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class pass2{
	public static void main(String[] args) throws Exception {
    
        FileOutputStream file = new FileOutputStream("C:\\Users\\Sakshi\\eclipse-workspace\\lab2\\src\\output.txt");
        System.setOut(new PrintStream(file));

        FileInputStream fis = new FileInputStream("C:\\Users\\Sakshi\\eclipse-workspace\\lab2\\src\\input.txt");
        Scanner scanner = new Scanner(fis);

        List<List<String>> result = new ArrayList<>();
        Map<String,String> symbolTable = new HashMap<>();
        boolean ICEnc = false;

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] lineArray = line.split(" ");
            List<String> currLine = new ArrayList<>();

            if(line.equals("INSTRUCTION CODE TABLE")){
                ICEnc = true;
                continue;
            }

            if(ICEnc == false){
                symbolTable.put(lineArray[0], lineArray[2]);
          
                
            }else{
                if(lineArray[0].equals("---")) continue;
                else{
     
                    currLine.add(lineArray[0]);
                    currLine.add(lineArray[2]);
                    currLine.add(lineArray[3]);

                    if(lineArray[4].equals("S")){
                        currLine.add(symbolTable.get(lineArray[5]));
                    }else if(lineArray[4].equals("C")){
                        currLine.add(lineArray[5]);
                    }else{
                        currLine.add("0");
                    }
                }
                result.add(currLine);
            }


        }

        for(List<String> list :result){
            System.out.println(list);
        }

        scanner.close();
    }

    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
    
        return true;
    }
}
