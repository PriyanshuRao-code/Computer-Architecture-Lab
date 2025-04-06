package processor.pipeline;

import generic.*;
import generic.Instruction.OperationType;
import generic.Operand.OperandType;
import processor.Processor;

public class Data_Hazard {
    Processor containingProcessor;
    OF_EX_LatchType OF_EX_Latch;
    EX_MA_LatchType EX_MA_Latch;
    MA_RW_LatchType MA_RW_Latch;
    IF_EnableLatchType IF_EnableLatch;
    IF_OF_LatchType IF_OF_Latch;

    public Data_Hazard(Processor containingProcessor, OF_EX_LatchType oF_EX_Latch, EX_MA_LatchType eX_MA_Latch,
            MA_RW_LatchType mA_RW_Latch, IF_EnableLatchType if_EnableLatch, IF_OF_LatchType if_OF_Latch) {
        this.containingProcessor = containingProcessor;
        this.OF_EX_Latch = oF_EX_Latch;
        this.EX_MA_Latch = eX_MA_Latch;
        this.MA_RW_Latch = mA_RW_Latch;
        this.IF_EnableLatch = if_EnableLatch;
        this.IF_OF_Latch = if_OF_Latch;
    }

    public IF_EnableLatchType getIF_EnableLatch() {
        return this.IF_EnableLatch;
    }

    public void setIF_EnableLatch(IF_EnableLatchType if_EnableLatch) {
        this.IF_EnableLatch = if_EnableLatch;
    }

    public boolean DestInstr(Instruction inst) {
        if (inst.getOperationType().toString().equals("add") ||
                inst.getOperationType().toString().equals("addi") ||
                inst.getOperationType().toString().equals("sub") ||
                inst.getOperationType().toString().equals("subi") ||
                inst.getOperationType().toString().equals("mul") ||
                inst.getOperationType().toString().equals("muli") ||
                inst.getOperationType().toString().equals("div") ||
                inst.getOperationType().toString().equals("divi") ||
                inst.getOperationType().toString().equals("and") ||
                inst.getOperationType().toString().equals("andi") ||
                inst.getOperationType().toString().equals("or") ||
                inst.getOperationType().toString().equals("ori") ||
                inst.getOperationType().toString().equals("xor") ||
                inst.getOperationType().toString().equals("xori") ||
                inst.getOperationType().toString().equals("slt") ||
                inst.getOperationType().toString().equals("slti") ||
                inst.getOperationType().toString().equals("sll") ||
                inst.getOperationType().toString().equals("slli") ||
                inst.getOperationType().toString().equals("srl") ||
                inst.getOperationType().toString().equals("srli") ||
                inst.getOperationType().toString().equals("sra") ||
                inst.getOperationType().toString().equals("srai") ||
                inst.getOperationType().toString().equals("load")) {
            return true;
        }
        return false;
    }

    public boolean SourceInstr(Instruction inst) {
        if (inst.getOperationType().toString().equals("add") ||
                inst.getOperationType().toString().equals("addi") ||
                inst.getOperationType().toString().equals("sub") ||
                inst.getOperationType().toString().equals("subi") ||
                inst.getOperationType().toString().equals("mul") ||
                inst.getOperationType().toString().equals("muli") ||
                inst.getOperationType().toString().equals("div") ||
                inst.getOperationType().toString().equals("divi") ||
                inst.getOperationType().toString().equals("and") ||
                inst.getOperationType().toString().equals("andi") ||
                inst.getOperationType().toString().equals("or") ||
                inst.getOperationType().toString().equals("ori") ||
                inst.getOperationType().toString().equals("xor") ||
                inst.getOperationType().toString().equals("xori") ||
                inst.getOperationType().toString().equals("slt") ||
                inst.getOperationType().toString().equals("slti") ||
                inst.getOperationType().toString().equals("sll") ||
                inst.getOperationType().toString().equals("slli") ||
                inst.getOperationType().toString().equals("srl") ||
                inst.getOperationType().toString().equals("srli") ||
                inst.getOperationType().toString().equals("sra") ||
                inst.getOperationType().toString().equals("srai") ||
                inst.getOperationType().toString().equals("load") ||
                inst.getOperationType().toString().equals("store") ||
                // inst.getOperationType().toString().equals("jmp") ||
                inst.getOperationType().toString().equals("beq") ||
                inst.getOperationType().toString().equals("bne") ||
                inst.getOperationType().toString().equals("blt") ||
                inst.getOperationType().toString().equals("bgt")
        // inst.getOperationType().toString().equals("end")
        ) {
            return true;
        }
        return false;
    }

    public boolean performData(Instruction inst) {

        // System.out.println("Started checking for Data hazard");
        Instruction inst_ex = EX_MA_Latch.getInstruction();
        Instruction inst_ma = MA_RW_Latch.getInstruction();
        Instruction inst_rw = IF_OF_Latch.getIF_OFinstructionRW();

        if (inst.getOperationType() == OperationType.end) {
            // IF_OF_Latch.setOF_enable(false);
            // IF_EnableLatch.setIF_enable(false);
            return true;
        }

        // Checking for destination operation type which can have conflicts
        if (!SourceInstr(inst)) {
            return false;
        }

        boolean hazard = false;
        int rs1 = inst.getSourceOperand1().getValue();

        int rs2 = -1;
        if (inst.getSourceOperand2().getOperandType() == OperandType.Register) {
            rs2 = inst.getSourceOperand2().getValue();
        }

        // If instruction is store will will also check for rd
        if (inst.getOperationType() == OperationType.store) {
            rs2 = inst.getDestinationOperand().getValue();
        }

        // taking destination operands from other latches
        int d1 = -1;
        int d2 = -1;
        int d3 = -1;
        if (inst_ex != null) {
            if (inst_ex.getDestinationOperand().getOperandType() == OperandType.Register
                    && DestInstr(inst_ex)) {
                d1 = inst_ex.getDestinationOperand().getValue();
            }
        }
        if (inst_ma != null) {
            if (inst_ma.getDestinationOperand().getOperandType() == OperandType.Register && DestInstr(inst_ma)) {
                d2 = inst_ma.getDestinationOperand().getValue();
            }
        }
        if (inst_rw != null) {
            if (inst_rw.getDestinationOperand().getOperandType() == OperandType.Register
                    && DestInstr(inst_rw)) {
                d3 = inst_rw.getDestinationOperand().getValue();
            }
        }

        // When rs1 is register
        if (d1 == rs1) {
            hazard = true;
        } else if (d2 == rs1) {
            hazard = true;
        } else if (d3 == rs1) {
            hazard = true;
        }

        // Checking for rs2 (Perfect register needed)
        if (inst.getSourceOperand2().getOperandType() == OperandType.Register
                || inst.getOperationType() == OperationType.store) {
            if (d1 == rs2) {
                hazard = true;
            } else if (d2 == rs2) {
                hazard = true;
            } else if (d3 == rs2) {
                hazard = true;
            }
        }

        if (rs1 == 31) {
            if (inst_rw != null) {
                if (inst_rw.getOperationType() == OperationType.div
                        || inst_rw.getOperationType() == OperationType.divi) {
                    hazard = true;
                }
            }
            if (inst_ma != null) {
                if (inst_ma.getOperationType() == OperationType.div
                        || inst_ma.getOperationType() == OperationType.divi) {
                    hazard = true;
                }
            }
            if (inst_ex != null) {
                if (inst_ex.getOperationType() == OperationType.div
                        || inst_ex.getOperationType() == OperationType.divi) {
                    hazard = true;
                }
            }
        }

        // Register having value 31
        if (rs2 == 31 && inst.getSourceOperand2().getOperandType() == OperandType.Register
                || inst.getOperationType() == OperationType.store) {
            if (inst_rw != null) {
                if (inst_rw.getOperationType() == OperationType.div
                        || inst_rw.getOperationType() == OperationType.divi) {
                    hazard = true;
                }
            }
            if (inst_ma != null) {
                if (inst_ma.getOperationType() == OperationType.div
                        || inst_ma.getOperationType() == OperationType.divi) {
                    hazard = true;
                }
            }
            if (inst_ex != null) {
                if (inst_ex.getOperationType() == OperationType.div
                        || inst_ex.getOperationType() == OperationType.divi) {
                    hazard = true;
                }
            }
        }

        if (hazard) {
            System.out.println("Data Hazard Found");
            IF_EnableLatch.setIF_enable(false);
            OF_EX_Latch.setEX_enable(true);
            OF_EX_Latch.setInstruction(null);
            Statistics.setofStall(Statistics.getofStall() + 1);
            return true;
        }
        return false;
    }

}
