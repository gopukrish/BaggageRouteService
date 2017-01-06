package com.denver.airport.launch;

import com.denver.airport.exception.AirportServiceException;
import com.denver.airport.exception.InputException;
import com.denver.airport.service.AirportService;
import com.denver.airport.util.Validator;

public abstract class InputHandler {
	public abstract void handle(String input, AirportService service) throws InputException, AirportServiceException;

	protected Validator validator = (String input, String regex) -> {
		if (input != null && input.matches(regex))
			return true;
		return false;
	};

	protected boolean validate(String input, String regex) {
		return validator.validate(input, regex);
	}
}
