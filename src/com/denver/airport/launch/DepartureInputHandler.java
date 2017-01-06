package com.denver.airport.launch;

import com.denver.airport.exception.AirportServiceException;
import com.denver.airport.exception.InputException;
import com.denver.airport.service.AirportService;
import com.denver.airport.util.Section;

public class DepartureInputHandler extends InputHandler {
	private final String DEPARTURE_INPUT_REGEX = "\\w+\\s\\w+\\s\\w+\\s([0-2][0-3]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
	private final String DEPARTURE_INPUT_ERROR_MSG = "Invalid Departure input : ";

	@Override
	public void handle(String input, AirportService service) throws InputException, AirportServiceException {
		boolean isValid = validate(input, DEPARTURE_INPUT_REGEX);
		if (!isValid)
			throw new InputException(DEPARTURE_INPUT_ERROR_MSG + input);
		String[] inputs = input.split(Section.INPUT_SEPARATOR);
		if(service != null)
			service.addDeparture(inputs[0], inputs[1], inputs[2], inputs[3]);
	}

}
