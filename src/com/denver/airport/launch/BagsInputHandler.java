package com.denver.airport.launch;

import com.denver.airport.exception.AirportServiceException;
import com.denver.airport.exception.InputException;
import com.denver.airport.service.AirportService;
import com.denver.airport.util.Section;

public class BagsInputHandler extends InputHandler {
	private final String BAG_INPUT_REGEX = "\\w+\\s\\w+\\s\\w+";
	private final String BAG_INPUT_ERROR_MSG = "Invalid Bag input : ";

	@Override
	public void handle(String input, AirportService service) throws InputException, AirportServiceException {
		boolean isValid = validate(input, BAG_INPUT_REGEX);
		if (!isValid)
			throw new InputException(BAG_INPUT_ERROR_MSG + input);
		String[] inputs = input.split(Section.INPUT_SEPARATOR);
		if(service != null)
			service.addBaggage(inputs[0], inputs[1], inputs[2]);
	}

}
