package processor.pipeline;

public class IF_EnableLatchType {

	boolean IF_enable;
	boolean IF_busy;
	boolean IF_busy_EX;
	// boolean branch;
	boolean ex_pc;

	public IF_EnableLatchType() {
		IF_enable = true;
		ex_pc = false;
	}

	public boolean isIF_enable() {
		return IF_enable;
	}

	public void setIF_enable(boolean iF_enable) {
		IF_enable = iF_enable;
	}

	public void setIF_busy(boolean iF_busy) {
		IF_busy = iF_busy;
	}

	public boolean isIF_busy() {
		return IF_busy;
	}

	// public boolean is_branch(){
	// return branch;
	// }

	// public void set_branch(boolean b){
	// branch = b;
	// }

	public boolean is_expc() {
		return ex_pc;
	}

	public void set_expc(boolean b) {
		ex_pc = b;
	}

	public boolean is_IF_busy_EX() {
		return IF_busy_EX;
	}

	public void set_IF_busy_EX(boolean b) {
		IF_busy_EX = b;
	}
}
