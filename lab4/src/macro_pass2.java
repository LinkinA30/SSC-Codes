import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class macro_pass2 {
	public static void main(String[] args) throws IOException {
		List<String> MDT = new ArrayList<String>();
		List<String> MNT = new ArrayList<String>();
		List<Integer> mntToMdt = new ArrayList<Integer>();
		//using 2 array list to store ALA value
		List<String> ALA = new ArrayList<String>();
		List<String> alaValue = new ArrayList<String>();
//		MDT
		FileReader mdtfile;
		mdtfile = new FileReader("C:\\Users\\Sakshi\\eclipse-workspace\\lab3\\src\\mdt.txt");
		
		BufferedReader brMdtFile = new BufferedReader(mdtfile);
		String curr_str = brMdtFile.readLine();
		//curr_str = brMdtFile.readLine();
		
		while(curr_str != null) {
			String mdtTokens[] = curr_str.split(" ");
			String mdtLine = "";
			for(int i=1;i<mdtTokens.length;i++) {
				mdtLine = mdtLine + mdtTokens[i] + " ";
			}
			MDT.add(mdtLine);
			curr_str = brMdtFile.readLine();
		}
		brMdtFile.close();
		
		
//		MNT
		FileReader mntfile;
		mntfile = new FileReader("C:\\Users\\Sakshi\\eclipse-workspace\\lab3\\src\\mnt.txt");
		
		BufferedReader brMntFile = new BufferedReader(mntfile);
		curr_str = brMntFile.readLine();
		
		while(curr_str != null) {
			String mntTokens[] = curr_str.split(" ");
			MNT.add(mntTokens[1]);
			mntToMdt.add(Integer.parseInt(mntTokens[2]));

			curr_str = brMntFile.readLine();
		}
		brMntFile.close();
		
//		ALA
		FileReader alafile;
		alafile = new FileReader("C:\\Users\\Sakshi\\eclipse-workspace\\lab3\\src\\ala.txt");
		
		BufferedReader brAlaFile = new BufferedReader(alafile);
		curr_str = brAlaFile.readLine();
		
		while(curr_str != null) {
			String alaTokens[] = curr_str.split(" ");
			ALA.add(alaTokens[1]);
			alaValue.add("");

			curr_str = brAlaFile.readLine();
		}
		brAlaFile.close();
		
//		input = output of pass1
		FileReader inputfile;
		inputfile = new FileReader("C:\\Users\\Sakshi\\eclipse-workspace\\lab3\\src\\output.txt");
		
		BufferedReader brinputFile = new BufferedReader(inputfile);
		curr_str = brinputFile.readLine();

		
//		Macro pass 2 Output file
		FileWriter outputWriter = new FileWriter("C:\\Users\\Sakshi\\eclipse-workspace\\lab4\\src\\output.txt");
		System.out.println("\nMACRO PASS 2 OUTPUT\n");
		while(curr_str != null) {
			String inputTokens[] = curr_str.split(" ");
			if(MNT.contains(inputTokens[0])) {
				
//				get mnt index then get the actual MDT index from mntToMdt array
				int MntIndex = MNT.indexOf(inputTokens[0]);
				int MdtIndex = mntToMdt.get(MntIndex);
				int finalValue;
				// if left value is greater means there are more macros defined after current 
				// macro else it is the last macro
				if(mntToMdt.size()<= MntIndex+1)
				{
					finalValue = MDT.size();					
				} else {
					finalValue = mntToMdt.get(MntIndex+1);
				}
				// processing macros first line seperately
				String mdtFirstLine = MDT.get(MdtIndex);
				String mdtFirstLineTokens[] = mdtFirstLine.split(" ");

				for(int i=0; i<mdtFirstLineTokens.length; i++)
				{
					if(mdtFirstLineTokens[i].contains("&"))
					{
						int AlaIndex = ALA.indexOf(mdtFirstLineTokens[i]);
						String valueForAla = inputTokens[i];
						alaValue.add(AlaIndex, valueForAla);

					}
				}

				// macro left over
				int i = MdtIndex;
				while(true) {
					i++;
					String macroLine = MDT.get(i);
					
					if(macroLine.contains("MEND")) {
						break;
					}
					String mdtTokens[] = macroLine.split(" ");
					
					//	write value of ALA and skip the writing to the file part
					String outPutLine = "";
					for(int j=0; j<mdtTokens.length; j++) {
						if(mdtTokens[j].contains("#"))
						{
							String mdtAlaToken[] = mdtTokens[j].split("#");
							int indexOfAlaToken = Integer.parseInt(mdtAlaToken[1]);
							outPutLine = outPutLine + alaValue.get(indexOfAlaToken) + " ";	
							
							
						} else {
							outPutLine = outPutLine + mdtTokens[j] + " ";
						}
					}
					
					System.out.println(outPutLine);
					outputWriter.write("+ "+outPutLine+"\n");
				}
			} else {
				System.out.println(curr_str);
				outputWriter.write(curr_str+"\n");
				
			}
			curr_str = brinputFile.readLine();
		}
		outputWriter.close();
		brinputFile.close();
	}
}
