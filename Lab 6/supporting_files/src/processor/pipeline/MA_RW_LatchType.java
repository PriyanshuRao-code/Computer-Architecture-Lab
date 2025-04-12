package processor.pipeline;

import generic.*;

public class MA_RW_LatchType {

	boolean RW_enable;
	Instruction instruction;
	int aluResult;
	int Idresult;
	boolean branch;
	boolean RW_busy;
	boolean MA_problem;

	public MA_RW_LatchType() {
		RW_enable = false;
	}

	public boolean isRW_enable() {
		return RW_enable;
	}

	public void setRW_enable(boolean rW_enable) {
		RW_enable = rW_enable;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public void setAlu(int aluResult) {
		this.aluResult = aluResult;
	}

	public int getALu() {
		return aluResult;
	}

	public void setIdresult(int Idresult) {
		this.Idresult = Idresult;
	}

	public int getIdresult() {
		return Idresult;
	}

	public boolean is_branch() {
		return branch;
	}

	public void set_branch(boolean b) {
		branch = b;
	}

	public boolean is_RW_busy() {
		return RW_busy;
	}

	public void set_RW_busy(boolean b) {
		RW_busy = b;
	}

	public boolean is_MA_problem() {
		return MA_problem;
	}

	public void set_MA_problem(boolean b) {
		MA_problem = b;
	}
}
