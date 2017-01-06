package com.denver.airport.launch;

import com.denver.airport.util.Section;

public class InputHandlerFactory {
	
	public InputHandlerFactory() {
		
	}
	
	public InputHandler getInputHandler(String section) {
		if (Section.BAGS_SECTION.equalsIgnoreCase(section))
			return new BagsInputHandler();
		if (Section.DEPARTURES_SECTION.equalsIgnoreCase(section))
			return new DepartureInputHandler();
		if (Section.CONVEYOR_SYSTEM_SECTION.equalsIgnoreCase(section))
			return new ConveyorInputHandler();
		return null;
	}
}