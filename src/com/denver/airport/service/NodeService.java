package com.denver.airport.service;

import com.denver.airport.domain.Node;
import com.denver.airport.domain.ShortPath;
import com.denver.airport.exception.NodeServiceException;

public interface NodeService {
	String ADD_NODE_ERROR = "Error in adding node : Source and Destination Node should not be null/empty/same";
	
	void addNode(String nodeName, String destNodeName, int hours) throws NodeServiceException;
	
	Node getNode(String node);
	
	ShortPath getShortPath(String srcNode, String destNode);

	void calculateShortPathForNodes();
	
	boolean hasValidData();
}
