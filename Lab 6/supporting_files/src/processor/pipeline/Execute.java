package processor.pipeline;

// import java.lang.module.Configuration;

import generic.*;
import generic.Operand.OperandType;
import processor.Clock;
import processor.Processor;
import configuration.*;

public class Execute implements Element {
	Processor containingProcessor;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	Branch_Hazard Br;

	public Execute(Processor containingProcessor, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch,
			EX_IF_LatchType eX_IF_Latch, Branch_Hazard br) {
		this.containingProcessor = containingProcessor;
		this.OF_EX_Latch = oF_EX_Latch;
		this.EX_MA_Latch = eX_MA_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
		this.Br = br;
	}

	public void performEX() {
		// TODO
		if (OF_EX_Latch.is_branch()) {
			System.out.println("BRANCH IN EX");
			return;
		}
		//
		if (OF_EX_Latch.is_EX_busy() || OF_EX_Latch.is_EX_busy_MA()) {
			System.out.println("EX busy");
			return;
		}
		OF_EX_Latch.set_jumped(false);
		if (OF_EX_Latch.isEX_enable()) {
			if (EX_MA_Latch.getInstruction() != null) {
				if ((EX_MA_Latch.getInstruction().getOperationType().toString().equals("jmp") ||
						EX_MA_Latch.getInstruction().getOperationType().toString().equals("bgt") ||
						EX_MA_Latch.getInstruction().getOperationType().toString().equals("beq") ||
						EX_MA_Latch.getInstruction().getOperationType().toString().equals("blt") ||
						EX_MA_Latch.getInstruction().getOperationType().toString().equals("bne")) &&
						(OF_EX_Latch.is_branch_problem())) {

					OF_EX_Latch.setInstruction(null);
					OF_EX_Latch.set_branch_problem(false);
				}
			}
			// Working with instruction which we got from OF_EX_Latch()
			Instruction inst = OF_EX_Latch.getInstruction();
			if (inst == null) {
				System.out.println("NULL in EX");
				EX_MA_Latch.setInstruction(inst);
			}
			// Latency instruction - operationType -> mul, div, alu latency

			if (inst != null) {
				System.out.println("EX instruction: " + inst);
				String opType = inst.getOperationType().toString();

				// programCounter from RegisterFile (In InstructionFetch.java pcnow was set as
				// (currentpc + 1))
				int pcnow = containingProcessor.getRegisterFile().programCounter - 2; // Change from assignment 3

				// Finding alu
				int alu = 0;
				if (opType.equals("addi") || opType.equals("subi") || opType.equals("muli") || opType.equals("divi")
						|| opType.equals("andi") || opType.equals("ori") || opType.equals("xori")
						|| opType.equals("slti")
						|| opType.equals("slli") || opType.equals("srli") || opType.equals("srai")
						|| opType.equals("load")
						|| opType.equals("store")) {

					// getting values of register and immediates for operation R2I
					int rs1 = containingProcessor.getRegisterFile().getValue(inst.getSourceOperand1().getValue()); // Getting
																													// value
																													// from
																													// register
																													// file,
																													// register
																													// number
																													// stored
																													// in
																													// Operand
																													// (Which
																													// is
																													// set
																													// at
																													// RegisterWrite.java)
																													// (while
																													// x0
																													// is
																													// defined
																													// initially
																													// in
																													// Simulator.java)
					int rd = containingProcessor.getRegisterFile().getValue(inst.getDestinationOperand().getValue());
					int immed = inst.getSourceOperand2().getValue();

					switch (opType) {
						case "addi":
							alu = rs1 + immed;
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "subi":
							alu = rs1 - immed;
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "muli":
							alu = rs1 * immed;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.multiplier_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "divi":
							alu = rs1 / immed;
							containingProcessor.getRegisterFile().setValue(31, rs1 % immed); // Remember to set
																								// remainder at
																								// x31

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.divider_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "andi":
							alu = rs1 & immed;
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "xori":
							alu = rs1 ^ immed;
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "ori":
							alu = rs1 | immed;
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "slti":
							alu = 0;
							if (immed > rs1) {
								alu = 1;
							}
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "slli":
							alu = rs1 << immed;
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "srai":
							alu = rs1 >> immed; // arithmetic shift right

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "srli":
							alu = rs1 >>> immed; // logical shift right

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "load":
							alu = rs1 + immed;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							System.out.println("EX BUSY IN LOAD EX");
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "store":
							alu = rd + immed; // We took value from rd specially for it.

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						default:
							System.out.print("Problem in switch(OpType) for R2I");
					}

				} else if (opType.equals("add") || opType.equals("sub") || opType.equals("mul") || opType.equals("div")
						|| opType.equals("and") || opType.equals("or") || opType.equals("xor") || opType.equals("slt")
						|| opType.equals("sll") || opType.equals("srl") || opType.equals("sra")) {

					// Finding alu for register operations
					int rs2 = containingProcessor.getRegisterFile().getValue(inst.getSourceOperand2().getValue());
					int rs1 = containingProcessor.getRegisterFile().getValue(inst.getSourceOperand1().getValue());

					switch (opType) {
						case "add":
							alu = rs1 + rs2;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "sub":
							alu = rs1 - rs2;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "mul":
							alu = rs1 * rs2;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.multiplier_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "div":
							alu = rs1 / rs2;
							containingProcessor.getRegisterFile().setValue(31, rs1 % rs2); // Remember setting value in
																							// register file

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.divider_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "and":
							alu = rs1 & rs2;
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "xor":
							alu = rs1 ^ rs2;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "or":
							alu = rs1 | rs2;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "sll":
							alu = rs1 << rs2;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "slt":
							alu = 0;
							if (rs2 > rs1) {
								alu = 1;
							}

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "sra":
							alu = rs1 >> rs2;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "srl":
							alu = rs1 >>> rs2;

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						default:
							System.out.print("Issue in R3 switch");
					}
				}

				else if (opType.equals("jmp")) {

					// Specially for jmp
					OperandType jumpType = inst.getDestinationOperand().getOperandType();
					int immed = 0;

					// jmp instruction has either register or immediate as target
					if (jumpType == OperandType.Immediate) {
						immed = inst.getDestinationOperand().getValue();
					}

					else {
						immed = containingProcessor.getRegisterFile().getValue(inst.getDestinationOperand().getValue());
						// Value corresponding to register (if present)
					}

					alu = immed + pcnow; // line number where we have to go.

					// is_jumped = true;
					OF_EX_Latch.set_jumped(true);
					EX_IF_Latch.setBranchEnabled(true);
					EX_IF_Latch.setPC(alu);// Sending the value calculated to IF stage

					Simulator.getEventQueue().addEvent(
							new ExecutionCompleteEvent(Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
					OF_EX_Latch.set_EX_busy(true);
					Br.IF_OF_Latch.setOF_busy_EX(true);
					Br.IF_EnableLatch.set_IF_busy_EX(true);

				} else if (opType.equals("end")) {
					OF_EX_Latch.setEX_enable(false);
					EX_MA_Latch.setMA_enable(true);

					// Do nothing (To separate it so that it does not go to default error line in
					// next else statement)
				} else {
					// Conditional statement

					// Setting register value
					int rs1 = containingProcessor.getRegisterFile().getValue(inst.getSourceOperand1().getValue());
					int rs2 = containingProcessor.getRegisterFile().getValue(inst.getSourceOperand2().getValue());
					int immed = inst.getDestinationOperand().getValue();

					switch (opType) {
						case "bgt":
							if (rs1 > rs2) {
								alu = pcnow + immed;

								// Enabling EX_IF_LatchType
								// is_jumped = true;
								OF_EX_Latch.set_jumped(true);
								EX_IF_Latch.setBranchEnabled(true);
								EX_IF_Latch.setPC(alu);
							}
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "beq":
							if (rs1 == rs2) {
								alu = pcnow + immed;

								// is_jumped = true;
								OF_EX_Latch.set_jumped(true);
								EX_IF_Latch.setBranchEnabled(true);
								EX_IF_Latch.setPC(alu);
							}
							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "blt":
							if (rs1 < rs2) {
								alu = pcnow + immed;

								// is_jumped = true;
								OF_EX_Latch.set_jumped(true);
								EX_IF_Latch.setBranchEnabled(true);
								EX_IF_Latch.setPC(alu);
							}

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						case "bne":
							if (rs1 != rs2) {
								alu = pcnow + immed;

								// is_jumped = true;
								OF_EX_Latch.set_jumped(true);
								EX_IF_Latch.setBranchEnabled(true);
								EX_IF_Latch.setPC(alu);
							}

							Simulator.getEventQueue().addEvent(new ExecutionCompleteEvent(
									Clock.getCurrentTime() + Configuration.ALU_latency, this, this));
							OF_EX_Latch.set_EX_busy(true);
							Br.IF_OF_Latch.setOF_busy_EX(true);
							Br.IF_EnableLatch.set_IF_busy_EX(true);
							break;
						default:
							System.out.print("Problem in conditional branch statements");
							break;
					}
				}

				EX_MA_Latch.setAlu(alu); // sending alu for MA
				EX_MA_Latch.setInstruction(inst);
			}
			Br.performBranch(OF_EX_Latch.is_jumped());

		}
		// OF_EX_Latch.setEX_enable(false);
		// EX_MA_Latch.setMA_enable(true);
	}

	@Override
	public void handleEvent(Event e) {
		if (EX_MA_Latch.is_MA_busy()) {
			System.out.println("EX busy due to MA");
			e.setEventTime(Clock.getCurrentTime() + 1);
			Simulator.getEventQueue().addEvent(e);
		} else {
			System.out.println("\n------HANDLING EVENT IN EX-----\n");

			EX_MA_Latch.setMA_enable(true);
			OF_EX_Latch.set_EX_busy(false);
			Br.IF_OF_Latch.setOF_busy_EX(false);
			Br.IF_EnableLatch.set_IF_busy_EX(false);
		}

	}
}