package processor.pipeline;

import generic.*;

public class IF_OF_LatchType {

	boolean OF_enable;
	int instruction;
	boolean branchhazard;
	Instruction instr;
	boolean OF_busy;
	boolean OF_busy_MA;
	boolean OF_busy_EX;
	boolean branch;

	public IF_OF_LatchType() {
		OF_enable = false;
	}

	public boolean isOF_enable() {
		return OF_enable;
	}

	public void setOF_enable(boolean oF_enable) {
		OF_enable = oF_enable;
	}

	public int getInstruction() {
		return instruction;
	}

	public void setInstruction(int instruction) {
		this.instruction = instruction;
	}

	public boolean getBranchHazard() {
		return branchhazard;
	}

	public void setBranchHazard(boolean branchhazard) {
		this.branchhazard = branchhazard;
	}

	public void setIF_OFinstructionRW(Instruction inst) {
		instr = inst;
	}

	public Instruction getIF_OFinstructionRW() {
		return instr;
	}

	public boolean isOF_busy() {
		return OF_busy;
	}

	public void setOF_busy(boolean oF_busy) {
		OF_busy = oF_busy;
	}

	public boolean isOF_busy_EX() {
		return OF_busy_EX;
	}

	public void setOF_busy_EX(boolean oF_busy) {
		OF_busy_EX = oF_busy;
	}

	public boolean isOF_busy_MA() {
		return OF_busy_MA;
	}

	public void setOF_busy_MA(boolean oF_busy) {
		OF_busy_MA = oF_busy;
	}

	public boolean is_branch() {
		return branch;
	}

	public void set_branch(boolean b) {
		branch = b;
	}

}
