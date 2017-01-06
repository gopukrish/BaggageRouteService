package com.denver.aiport.launch.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import com.denver.airport.exception.AirportServiceException;
import com.denver.airport.exception.InputException;
import com.denver.airport.launch.BagsInputHandler;
import com.denver.airport.launch.InputHandler;
import com.denver.airport.service.impl.DenverAirportService;

public class BagsInputHandlerTest {
	private InputHandler inputHandler;
	
	@Before
	public void setUp() throws Exception {
		inputHandler = new BagsInputHandler();
	}

	@After
	public void tearDown() throws Exception {
		inputHandler = null;
	}

	@Test(expected=InputException.class)
	public void testHandle_ThrowsException_whenPassedInvalidInput() throws InputException, AirportServiceException {
		inputHandler.handle("xby adsa", new DenverAirportService());
	}
	
	@Test(expected=InputException.class)
	public void testHandle_ThrowsException_whenPassedNullInput() throws InputException, AirportServiceException {
		inputHandler.handle(null, new DenverAirportService());
	}
	
	@Test(expected=InputException.class)
	public void testHandle_ThrowsException_whenPassedEmptyInput() throws InputException, AirportServiceException {
		inputHandler.handle("", new DenverAirportService());
	}
	
	@Test
	public void testHandle_ThrowsNoException_whenPassedValidInput() throws InputException, AirportServiceException {
		inputHandler.handle("0001 Concourse_A_Ticketing UA12", new DenverAirportService());
		assertTrue(true);
	}
}
