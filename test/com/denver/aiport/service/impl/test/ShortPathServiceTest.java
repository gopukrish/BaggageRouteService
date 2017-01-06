package com.denver.aiport.service.impl.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.denver.airport.domain.Node;
import com.denver.airport.domain.ShortPath;
import com.denver.airport.exception.NodeServiceException;
import com.denver.airport.service.NodeService;
import com.denver.airport.service.PathService;
import com.denver.airport.service.impl.AirportNodeService;
import com.denver.airport.service.impl.ShortPathService;

public class ShortPathServiceTest {
	private PathService pathService;
	@Before
	public void setUp() throws Exception {
		pathService = new ShortPathService();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCalculateShortPath_returnsNull_whenNodeMapNull() {
		ShortPath[][] shortPaths = pathService.calculateShortPath(null);
		assertNull(shortPaths);
	}
	
	@Test
	public void testCalculateShortPath_returnsZeroSize_whenNodeMapSizeZero() {
		ShortPath[][] shortPaths = pathService.calculateShortPath(new HashMap<>());
		assertNotNull(shortPaths);
		assertEquals(0, shortPaths.length);
	}
	
	@Test
	public void testCalculateShortPath_returnsValidPaths_whenValidNodeMapPassed() throws NodeServiceException {
		NodeService nodeService = new AirportNodeService();
		nodeService.addNode("A1", "A3", 2);
		nodeService.addNode("A3", "A4", 6);
		nodeService.addNode("A1", "A5", 1);
		Map<String, Node> nodes = new HashMap<>();
		nodes.put("A1", nodeService.getNode("A1"));
		nodes.put("A3", nodeService.getNode("A3"));
		nodes.put("A4", nodeService.getNode("A4"));
		nodes.put("A5", nodeService.getNode("A5"));
		ShortPath[][] shortPaths = pathService.calculateShortPath(nodes);
		assertNotNull(shortPaths);
		assertEquals(4, shortPaths.length);
		int length = shortPaths[0][0].getPathLength();
		assertEquals(0, length);
		length = shortPaths[0][1].getPathLength();
		assertEquals(2, length);
		length = shortPaths[0][2].getPathLength();
		assertEquals(8, length);
		length = shortPaths[0][3].getPathLength();
		assertEquals(1, length);
		
		length = shortPaths[1][0].getPathLength();
		assertEquals(2, length);
		length = shortPaths[1][1].getPathLength();
		assertEquals(0, length);
		length = shortPaths[1][2].getPathLength();
		assertEquals(6, length);
		length = shortPaths[1][3].getPathLength();
		assertEquals(3, length);
		
		length = shortPaths[2][0].getPathLength();
		assertEquals(8, length);
		length = shortPaths[2][1].getPathLength();
		assertEquals(6, length);
		length = shortPaths[2][2].getPathLength();
		assertEquals(0, length);
		length = shortPaths[2][3].getPathLength();
		assertEquals(9, length);
		
		length = shortPaths[3][0].getPathLength();
		assertEquals(1, length);
		length = shortPaths[3][1].getPathLength();
		assertEquals(3, length);
		length = shortPaths[3][2].getPathLength();
		assertEquals(9, length);
		length = shortPaths[3][3].getPathLength();
		assertEquals(0, length);
		
	}

}
