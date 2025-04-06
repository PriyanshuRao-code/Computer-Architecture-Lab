package processor.pipeline;

import generic.*;

public class EX_MA_LatchType {

	boolean MA_enable;
	Instruction instruction;
	int aluResult;
	boolean branch;
	boolean MA_busy;

	public EX_MA_LatchType() {
		MA_enable = false;
	}

	public boolean isMA_enable() {
		return MA_enable;
	}

	public void setMA_enable(boolean mA_enable) {
		MA_enable = mA_enable;
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

	public boolean is_branch() {
		return branch;
	}

	public void set_branch(boolean b) {
		branch = b;
	}

	public boolean is_MA_busy() {
		return MA_busy;
	}

	public void set_MA_busy(boolean b) {
		MA_busy = b;
	}
}
