package processor.pipeline;

import generic.*;

public class OF_EX_LatchType {

	boolean EX_enable;
	Instruction instruction;
	boolean branch;
	boolean branch_problem;
	boolean jumped;
	boolean EX_busy;
	boolean EX_busy_MA;

	public OF_EX_LatchType() {
		EX_enable = false;
		branch_problem = false;
	}

	public boolean isEX_enable() {
		return EX_enable;
	}

	public void setEX_enable(boolean eX_enable) {
		EX_enable = eX_enable;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public boolean is_branch() {
		return branch;
	}

	public void set_branch(boolean b) {
		branch = b;
	}

	public boolean is_branch_problem() {
		return branch_problem;
	}

	public void set_branch_problem(boolean b) {
		branch_problem = b;
	}

	public boolean is_jumped() {
		return jumped;
	}

	public void set_jumped(boolean b) {
		jumped = b;
	}

	public boolean is_EX_busy() {
		return EX_busy;
	}

	public void set_EX_busy(boolean b) {
		EX_busy = b;
	}

	public boolean is_EX_busy_MA() {
		return EX_busy;
	}

	public void set_EX_busy_MA(boolean b) {
		EX_busy = b;
	}

}
