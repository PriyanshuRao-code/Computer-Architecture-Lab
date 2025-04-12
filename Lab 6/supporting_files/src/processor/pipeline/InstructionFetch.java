package processor.pipeline;

import configuration.*;
import generic.*;
import processor.*;

public class InstructionFetch implements Element {

	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	EX_IF_LatchType EX_IF_Latch;
	Branch_Hazard Br;

	public InstructionFetch(Processor containingProcessor, IF_EnableLatchType iF_EnableLatch,
			IF_OF_LatchType iF_OF_Latch, EX_IF_LatchType eX_IF_Latch, Branch_Hazard br) {
		this.containingProcessor = containingProcessor;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
		this.Br = br;
	}

	public void performIF() {
		if (IF_EnableLatch.isIF_enable()) {
			// || IF_EnableLatch.is_IF_busy_EX()
			if (IF_EnableLatch.isIF_busy()) {
				// IF_OF_Latch.setInstruction(-1);
				System.out.println("IF BUSY");
				return;
			}
			if (EX_IF_Latch.isBranchEnabled()) {
				int newPC = EX_IF_Latch.getPC();
				if (IF_OF_Latch.getBranchHazard()) {
					IF_OF_Latch.setBranchHazard(false);
					IF_OF_Latch.setInstruction(-1);
					Statistics.setwrongBranch(Statistics.getwrongBranch() + 1);
					IF_OF_Latch.setOF_enable(true);
					System.out.println("Branch Hazard Detected in IF");
				} else {
					System.out.println("SETTING EX_IF PC");
					containingProcessor.getRegisterFile().setProgramCounter(newPC);
					EX_IF_Latch.setBranchEnabled(false);
					int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
					// int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
					// IF_OF_Latch.setInstruction(newInstruction);
					// System.out.println("PC now: " + currentPC);

					Simulator.getEventQueue()
							.addEvent(new MemoryReadEvent(Clock.getCurrentTime() + Configuration.mainMemoryLatency,
									this, containingProcessor.getMainMemory(),
									containingProcessor.getRegisterFile().getProgramCounter()));
					IF_EnableLatch.setIF_busy(true);
					IF_OF_Latch.setOF_enable(false);

					// IF_EnableLatch.set_branch(false);
					IF_EnableLatch.set_expc(true);

					// containingProcessor.getRegisterFile().setProgramCounter(currentPC + 1);
				}
			} else {
				// int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
				// int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
				// IF_OF_Latch.setInstruction(newInstruction);
				// System.out.println("PC now: " + currentPC);

				Simulator.getEventQueue()
						.addEvent(new MemoryReadEvent(Clock.getCurrentTime() + Configuration.mainMemoryLatency, this,
								containingProcessor.getMainMemory(),
								containingProcessor.getRegisterFile().getProgramCounter()));
				IF_EnableLatch.setIF_busy(true);
				IF_OF_Latch.setOF_enable(false);

				// containingProcessor.getRegisterFile().setProgramCounter(currentPC + 1);
			}
			// IF_EnableLatch.setIF_enable(false);

			// IF_OF_Latch.setOF_enable(true);
		}
	}

	@Override
	public void handleEvent(Event e) {
		if (IF_OF_Latch.isOF_busy() || IF_OF_Latch.isOF_busy_EX() || IF_OF_Latch.isOF_busy_MA()) {
			e.setEventTime(Clock.getCurrentTime() + 1);
			Simulator.getEventQueue().addEvent(e);
		} else {
			System.out.println("\n------HANDLING EVENT IN IF-----\n");
			MemoryResponseEvent event = (MemoryResponseEvent) e;
			if (IF_EnableLatch.is_expc()) {
				IF_EnableLatch.set_expc(false);
				IF_OF_Latch.set_branch(false);
				Br.OF_EX_Latch.set_branch(false);
				Br.EX_MA_Latch.set_branch(false);
				Br.MA_RW_Latch.set_branch(false);
			}

			Statistics.setNumberOfInstructions(Statistics.getNumberofInstruction() + 1);
			System.out
					.println("\n\n\n-------INSTRUCTION COUNT " + Statistics.getNumberofInstruction() + "------\n\n\n");

			int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
			IF_OF_Latch.setInstruction(event.getValue());
			System.out.println("PC now: " + currentPC);
			containingProcessor.getRegisterFile().setProgramCounter(currentPC + 1);

			IF_OF_Latch.setOF_enable(true);
			IF_EnableLatch.setIF_busy(false);
		}

	}
}
