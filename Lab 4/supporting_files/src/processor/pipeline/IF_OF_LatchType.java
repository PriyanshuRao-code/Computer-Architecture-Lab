package processor.pipeline;

import generic.*;

public class IF_OF_LatchType {

	boolean OF_enable;
	int instruction;
	boolean branchhazard;
	Instruction instr;

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

}
