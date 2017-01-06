package com.denver.aiport.launch.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.denver.airport.exception.AirportServiceException;
import com.denver.airport.exception.InputException;
import com.denver.airport.launch.DepartureInputHandler;
import com.denver.airport.launch.InputHandler;
import com.denver.airport.service.impl.DenverAirportService;

public class DepartureInputHandlerTest {

	private InputHandler inputHandler;

	@Before
	public void setUp() throws Exception {
		inputHandler = new DepartureInputHandler();
	}

	@After
	public void tearDown() throws Exception {
		inputHandler = null;
	}

	@Test(expected = InputException.class)
	public void testHandle_ThrowsException_whenPassedInvalidInput() throws InputException, AirportServiceException {
		inputHandler.handle("xby adsa", new DenverAirportService());
	}

	@Test(expected = InputException.class)
	public void testHandle_ThrowsException_whenPassedNullInput() throws InputException, AirportServiceException {
		inputHandler.handle(null, new DenverAirportService());
	}

	@Test(expected = InputException.class)
	public void testHandle_ThrowsException_whenPassedEmptyInput() throws InputException, AirportServiceException {
		inputHandler.handle("", new DenverAirportService());
	}

	@Test
	public void testHandle_ThrowsNoException_whenPassedValidInput() throws InputException, AirportServiceException {
		inputHandler.handle("UA10 A1 MIA 08:00", new DenverAirportService());
		assertTrue(true);
	}
	
	@Test(expected = InputException.class)
	public void testHandle_ThrowsNoException_whenPassedInvalidHourInput() throws InputException, AirportServiceException {
		inputHandler.handle("UA10 A1 MIA 24:00", new DenverAirportService());
	}
	
	@Test(expected = InputException.class)
	public void testHandle_ThrowsNoException_whenPassedInvalidMinutesInput() throws InputException, AirportServiceException {
		inputHandler.handle("UA10 A1 MIA 03:60", new DenverAirportService());
	}

}
