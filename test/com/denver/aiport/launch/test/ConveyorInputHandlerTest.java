package com.denver.aiport.launch.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.denver.airport.exception.AirportServiceException;
import com.denver.airport.exception.InputException;
import com.denver.airport.launch.ConveyorInputHandler;
import com.denver.airport.launch.InputHandler;
import com.denver.airport.service.impl.DenverAirportService;

public class ConveyorInputHandlerTest {
	private InputHandler inputHandler;

	@Before
	public void setUp() throws Exception {
		inputHandler = new ConveyorInputHandler();
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
		inputHandler.handle("Concourse_A_Ticketing A5 5", new DenverAirportService());
		assertTrue(true);
	}
	
	@Test(expected = InputException.class)
	public void testHandle_ThrowsException_whenPassedInvalidHourInput() throws InputException, AirportServiceException {
		inputHandler.handle("Concourse_A_Ticketing A5 five", new DenverAirportService());
	}
}
