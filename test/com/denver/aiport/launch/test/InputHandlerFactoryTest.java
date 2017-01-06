package com.denver.aiport.launch.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.denver.airport.launch.BagsInputHandler;
import com.denver.airport.launch.ConveyorInputHandler;
import com.denver.airport.launch.DepartureInputHandler;
import com.denver.airport.launch.InputHandler;
import com.denver.airport.launch.InputHandlerFactory;

public class InputHandlerFactoryTest {
	private InputHandlerFactory inputHandlerFactory;
	
	@Before
	public void setUp() throws Exception {
		inputHandlerFactory =  new InputHandlerFactory();
	}

	@After
	public void tearDown() throws Exception {
		inputHandlerFactory = null;
	}

	@Test
	public void testGetInputHandler_ReturnNull_WhenPassedNull() {
		InputHandler inputHandler = inputHandlerFactory.getInputHandler(null);
		assertNull(inputHandler);
	}
	
	@Test
	public void testGetInputHandler_ReturnNull_WhenPassedEmptyString() {
		InputHandler inputHandler = inputHandlerFactory.getInputHandler("");
		assertNull(inputHandler);
	}
	
	@Test
	public void testGetInputHandler_Return_BagsInputHandler_WhenPassedBags() {
		InputHandler inputHandler = inputHandlerFactory.getInputHandler("Bags");
		assertTrue(inputHandler instanceof BagsInputHandler);
	}
	
	@Test
	public void testGetInputHandler_Return_ConveyorInputHandler_WhenPassedConveyorSystem() {
		InputHandler inputHandler = inputHandlerFactory.getInputHandler("Conveyor System");
		assertTrue(inputHandler instanceof ConveyorInputHandler);
	}
	
	@Test
	public void testGetInputHandler_Return_BagsInputHandler_WhenPassedDepartures() {
		InputHandler inputHandler = inputHandlerFactory.getInputHandler("Departures");
		assertTrue(inputHandler instanceof DepartureInputHandler);
	}
	
	@Test
	public void testGetInputHandler_Return_BagsInputHandler_WhenPassedOtherValues() {
		InputHandler inputHandler = inputHandlerFactory.getInputHandler("xyz");
		assertNull(inputHandler);
	}
}
