package com.denver.aiport.service.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.denver.airport.exception.DepartureServiceException;
import com.denver.airport.service.DepartureService;
import com.denver.airport.service.impl.FlightDepartureService;

public class FlightDepartureServiceTest {
	private DepartureService departureService;
	@Before
	public void setUp() throws Exception {
		departureService = new FlightDepartureService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenInputsNull() throws DepartureServiceException {
		departureService.addDeparture(null, null, null, null);
	}
	
	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenInputsEmpty() throws DepartureServiceException {
		departureService.addDeparture(null, null, null, null);
	}
	
	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenFlightIdNull() throws DepartureServiceException {
		departureService.addDeparture(null, "A1", "MIA", "09:33");
	}
	
	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenFlightIdEmpty() throws DepartureServiceException {
		departureService.addDeparture("", "A1", "MIA", "09:33");
	}
	
	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenGateNull() throws DepartureServiceException {
		departureService.addDeparture("Flight1", null, "MIA", "09:33");
	}
	
	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenGateEmpty() throws DepartureServiceException {
		departureService.addDeparture("Flight1", "", "MIA", "09:33");
	}
	
	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenDestNull() throws DepartureServiceException {
		departureService.addDeparture("Flight1", "A2", null, "09:33");
	}
	
	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenDestEmpty() throws DepartureServiceException {
		departureService.addDeparture("Flight1", "A1", "", "09:33");
	}
	
	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenTimeNull() throws DepartureServiceException {
		departureService.addDeparture("Flight1", "A2", "LAX", null);
	}
	
	@Test(expected=DepartureServiceException.class)
	public void testAddDeparture_throwsException_whenTimeEmpty() throws DepartureServiceException {
		departureService.addDeparture("Flight1", "A1", "LAX", "");
	}
	
	@Test
	public void testAddDeparture_throwsNoException_whenPassedValidInputs() throws DepartureServiceException {
		departureService.addDeparture("Flight1", "A1", "LAX", "09:00");
		assertTrue(true);
	}
	
	@Test
	public void testHasValidData_ReturnFalse_whenNoDeptsAdded(){
		departureService = new FlightDepartureService(); 
		assertFalse(departureService.hasValidData());
	}
	
	@Test
	public void testHasValidData_ReturnTrue_whenDeptsAdded() throws DepartureServiceException{
		departureService = new FlightDepartureService();
		departureService.addDeparture("Flight1", "A1", "LAX", "09:00");
		assertTrue(departureService.hasValidData());
	}
	
	@Test
	public void testGetFlightDepartureDetail_ReturnNull_whenNoDeptsAdded(){
		departureService = new FlightDepartureService(); 
		assertNull(departureService.getFlightDepartureDetail("UA11"));
	}
	
	@Test
	public void testGetFlightDepartureDetail_ReturnValidDetail_whenDeptsAdded() throws DepartureServiceException{
		departureService = new FlightDepartureService();
		departureService.addDeparture("Flight2", "A1", "LAX", "09:00");
		departureService.addDeparture("Flight3", "A1", "LAX", "09:00");
		departureService.addDeparture("Flight4", "A1", "LAX", "09:00");
		assertNotNull(departureService.getFlightDepartureDetail("Flight2"));
		assertNotNull(departureService.getFlightDepartureDetail("Flight3"));
		assertNotNull(departureService.getFlightDepartureDetail("Flight4"));
	}
}
