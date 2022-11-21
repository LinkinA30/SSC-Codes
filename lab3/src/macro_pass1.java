import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Files;

public class macro_pass1{
	public static void main(String[] args) {
		
		
		FileReader file;
		try {
			file = new FileReader("C:\\Users\\Sakshi\\eclipse-workspace\\lab3\\src\\input.txt");
			BufferedReader brFile = new BufferedReader(file);
			String curr_str = brFile.readLine();
//			using array list data struct for MNT MDT ALA
			List<String> MDT = new ArrayList<String>();
			List<String> MNT = new ArrayList<String>();
			List<String> ALA = new ArrayList<String>();
			List<Integer> mntToMdt = new ArrayList<Integer>();
			
			FileWriter outputWriter = new FileWriter("C:\\Users\\Sakshi\\eclipse-workspace\\lab3\\src\\output.txt");
			System.out.println("OUTPUT CODE: ");
			while(curr_str != null) {
				String tokens[] = curr_str.split(" "); 
				if(tokens[0].contains("MACRO")) 
				{
					curr_str = brFile.readLine();
					String macroFirstLine[] = curr_str.split(" ");
//					MDT
					MDT.add(" " + curr_str);
//					MNT
					if(!macroFirstLine[0].contains("&"))
					{
						MNT.add(macroFirstLine[0]);						
					} else if(!macroFirstLine[1].contains("&")) {
						MNT.add(macroFirstLine[1]);	
					}
					mntToMdt.add(MDT.size()-1);
					
//					ALA
//					for loop to check which macro token contains & then add it to ALA
					for(int i=0; i< macroFirstLine.length; i++) 
					{
						if(macroFirstLine[i].contains("&"))
						{
//							add to ALA
							ALA.add(macroFirstLine[i]);
						}
					}
					
					curr_str = brFile.readLine();
					
					while(true)
					{
						if(curr_str.contains("MEND"))
						{
							MDT.add(" MEND");
							break;
						}
						String macroTokens[] = curr_str.split(" ");
						List<String> mdtLineArray = new ArrayList<String>();
						
						for(int i=0; i<macroTokens.length; i++)
						{
							int alaIndex = -1;
							if(macroTokens[i].contains("&"))
							{
								for(int j=0; j<ALA.size(); j++)
								{
									if(ALA.get(j).contains(macroTokens[i]))
									{
										mdtLineArray.add("#" + String.valueOf(j));
										alaIndex = j;
										break;
									}
									
								}
							} else {
								mdtLineArray.add(macroTokens[i]);
							}
						}
						String mdtLine = "";
						for(int i=0; i<mdtLineArray.size(); i++)
						{

							mdtLine = mdtLine + " " + mdtLineArray.get(i);
						}
						MDT.add(mdtLine);

						curr_str = brFile.readLine();
					}	
				}
				
				if(!curr_str.contains("MEND"))
				{
					System.out.println(curr_str);
//					writing output Code file for pass2
//					outputFile
					
					outputWriter.write(curr_str+"\n");
					
				}
				curr_str = brFile.readLine();
			}


			
		
//			write file
			FileWriter mdtWriter = new FileWriter("C:\\Users\\Sakshi\\eclipse-workspace\\lab3\\src\\mdt.txt");
			//display mdt 
			System.out.println("\n----MDT----");
			for(int i=0; i<MDT.size(); i++)
			{			
				System.out.println(i + "     "+MDT.get(i));	
				mdtWriter.write(i+MDT.get(i)+"\n");
			}
			
			FileWriter mntWriter = new FileWriter("C:\\Users\\Sakshi\\eclipse-workspace\\lab3\\src\\mnt.txt");
			//display mnt 
			System.out.println("\n----MNT----");
			for(int i=0; i<MNT.size(); i++)
			{
				System.out.println(i +"     "+ MNT.get(i) + "     " + mntToMdt.get(i));
				mntWriter.write(i+" "+ MNT.get(i)+ " "+ mntToMdt.get(i) +"\n");
			}
			
			FileWriter alaWriter = new FileWriter("C:\\Users\\Sakshi\\eclipse-workspace\\lab3\\src\\ala.txt");
			//display ala
			System.out.println("\n----ALA----");
			for(int i=0; i<ALA.size(); i++)
			{
				System.out.println( "#"+i + "    "+ ALA.get(i));
				alaWriter.write("#" + i + " " + ALA.get(i)+"\n");
			}
						
		
			//closed all files		
			outputWriter.close();
			mdtWriter.close();
			mntWriter.close();
			alaWriter.close();
	
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
