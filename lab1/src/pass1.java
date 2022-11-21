import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.*;
public class pass1 {

public static void main(String[] args) {
	try {
		HashMap<String, String> is_sc = new HashMap<>();
		is_sc.put("STOP", "IS");
		is_sc.put("ADD", "IS");
		is_sc.put("SUB", "IS");
		is_sc.put("MULT", "IS");
		is_sc.put("MOVER", "IS");
		is_sc.put("MOVEM", "IS");
		is_sc.put("COMP", "IS");
		is_sc.put("BC", "IS");
		is_sc.put("DIV", "IS");
		is_sc.put("READ", "IS");
		is_sc.put("PRINT", "IS");
		is_sc.put("DC", "DL");
		is_sc.put("DS", "DL");
		is_sc.put("START", "AD");
		is_sc.put("END", "AD");
		is_sc.put("ORIGIN", "AD");
		is_sc.put("EQU", "AD");
		is_sc.put("LTORG", "AD");
		
		HashMap<String, Integer> is_mc = new HashMap<>();
		is_mc.put("STOP", 0);
		is_mc.put("ADD", 1);
		is_mc.put("SUB", 2);
		is_mc.put("MULT", 3);
		is_mc.put("MOVER", 4);
		is_mc.put("MOVEM", 5);
		is_mc.put("COMP", 6);
		is_mc.put("BC", 7);
		is_mc.put("DIV", 8);
		is_mc.put("READ", 9);
		is_mc.put("PRINT", 10);
		is_mc.put("DC", 1);
		is_mc.put("DS", 2);
		is_mc.put("START", 1);
		is_mc.put("END", 2);
		is_mc.put("ORIGIN", 3);
		is_mc.put("EQU", 4);
		is_mc.put("LTORG", 5);
		
		HashMap<String, Integer> rg_tb = new HashMap<>();
		rg_tb.put("AREG", 1);
		rg_tb.put("BREG", 2);
		rg_tb.put("CREG", 3);
		rg_tb.put("DREG", 4);
		
		HashMap<String, Integer> cd_code = new HashMap<>();
		cd_code.put("LT", 1);
		cd_code.put("LE", 2);
		cd_code.put("EQ", 3);
		cd_code.put("GT", 4);
		cd_code.put("GE", 5);
		cd_code.put("NE", 6);
		
		FileReader file = new FileReader("C:\\Users\\Sakshi\\eclipse-workspace\\lab1\\src\\input.txt");
		BufferedReader input = new BufferedReader(file);
		
		String curr_str = input.readLine();
		Integer lc = 0;
		HashMap<String, Integer> symb_addr = new HashMap<>();
		HashMap<String, Integer> symb_index = new HashMap<>();
		HashMap<Integer, String> index_symb = new HashMap<>();
		System.out.println("\nINSTRUCTION CODE TABLE\n");
		System.out.println("");
		Integer curr_symb_index = 1;
		while (curr_str != null) {
			String[] tokens = curr_str.split(" ");
			int i=0;
			//Check for label
			String sc = is_sc.get(tokens[i]);
			Integer mc = is_mc.get(tokens[i]);
			Integer operand1 = 0;
			boolean operand2_present = false;
			boolean is_operand2_symb = false;
			Integer operand2_constant = null;
			Integer operand2_symb_index = null;
			String current_label = null;
			String operand2_symb = null;
			if(sc==null) { //label found
				current_label = tokens[i];
				Integer idx = symb_index.get(tokens[i]);
			if(idx==null) {
				symb_index.put(tokens[i], curr_symb_index);
				index_symb.put(curr_symb_index, tokens[i]);
				symb_addr.put(tokens[i], lc);
				curr_symb_index++;
		}
			else symb_addr.replace(tokens[i], lc);
			i++;
			sc = is_sc.get(tokens[i]);
			mc = is_mc.get(tokens[i]);
		}
		
		i++;
		//Instruction Done
		
		//Handle 1st Operand
		if(i+1 < tokens.length){
			Integer rg_code = rg_tb.get(tokens[i]);//check for register
			if(rg_code!=null) {
			operand1 = rg_code;
		}
		else if (sc=="IS" && mc==7) { //Check for BC instruction
		//find condition code
			operand1 = cd_code.get(tokens[i]);
			}
			i++;
		}
		
		//Handle Second Operand
		if(i < tokens.length) {
		operand2_present = true;
		// Check if constant
		try {
		   Integer constant = Integer.parseInt(tokens[i]);
		   operand2_constant = constant;
	   
	} 
		catch (NumberFormatException e) {
		//Symbol Found
			is_operand2_symb = true;
			operand2_symb = tokens[i];
			operand2_symb_index = symb_index.get(tokens[i]);
			if(operand2_symb_index==null) {
				symb_index.put(tokens[i], curr_symb_index);
				index_symb.put(curr_symb_index, tokens[i]);
				symb_addr.put(tokens[i], -1);
				operand2_symb_index = curr_symb_index;
				curr_symb_index++;
		
		}
		}
		}
		if(sc == "AD") {
			//Handle STRAT and ORIGIN
			if(operand2_constant != null  && (mc==1 || mc==3)) lc = operand2_constant;
			if(mc == 4) { //Handle EQU
				Integer addr = symb_addr.get(operand2_symb);
				symb_addr.replace(current_label, addr);
		}
			System.out.print("---");
		}
		else {
			System.out.print(lc);
			lc++;
		}
		if(sc == "DL" && mc==2) {
			lc+=operand2_constant-1;
		}
		System.out.print(" (" + sc + "," + mc + ") " + operand1);
		if(operand2_present) {
			if(is_operand2_symb) System.out.print(" (S, " + operand2_symb_index + ")");
			else System.out.print(" (C, "+ operand2_constant + ")");
		}
		System.out.println("");
		curr_str = input.readLine();
		
		}
		
		System.out.println("\n\n\nSYMBOL TABLE");
		for(int i=1; i<curr_symb_index; i++) {
			String symb_name = index_symb.get(i);
			System.out.println(i + "  " + symb_name + "  " + symb_addr.get(symb_name));
		}
		
		
		input.close();
		}
	
	catch (Exception e) {
		e.getStackTrace();
	}
	
	}

}
