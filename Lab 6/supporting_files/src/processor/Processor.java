package processor;

import configuration.Configuration;
import processor.memorysystem.MainMemory;
import processor.memorysystem.Cache;
import processor.pipeline.EX_IF_LatchType;
import processor.pipeline.EX_MA_LatchType;
import processor.pipeline.Execute;
import processor.pipeline.IF_EnableLatchType;
import processor.pipeline.IF_OF_LatchType;
import processor.pipeline.InstructionFetch;
import processor.pipeline.MA_RW_LatchType;
import processor.pipeline.MemoryAccess;
import processor.pipeline.OF_EX_LatchType;
import processor.pipeline.OperandFetch;
import processor.pipeline.RegisterFile;
import processor.pipeline.RegisterWrite;

public class Processor {

	RegisterFile registerFile;
	MainMemory mainMemory;

	IF_EnableLatchType IF_EnableLatch;
	IF_OF_LatchType IF_OF_Latch;
	OF_EX_LatchType OF_EX_Latch;
	EX_MA_LatchType EX_MA_Latch;
	EX_IF_LatchType EX_IF_Latch;
	MA_RW_LatchType MA_RW_Latch;

	InstructionFetch IFUnit;
	OperandFetch OFUnit;
	Execute EXUnit;
	MemoryAccess MAUnit;
	RegisterWrite RWUnit;

	// Added cache
	Cache L1dCache;
	Cache L1iCache;

	public Processor() {
		registerFile = new RegisterFile();
		mainMemory = new MainMemory();

		IF_EnableLatch = new IF_EnableLatchType();
		IF_OF_Latch = new IF_OF_LatchType();
		OF_EX_Latch = new OF_EX_LatchType();
		EX_MA_Latch = new EX_MA_LatchType();
		EX_IF_Latch = new EX_IF_LatchType();
		MA_RW_Latch = new MA_RW_LatchType();

		IFUnit = new InstructionFetch(this, IF_EnableLatch, IF_OF_Latch, EX_IF_Latch, OF_EX_Latch, EX_MA_Latch,
				MA_RW_Latch); // added EX_MA_Latch, MA_RW_Latch
		OFUnit = new OperandFetch(this, IF_OF_Latch, OF_EX_Latch, IF_EnableLatch, EX_MA_Latch, MA_RW_Latch); // added
																												// IF_EnableLatch,
																												// EX_MA_Latch,
																												// MA_RW_Latch
		EXUnit = new Execute(this, OF_EX_Latch, EX_MA_Latch, EX_IF_Latch, IF_OF_Latch, MA_RW_Latch, IF_EnableLatch); // added
																														// IF_OF_Latch,
																														// MA_RW_Latch,
																														// IF_EnableLatch
		MAUnit = new MemoryAccess(this, EX_MA_Latch, MA_RW_Latch, OF_EX_Latch, IF_OF_Latch, IF_EnableLatch); // added
																												// OF_EX_Latch,
																												// IF_OF_Latch,
																												// IF_EnableLatch
		RWUnit = new RegisterWrite(this, MA_RW_Latch, IF_EnableLatch, IF_OF_Latch); // added IF_OF_Latch

		// added cache
		L1dCache = new Cache(this, "L1dCache", Configuration.L1d_associativity, Configuration.L1d_numberOfLines,
				Configuration.L1d_latency);
		L1iCache = new Cache(this, "L1iCache", Configuration.L1i_associativity, Configuration.L1i_numberOfLines,
				Configuration.L1i_latency);

	}

	public void printState(int memoryStartingAddress, int memoryEndingAddress) {
		System.out.println(registerFile.getContentsAsString());

		System.out.println(mainMemory.getContentsAsString(memoryStartingAddress, memoryEndingAddress));
	}

	public RegisterFile getRegisterFile() {
		return registerFile;
	}

	public void setRegisterFile(RegisterFile registerFile) {
		this.registerFile = registerFile;
	}

	public MainMemory getMainMemory() {
		return mainMemory;
	}

	public void setMainMemory(MainMemory mainMemory) {
		this.mainMemory = mainMemory;
	}

	public InstructionFetch getIFUnit() {
		return IFUnit;
	}

	public OperandFetch getOFUnit() {
		return OFUnit;
	}

	public Execute getEXUnit() {
		return EXUnit;
	}

	public MemoryAccess getMAUnit() {
		return MAUnit;
	}

	public RegisterWrite getRWUnit() {
		return RWUnit;
	}

	public Cache getL1dCache() {
		return L1dCache;
	}

	public Cache getL1iCache() {
		return L1iCache;
	}

}
