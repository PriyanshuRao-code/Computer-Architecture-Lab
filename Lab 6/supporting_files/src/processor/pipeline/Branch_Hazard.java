package processor.pipeline;

import generic.*;
// import generic.Instruction.OperationType;
// import generic.Operand.OperandType;
import processor.Processor;

public class Branch_Hazard {
    Processor containingProcessor;
    OF_EX_LatchType OF_EX_Latch;
    EX_MA_LatchType EX_MA_Latch;
    MA_RW_LatchType MA_RW_Latch;
    IF_EnableLatchType IF_EnableLatch;
    IF_OF_LatchType IF_OF_Latch;

    public Branch_Hazard(Processor containingProcessor, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch,
            MA_RW_LatchType mA_RW_Latch, IF_EnableLatchType if_EnableLatch, IF_OF_LatchType if_OF_Latch) {
        this.containingProcessor = containingProcessor;
        this.OF_EX_Latch = oF_EX_Latch;
        this.EX_MA_Latch = eX_MA_Latch;
        this.MA_RW_Latch = mA_RW_Latch;
        this.IF_EnableLatch = if_EnableLatch;
        this.IF_OF_Latch = if_OF_Latch;
    }

    public void performBranch(boolean is_jumped) {
        // System.out.println("Checking for branch hazard");
        if (is_jumped) {
            System.out.println("Branch hazard found");
            IF_OF_Latch.setInstruction(-1);
            IF_OF_Latch.setBranchHazard(true);
            Statistics.setwrongBranch(Statistics.getwrongBranch() + 1);

            IF_OF_Latch.set_branch(true);
            OF_EX_Latch.set_branch(true);
            EX_MA_Latch.set_branch(true);
            MA_RW_Latch.set_branch(true);
            OF_EX_Latch.set_branch_problem(true);
        }
    }

}
