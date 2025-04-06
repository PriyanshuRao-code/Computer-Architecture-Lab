package processor.pipeline;

// import java.lang.module.Configuration;

import generic.*;
import processor.*;
import configuration.Configuration;

public class MemoryAccess implements Element {
	Processor containingProcessor;
	EX_MA_LatchType EX_MA_Latch;
	MA_RW_LatchType MA_RW_Latch;
	Branch_Hazard Br;

	public MemoryAccess(Processor containingProcessor, EX_MA_LatchType eX_MA_Latch, MA_RW_LatchType mA_RW_Latch,
			Branch_Hazard br) {
		this.containingProcessor = containingProcessor;
		this.EX_MA_Latch = eX_MA_Latch;
		this.MA_RW_Latch = mA_RW_Latch;
		this.Br = br;
	}

	public void performMA() {
		if (EX_MA_Latch.is_MA_busy()) {
			return;
		}

		if (MA_RW_Latch.is_MA_problem()) {
			EX_MA_Latch.setInstruction(null);
			MA_RW_Latch.set_MA_problem(false);
		}
		if (EX_MA_Latch.is_branch()) {
			return;
		}
		if (EX_MA_Latch.isMA_enable()) {

			// Taking instruction and alu from EX_MA latch
			Instruction inst = EX_MA_Latch.getInstruction();

			// MA_RW_Latch.setInstruction(inst); // Sending instruction further (don't send
			// it here in assignment 5)

			System.out.println("MA instruction: " + inst);
			if (inst == null) {
				MA_RW_Latch.setInstruction(inst);
				MA_RW_Latch.setRW_enable(true);
			}
			if (inst != null) {

				String op = inst.getOperationType().toString();
				int alu = EX_MA_Latch.getALu(); // alu is pcnow + immed i.e. address (for load and store)

				// MA_RW_Latch.setAlu(alu);

				if (op.equals("load")) {
					// MA_RW_Latch.setIdresult(containingProcessor.getMainMemory().getWord(alu)); //
					// storing load result to
					Simulator.getEventQueue()
							.addEvent(new MemoryReadEvent(Clock.getCurrentTime() + Configuration.mainMemoryLatency,
									this, containingProcessor.getMainMemory(), alu));

					Br.IF_OF_Latch.setOF_busy_MA(true);
					EX_MA_Latch.set_MA_busy(true);
					Br.OF_EX_Latch.set_EX_busy_MA(true);
					MA_RW_Latch.set_RW_busy(true);
					MA_RW_Latch.setRW_enable(false);
					// latch
				} else if (op.equals("store")) {
					// Storing word in source operand 1 in alu (address). Value is taken from
					// registerfile
					// containingProcessor.getMainMemory().setWord(alu,
					// containingProcessor.getRegisterFile().getValue(inst.getSourceOperand1().getValue()));
					Simulator.getEventQueue().addEvent(new MemoryWriteEvent(Clock.getCurrentTime(), this,
							containingProcessor.getMainMemory(), alu,
							containingProcessor.getRegisterFile().getValue(inst.getSourceOperand1().getValue())));

					Br.IF_OF_Latch.setOF_busy_MA(true);
					EX_MA_Latch.set_MA_busy(true);
					Br.OF_EX_Latch.set_EX_busy_MA(true);
					MA_RW_Latch.set_RW_busy(true);
					MA_RW_Latch.setRW_enable(false);
				}

				else if (op.equals("end")) {
					EX_MA_Latch.setMA_enable(false);
					MA_RW_Latch.setInstruction(inst);

				} else {
					MA_RW_Latch.setInstruction(inst);
					MA_RW_Latch.setRW_enable(true);
					MA_RW_Latch.setAlu(alu);
				}
			}

			// MA_RW_Latch.setRW_enable(true); (Not here in assignment 5)

			// EX_MA_Latch.setMA_enable(false);

		}
	}

	@Override
	public void handleEvent(Event e) {
		System.out.println("\n-------HANDLING EVENT IN MA--------\n");
		MemoryResponseEvent event = (MemoryResponseEvent) e;
		Instruction inst = EX_MA_Latch.getInstruction();
		MA_RW_Latch.setInstruction(inst);
		MA_RW_Latch.setIdresult(event.getValue());
		MA_RW_Latch.setRW_enable(true);

		MA_RW_Latch.set_MA_problem(true);

		Br.IF_OF_Latch.setOF_busy_MA(false);
		EX_MA_Latch.set_MA_busy(false);
		Br.OF_EX_Latch.set_EX_busy_MA(false);
		MA_RW_Latch.set_RW_busy(false);
	}
}
