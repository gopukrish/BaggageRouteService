package com.denver.airport.launch;

import com.denver.airport.exception.AirportServiceException;
import com.denver.airport.exception.InputException;
import com.denver.airport.service.AirportService;
import com.denver.airport.util.Section;

public class ConveyorInputHandler extends InputHandler {
	private final String CONVEYOR_INPUT_REGEX = "\\w+\\s\\w+\\s\\d+";
	private final String CONVEYOR_ERROR_MSG = "Invalid Conveyor input : ";
	
	@Override
	public void handle(String input, AirportService service) throws InputException, AirportServiceException {
		boolean isValid = validate(input, CONVEYOR_INPUT_REGEX);
		if(!isValid)
			throw new InputException(CONVEYOR_ERROR_MSG + input);
		String[] inputs = input.split(Section.INPUT_SEPARATOR);
		int hour = Integer.parseInt(inputs[2]);
		if(service != null)
			service.addNode(inputs[0], inputs[1], hour);
	}
}
