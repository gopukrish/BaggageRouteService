package com.denver.aiport.service.impl.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.denver.airport.domain.ShortPath;
import com.denver.airport.exception.NodeServiceException;
import com.denver.airport.service.NodeService;
import com.denver.airport.service.impl.AirportNodeService;

public class AirportNodeServiceTest {
	private NodeService nodeService;

	@Before
	public void setUp() throws Exception {
		nodeService = new AirportNodeService();
	}

	@After
	public void tearDown() throws Exception {
		nodeService = null;
	}

	@Test(expected = NodeServiceException.class)
	public void testAddNode_ThrowsException_whenSrcDestNodePassedNull() throws NodeServiceException {
		nodeService.addNode(null, null, 0);
	}

	@Test(expected = NodeServiceException.class)
	public void testAddNode_ThrowsException_whenSrcDestNodePassedEmpty() throws NodeServiceException {
		nodeService.addNode("", "", 0);
	}

	@Test(expected = NodeServiceException.class)
	public void testAddNode_ThrowsException_whenSrcNodePassedNull() throws NodeServiceException {
		nodeService.addNode(null, "A5", 0);
	}

	@Test(expected = NodeServiceException.class)
	public void testAddNode_ThrowsException_whenSrcNodePassedEmpty() throws NodeServiceException {
		nodeService.addNode("", "A5", 0);
	}
	
	@Test(expected = NodeServiceException.class)
	public void testAddNode_ThrowsException_whenSrcAndDestNodeSame() throws NodeServiceException {
		nodeService.addNode("A5", "A5", 0);
	}

	@Test(expected = NodeServiceException.class)
	public void testAddNode_ThrowsException_whenDestNodePassedNull() throws NodeServiceException {
		nodeService.addNode("A5", null, 0);
	}

	@Test(expected = NodeServiceException.class)
	public void testAddNode_ThrowsException_whenDestNodePassedEmpty() throws NodeServiceException {
		nodeService.addNode("A5", " ", 0);
	}

	@Test
	public void testAddNode_ThrowsNoException_whenPassedValidInput() throws NodeServiceException {
		nodeService.addNode("A5", "A1", 0);
		assertTrue(true);
	}

	@Test
	public void testAddNode_hasTwoNodes_whenSrcAndDestNodesAdded() throws NodeServiceException {
		nodeService = new AirportNodeService();
		nodeService.addNode("A5", "A1", 2);
		assertNotNull(nodeService.getNode("A5"));
		assertNotNull(nodeService.getNode("A1"));
		int length = nodeService.getNode("A5").getConnectedNodes().get("A1");
		assertEquals(2, length);
		length = nodeService.getNode("A1").getConnectedNodes().get("A5");
		assertEquals(2, length);
	}

	@Test
	public void testHasValidData_ReturnsFalse_whenNoNodesAdded() throws NodeServiceException {
		nodeService = new AirportNodeService();
		assertFalse(nodeService.hasValidData());
	}

	@Test
	public void testHasValidData_ReturnsTrue_whenNodesAdded() throws NodeServiceException {
		nodeService = new AirportNodeService();
		nodeService.addNode("A1", "A2", 2);
		assertTrue(nodeService.hasValidData());
	}

	@Test
	public void testGetNode_ReturnsNull_whenNoNodesAdded() throws NodeServiceException {
		nodeService = new AirportNodeService();
		assertNull(nodeService.getNode("A1"));
	}

	@Test
	public void testGetNode_ReturnsNode_whenNodesAdded() throws NodeServiceException {
		nodeService = new AirportNodeService();
		nodeService.addNode("A2", "A3", 2);
		assertNotNull(nodeService.getNode("A2"));
		assertNotNull(nodeService.getNode("A3"));
	}
	
	@Test
	public void testGetShortPath_ReturnsNull_whenShortPathNotCalulated() throws NodeServiceException {
		nodeService = new AirportNodeService();
		nodeService.addNode("A1", "A2", 2);
		assertNotNull(nodeService.getNode("A1"));
		assertNotNull(nodeService.getNode("A2"));
		ShortPath sp = nodeService.getShortPath("A1", "A2");
		assertNull(sp);
	}
	
	@Test
	public void testGetShortPath_ReturnsValidPath_whenShortPathCalulated() throws NodeServiceException {
		NodeService nodeService = new AirportNodeService();
		nodeService.addNode("A1", "A2", 5);
		assertNotNull(nodeService.getNode("A1"));
		assertNotNull(nodeService.getNode("A2"));
		nodeService.calculateShortPathForNodes();
		ShortPath sp = nodeService.getShortPath("A1", "A2");
		assertNotNull(sp);
		assertEquals(5, sp.getPathLength());
		String path = new String("A1 A2 ");
		assertTrue(path.equals(sp.getShortPath()));
	}
	
	@Test(timeout=1000)
	public void testCalculateShortPathForNodes_TimeOut_whenNoNodesAdded() throws NodeServiceException {
		NodeService nodeService = new AirportNodeService();
		nodeService.calculateShortPathForNodes();
	}
	
	@Test(timeout=1000)
	public void testCalculateShortPathForNodes_TimeOut_whenTwoNodesAdded() throws NodeServiceException {
		NodeService nodeService = new AirportNodeService();
		nodeService.addNode("A1", "A2", 1);
		nodeService.calculateShortPathForNodes();
	}
	
	@Test(timeout=1000)
	public void testCalculateShortPathForNodes_TimeOut_whenFiveNodesAdded() throws NodeServiceException {
		NodeService nodeService = new AirportNodeService();
		nodeService.addNode("A1", "A2", 1);
		nodeService.addNode("A2", "A3", 1);
		nodeService.addNode("A4", "A5", 1);
		nodeService.addNode("A5", "A6", 1);
		nodeService.addNode("A6", "A7", 1);
		nodeService.calculateShortPathForNodes();
	}
	
	@Test(timeout=1000)
	public void testCalculateShortPathForNodes_TimeOut_whenTenNodesAdded() throws NodeServiceException {
		NodeService nodeService = new AirportNodeService();
		for(int i=1; i<=10;i++){
			String srcNode = "A" + i;
			String destNode = "A" + (i+1);
			nodeService.addNode(srcNode, destNode, i+1);
		}
		nodeService.calculateShortPathForNodes();
	}
	
	@Test(timeout=1000)
	public void testCalculateShortPathForNodes_TimeOut_when100NodesAdded() throws NodeServiceException {
		NodeService nodeService = new AirportNodeService();
		for(int i=1; i<=100;i++){
			String srcNode = "A" + i;
			String destNode = "A" + (i+1);
			nodeService.addNode(srcNode, destNode, i+1);
		}
		nodeService.calculateShortPathForNodes();
	}
}
