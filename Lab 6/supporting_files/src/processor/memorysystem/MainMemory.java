package processor.memorysystem;

import generic.*;
import generic.Event.EventType;
import processor.Clock;
import configuration.*;

public class MainMemory implements Element {
	int[] memory;

	public MainMemory() {
		memory = new int[65536];
	}

	public int getWord(int address) {
		return memory[address];
	}

	public void setWord(int address, int value) {
		memory[address] = value;
	}

	public String getContentsAsString(int startingAddress, int endingAddress) {
		if (startingAddress == endingAddress)
			return "";

		StringBuilder sb = new StringBuilder();
		sb.append("\nMain Memory Contents:\n\n");
		for (int i = startingAddress; i <= endingAddress; i++) {
			sb.append(i + "\t\t: " + memory[i] + "\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	@Override
	public void handleEvent(Event e) {
		if (e.getEventType() == EventType.MemoryRead) {
			MemoryReadEvent event = (MemoryReadEvent) e;
			Simulator.getEventQueue()
					.addEvent(new MemoryResponseEvent(Clock.getCurrentTime() + Configuration.mainMemoryLatency, this,
							event.getRequestingElement(), getWord(event.getAddressToReadFrom()),
							event.getAddressToReadFrom()));
		}
		if (e.getEventType() == EventType.MemoryWrite) {
			// assert e instanceof MemoryWriteEvent;
			MemoryWriteEvent event = (MemoryWriteEvent) e;
			setWord(event.getAddressToWriteTo(), event.getValue());
			Simulator.getEventQueue()
					.addEvent(new MemoryResponseEvent(Clock.getCurrentTime() + Configuration.mainMemoryLatency, this,
							event.getRequestingElement(), event.getValue(), event.getAddressToWriteTo()));
		}
	}
}
