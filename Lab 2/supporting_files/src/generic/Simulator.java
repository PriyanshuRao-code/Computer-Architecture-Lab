package generic;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import generic.Operand.OperandType;

public class Simulator {

	static FileInputStream inputcodeStream = null;

	private static String destinationFile;
	public static void setupSimulation(String assemblyProgramFile, String objectProgramFile) {
		int firstCodeAddress = ParsedProgram.parseDataSection(assemblyProgramFile);
		ParsedProgram.parseCodeSection(assemblyProgramFile, firstCodeAddress);
		ParsedProgram.printState();

		destinationFile = objectProgramFile;
	}

	public static void assemble() {

		DataOutputStream write_int = null;
		FileOutputStream file_out_obj = null; 
		try {
			file_out_obj = new FileOutputStream(destinationFile);
			write_int = new DataOutputStream(file_out_obj);
			try {
				int main_func_addr = ParsedProgram.symtab.get("main");
				write_int.writeInt(main_func_addr);
				for (Integer data : ParsedProgram.data) {
					write_int.writeInt(data);
				}
				for (int prog_counter = main_func_addr; prog_counter < main_func_addr
						+ ParsedProgram.code.size(); ++prog_counter) {
					Instruction inst = ParsedProgram.getInstructionAt(prog_counter);
					int assemble_instr = bin_to_deci(getInstructionString(inst), true);
					write_int.writeInt(assemble_instr);
				}

				write_int.flush();
				write_int.close();
			} catch (IOException e) {
				Misc.printErrorAndExit(e.toString());
			}
		} catch (FileNotFoundException e) { 
			Misc.printErrorAndExit(e.toString());
		}
	}

	private static String getInstructionString(Instruction ins) {

	String answer = "";
    int opcode = getOpcode(ins.getOperationType());
    answer += start_pad(Integer.toBinaryString(opcode), 5, false);

	if (is_R3_type(ins.getOperationType())) {
        answer+=s1_start_pad(ins);
        answer += s2_start_pad(ins);
        answer += dest_start_pad(ins);
        answer = end_pad(answer, 32);
    } 
    else if (is_R2I_type(ins.getOperationType())) {
        answer+= s1_start_pad(ins);
        answer += dest_start_pad(ins);

    int immVal = 0;
    String string_immed = ""; 
    if (is_s2_oper_immed(ins)) {
        immVal = ins.getSourceOperand2().getValue();
        string_immed = Integer.toBinaryString(immVal);
    } 
    else {
        String label = ins.getSourceOperand2().getLabelValue();
        immVal = ParsedProgram.symtab.get(label); 
        string_immed = Integer.toBinaryString(immVal);
    }
    string_immed = start_pad(string_immed, 17, immVal < 0); 
    answer += string_immed;
} 


else if (is_branch_type(ins.getOperationType())) {
    answer += s1_start_pad(ins);
    answer += s2_start_pad(ins);

    String string_immed = "";
    int target_branch = 0;
    if (is_dest_oper_immed(ins)) {
        target_branch = ins.getDestinationOperand().getValue();
    } else {
        String label = ins.getDestinationOperand().getLabelValue();
        target_branch = ParsedProgram.symtab.get(label);
    }
    int prog_counter = ins.getProgramCounter();
    string_immed = Integer.toBinaryString(target_branch - prog_counter);
    if (string_immed.length() > 17) {
        string_immed = string_immed.substring(string_immed.length() - 17);
    }
    string_immed = start_pad(string_immed, 17, (target_branch - prog_counter) < 0);
    answer += string_immed;

} else if (is_RI_type(ins.getOperationType())) {
    answer = end_pad(answer, 10);
    String string_immed = "";
    int target_branch = 0;
    
	if (is_dest_oper_immed(ins) || is_dest_oper_regis(ins)) {
        target_branch = ins.getDestinationOperand().getValue();
        string_immed = Integer.toBinaryString(ins.getDestinationOperand().getValue());
    } else {
        String label = ins.getDestinationOperand().getLabelValue();
        target_branch = ParsedProgram.symtab.get(label);
    }
    
	int prog_counter = ins.getProgramCounter();
    string_immed = Integer.toBinaryString(target_branch - prog_counter);
    if (string_immed.length() > 22) {
        string_immed = string_immed.substring(string_immed.length() - 22);
    }
    string_immed = start_pad(string_immed, 22, (target_branch - prog_counter) < 0);
    answer += string_immed;
} else if (is_end_type(ins.getOperationType())) {
    answer = end_pad(answer, 32);
} else {
    Misc.printErrorAndExit("unknown instruction!!");
}

return answer; 

	}



private static int getOpcode(Instruction.OperationType op_type) {
        switch (op_type) {
            case add: return 0b00000;
            case addi: return 0b00001;
            case sub: return 0b00010;
            case subi: return 0b00011;
            case mul: return 0b00100;
            case muli: return 0b00101;
            case div: return 0b00110;
            case divi: return 0b00111;
            case and: return 0b01000;
            case andi: return 0b01001;
            case or: return 0b01010;
            case ori: return 0b01011;
            case xor: return 0b01100;
            case xori: return 0b01101;
            case slt: return 0b01110;
            case slti: return 0b01111;
            case sll: return 0b10000;
            case slli: return 0b10001;
            case srl: return 0b10010;
            case srli: return 0b10011;
            case sra: return 0b10100;
            case srai: return 0b10101;
            case load: return 0b10110;
            case store: return 0b10111;
            case jmp: return 0b11000;
            case beq: return 0b11001;
            case bne: return 0b11010;
            case blt: return 0b11011;
            case bgt: return 0b11100;
            case end: return 0b11101;
            default:
                Misc.printErrorAndExit("Unknown opcode for: " + op_type);
                return -1;
        }
    }

    private static boolean is_R3_type(Instruction.OperationType op_type) {
    boolean result;
    switch (op_type) {
        case sub:
        case add:
        case div:
        case mul:
        case sra:
        case xor:
        case srl:
        case sll:
        case or:
        case slt:
        case and:
            result = true;
            break;
        default:
            result = false;
            break;
    }
    return result;
}


private static boolean is_R2I_type(Instruction.OperationType op_type) {
    boolean result;
    switch (op_type) {
        case subi:
        case addi:
        case divi:
        case muli:
        case srli:
        case slti:
        case slli:
        case load:
        case store:
        case ori:
        case andi:
        case srai:
        case xori:
            result = true;
            break;
        default:
            result = false;
            break;
    }
    return result;
}


private static boolean is_branch_type(Instruction.OperationType op_type) {
    boolean result;
    switch (op_type) {
        case beq:
        case bne:
        case blt:
        case bgt:
            result = true;
            break;
        default:
            result = false;
            break;
    }
    return result;
}


private static boolean is_RI_type(Instruction.OperationType op_type) {
    boolean result;
    switch (op_type) {
        case jmp:
            result = true;
            break;
        default:
            result = false;
            break;
    }
	return result;
}


private static boolean is_end_type(Instruction.OperationType op_type) {
    boolean result;
    switch (op_type) {
        case end:
            result = true;
            break;
        default:
            result = false;
            break;
    }
    return result;
}


    public static String s1_start_pad(Instruction instr) {
        return start_pad(Integer.toBinaryString(instr.getSourceOperand1().getValue()), 5, false);
    }
    
    public static String s2_start_pad(Instruction instr){
        return start_pad(Integer.toBinaryString(instr.getSourceOperand2().getValue()), 5, false);
    }
    
    public static String dest_start_pad(Instruction instr){
        return start_pad(Integer.toBinaryString(instr.getDestinationOperand().getValue()), 5, false);
    }


	private static String start_pad(String str, int total_len, boolean is_signed) {
		if (str.length() >= total_len) {
			return str;
		}
		int len_count = 0;
		String answer = "";
        char c;
        if(is_signed){
            c='1';
        }
        else{
            c='0';
        }
		while (len_count < total_len - str.length()) {
			answer += c;
			++len_count;
		}
		answer += str;
		return answer;
	}

    private static boolean is_dest_oper_immed(Instruction instr){
        boolean result;
        if(instr.getDestinationOperand().getOperandType() == OperandType.valueOf("Immediate")){
            result = true;
        }
        else{
            result = false;
        }
        return result;
    }
    
    private static boolean is_dest_oper_regis(Instruction instr){
        boolean result;
        if(instr.getDestinationOperand().getOperandType() == OperandType.valueOf("Register")
){
            result = true;
        }
        else{
            result = false;
        }
        return result;
    }    


    private static boolean is_s2_oper_immed(Instruction instr){
        boolean result;
        if(instr.getSourceOperand2().getOperandType() == OperandType.valueOf("Immediate")){
            result=true;
        }
        else{
            result =false;
        }
        return result;
	
    }
        private static int bin_to_deci(String bin_str, boolean is_signed) {
    
		if (!is_signed) {
			return Integer.parseInt(bin_str, 2);
		} else {
			String copy_str = '0' + bin_str.substring(1);
			int answer = Integer.parseInt(copy_str, 2);

			if (bin_str.length() == 32) {
				int pow = (1 << 30); 
				if (bin_str.charAt(0) == '1') {
					answer -= pow;
					answer -= pow;
				}
			} else {
				int pow = (1 << (bin_str.length() - 1));
				if (bin_str.charAt(0) == '1') { 
					answer -= pow;
				}
			}

			return answer;
		}
	}



    private static String end_pad(String str, int total_len) {
		if (str.length() >= total_len) { 
			return str;
		}
		int len_count = 0;
		String answer = str;
		while (len_count < total_len - str.length()) {
			answer += '0';
			++len_count;
		}
		return answer;
	}

    
   
}