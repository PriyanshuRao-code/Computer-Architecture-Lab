package processor.pipeline;

import generic.*;
import processor.Processor;

public class RegisterWrite {
	Processor containingProcessor;
	MA_RW_LatchType MA_RW_Latch;
	IF_EnableLatchType IF_EnableLatch;
	
	public RegisterWrite(Processor containingProcessor, MA_RW_LatchType mA_RW_Latch, IF_EnableLatchType iF_EnableLatch)
	{
		this.containingProcessor = containingProcessor;
		this.MA_RW_Latch = mA_RW_Latch;
		this.IF_EnableLatch = iF_EnableLatch;
	}
	
	public void performRW()
	{
		if(MA_RW_Latch.isRW_enable())
		{
			
			// if instruction being processed is an end instruction, remember to call Simulator.setSimulationComplete(true);
			

			
			//Taking instruction again from latch(MA_RW_Latch)
			Instruction instruction = MA_RW_Latch.getInstruction();
			String op = instruction.getOperationType().toString();

			if(op.equals("load")){
				// Putting load value into rd
				//Setting (register number, value)
				containingProcessor.getRegisterFile().setValue(instruction.getDestinationOperand().getValue(), MA_RW_Latch.getIdresult()); 
			}

			else if(op.equals("store")||op.equals("jmp")||op.equals("beq")||op.equals("blt")||op.equals("bne")||op.equals("bgt")){
				//Do nothing
			}
			
			else if(op.equals("end")){
				Simulator.setSimulationComplete(true);
			}
			else{
				// Putting alu result into rd (register number, value)
				containingProcessor.getRegisterFile().setValue(instruction.getDestinationOperand().getValue(), MA_RW_Latch.getALu());
			}

			MA_RW_Latch.setRW_enable(false);
			IF_EnableLatch.setIF_enable(true);
		}
	}

}
