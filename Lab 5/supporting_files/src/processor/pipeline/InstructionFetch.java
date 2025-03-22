package processor.pipeline;

import generic.Statistics;
import processor.Processor;

public class InstructionFetch {

	Processor containingProcessor;
	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	EX_IF_LatchType EX_IF_Latch;

	public InstructionFetch(Processor containingProcessor, IF_EnableLatchType iF_EnableLatch,
			IF_OF_LatchType iF_OF_Latch, EX_IF_LatchType eX_IF_Latch) {
		this.containingProcessor = containingProcessor;
		this.IF_EnableLatch = iF_EnableLatch;
		this.IF_OF_Latch = iF_OF_Latch;
		this.EX_IF_Latch = eX_IF_Latch;
	}

	public void performIF() {
		if (IF_EnableLatch.isIF_enable()) {
			if (EX_IF_Latch.isBranchEnabled()) {
				int newPC = EX_IF_Latch.getPC();
				if (IF_OF_Latch.getBranchHazard()) {
					IF_OF_Latch.setBranchHazard(false);
					IF_OF_Latch.setInstruction(-1);
					Statistics.setwrongBranch(Statistics.getwrongBranch() + 1);
				} else {
					containingProcessor.getRegisterFile().setProgramCounter(newPC);
					EX_IF_Latch.setBranchEnabled(false);
					int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
					int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
					IF_OF_Latch.setInstruction(newInstruction);
					System.out.println("PC now: " + currentPC);

					containingProcessor.getRegisterFile().setProgramCounter(currentPC + 1);
				}
			} else {
				int currentPC = containingProcessor.getRegisterFile().getProgramCounter();
				int newInstruction = containingProcessor.getMainMemory().getWord(currentPC);
				IF_OF_Latch.setInstruction(newInstruction);
				System.out.println("PC now: " + currentPC);

				containingProcessor.getRegisterFile().setProgramCounter(currentPC + 1);
			}
			// IF_EnableLatch.setIF_enable(false);
			IF_OF_Latch.setOF_enable(true);
		}
	}

}
