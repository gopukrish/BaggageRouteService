package com.denver.aiport.service.impl.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.denver.airport.exception.BaggageServiceException;
import com.denver.airport.exception.DepartureServiceException;
import com.denver.airport.exception.NodeServiceException;
import com.denver.airport.service.BaggageService;
import com.denver.airport.service.DepartureService;
import com.denver.airport.service.NodeService;
import com.denver.airport.service.impl.AirportNodeService;
import com.denver.airport.service.impl.BaggageDetailService;
import com.denver.airport.service.impl.FlightDepartureService;

public class BaggageDetailServiceTest {
	BaggageService bagService;
	
	@Before
	public void setUp() throws Exception {
		bagService = new BaggageDetailService();
	}

	@After
	public void tearDown() throws Exception {
		bagService = null;
	}

	@Test(expected=BaggageServiceException.class)
	public void testAddBaggage_throwsException_whenInputsNull() throws BaggageServiceException {
		bagService.addBaggage(null, null, null);
	}
	
	@Test(expected=BaggageServiceException.class)
	public void testAddBaggage_throwsException_whenInputsEmpty() throws BaggageServiceException {
		bagService.addBaggage("", "", "");
	}
	
	@Test(expected=BaggageServiceException.class)
	public void testAddBaggage_throwsException_whenBaggageIdNull() throws BaggageServiceException {
		bagService.addBaggage(null, "A1", "Flight1");
	}
	
	@Test(expected=BaggageServiceException.class)
	public void testAddBaggage_throwsException_whenBaggageIdEmpty() throws BaggageServiceException {
		bagService.addBaggage("", "A1", "Flight1");
	}
	
	@Test(expected=BaggageServiceException.class)
	public void testAddBaggage_throwsException_whenEntryPointNull() throws BaggageServiceException {
		bagService.addBaggage("Bag1", null, "Flight1");
	}
	
	@Test(expected=BaggageServiceException.class)
	public void testAddBaggage_throwsException_whenEntryPointEmpty() throws BaggageServiceException {
		bagService.addBaggage("Bag1", "", "Flight1");
	}
	
	@Test(expected=BaggageServiceException.class)
	public void testAddBaggage_throwsException_whenFlightIdNull() throws BaggageServiceException {
		bagService.addBaggage("Bag1", "Gate1", null);
	}
	
	@Test(expected=BaggageServiceException.class)
	public void testAddBaggage_throwsException_whenFlightIdEmpty() throws BaggageServiceException {
		bagService.addBaggage("Bag1", "Gate1", "");
	}
	
	@Test
	public void testAddBaggage_throwsNoException_whenPassedValidInput() throws BaggageServiceException {
		bagService.addBaggage("Bag1", "Gate1", "Flight1");
	}
	
	@Test
	public void testAssignShortPathToBaggage_throwsNoException_whenPassedServicesNull() throws BaggageServiceException {
		bagService.assignShortPathToBaggage(null, null);
	}
	
	@Test
	public void testAssignShortPathToBaggage_throwsNoException_whenPassedNodeServiceNull() throws BaggageServiceException {
		bagService.assignShortPathToBaggage(null, new FlightDepartureService());
	}
	
	@Test
	public void testAssignShortPathToBaggage_throwsNoException_whenPassedDepartureServiceNull() throws BaggageServiceException {
		bagService.assignShortPathToBaggage(new AirportNodeService(), null);
	}
	
	@Test
	public void testAssignShortPathToBaggage_throwsNoException_whenNoBagsAdded() throws BaggageServiceException {
		bagService.assignShortPathToBaggage(new AirportNodeService(), new FlightDepartureService());
	}
	
	@Test
	public void testGetAllBaggageShortestPath_returnEmptyList_whenNoBagsAdded() throws BaggageServiceException {
		List<String> bagsLst = bagService.getAllBaggageShortestPath();
		assertNotNull(bagsLst);
		assertEquals(0, bagsLst.size());
	}
	
	@Test
	public void testGetAllBaggageShortestPath_returnNoRouteFound_whenNoNodesAdded() throws BaggageServiceException {
		bagService = new BaggageDetailService();
		bagService.addBaggage("Bag1", "Gate1", "Flight1");
		bagService.addBaggage("Bag2", "Gate2", "Flight2");
		List<String> bagsLst = bagService.getAllBaggageShortestPath();
		assertNotNull(bagsLst);
		assertEquals(2, bagsLst.size());
		assertTrue( bagsLst.get(0).contains(BaggageService.NO_OPTIMAL_ROUTE_FOUND));
		assertTrue( bagsLst.get(1).contains(BaggageService.NO_OPTIMAL_ROUTE_FOUND));
	}
	
	@Test
	public void testGetAllBaggageShortestPath_returnValidPaths_whenNodesDepartureAdded() throws BaggageServiceException, NodeServiceException, DepartureServiceException {
		NodeService nodeService = new AirportNodeService();
		nodeService.addNode("A1", "A2", 3);
		nodeService.addNode("A2", "A3", 1);
		nodeService.addNode("A5", "A4", 4);
		nodeService.addNode("A4", "A3", 3);
		nodeService.addNode("A4", "A6", 1);
		nodeService.addNode("A6", "A7", 2);
		
		DepartureService departureService = new FlightDepartureService();
		departureService.addDeparture("Flight1", "A1", "LAX", "00:50");
		departureService.addDeparture("Flight2", "A2", "JFK", "01:00");
		departureService.addDeparture("Flight3", "A3", "LAX", "03:30");
		departureService.addDeparture("Flight4", "A4", "LAX", "04:00");
		departureService.addDeparture("Flight5", "A5", "LAX", "02:00");
		departureService.addDeparture("Flight6", "A6", "LAX", "08:20");
		departureService.addDeparture("Flight7", "A7", "LAX", "11:10");
		
		bagService = new BaggageDetailService();
		bagService.addBaggage("Bag1", "A6", "Flight1");
		bagService.addBaggage("Bag2", "A7", "Flight2");
		bagService.addBaggage("Bag3", "A3", "Flight7");
		bagService.addBaggage("Bag4", "A5", "Flight1");
		bagService.addBaggage("Bag5", "A1", "Flight6");
		
		nodeService.calculateShortPathForNodes();
		bagService.assignShortPathToBaggage(nodeService, departureService);
		List<String> bagsLst = bagService.getAllBaggageShortestPath();
		assertNotNull(bagsLst);
		assertEquals(5, bagsLst.size());
		assertNotNull(bagService.getBaggageShortPath("Bag1"));
		assertNotNull(bagService.getBaggageShortPath("Bag2"));
		assertNotNull(bagService.getBaggageShortPath("Bag3"));
		assertNotNull(bagService.getBaggageShortPath("Bag4"));
		assertNotNull(bagService.getBaggageShortPath("Bag5"));
		assertEquals(8, bagService.getBaggageShortPath("Bag1").getPathLength());
		assertEquals(7, bagService.getBaggageShortPath("Bag2").getPathLength());
		assertEquals(6, bagService.getBaggageShortPath("Bag3").getPathLength());
		assertEquals(11, bagService.getBaggageShortPath("Bag4").getPathLength());
		assertEquals(8, bagService.getBaggageShortPath("Bag5").getPathLength());
	}
	
	@Test
	public void testGetBaggageShortPath_returnsNull_whenNoBaggageFound() throws BaggageServiceException {
		bagService = new BaggageDetailService();
		bagService.addBaggage("Bag1", "Gate1", "Flight1");
		bagService.addBaggage("Bag2", "Gate2", "Flight2");
		assertNull( bagService.getBaggageShortPath("Bag5"));
	}
}
