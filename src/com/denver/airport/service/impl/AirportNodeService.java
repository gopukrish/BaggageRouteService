package com.denver.airport.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.denver.airport.domain.Node;
import com.denver.airport.domain.ShortPath;
import com.denver.airport.exception.NodeServiceException;
import com.denver.airport.service.NodeService;
import com.denver.airport.service.PathService;
import com.denver.airport.util.StringUtils;

public class AirportNodeService implements NodeService {
	private Map<String, Node> nodeMap = null;
	private int nodeIndex = 0;
	private ShortPath[][] shortPaths;
	private PathService shortPathService;

	public AirportNodeService() {
		this(new ShortPathService());
	}

	public AirportNodeService(PathService pathService) {
		nodeMap = new HashMap<>();
		shortPathService = pathService;
	}

	private void createNode(String nodeName, String destNodeName, int hours) {
		if (nodeMap.containsKey(nodeName))
			nodeMap.get(nodeName).addConnectedNode(destNodeName, hours);
		else
			nodeMap.put(nodeName, new Node(nodeName, destNodeName, nodeIndex++, hours));
	}

	public void addNode(String srcNode, String destNode, int hours) throws NodeServiceException {
		if(!StringUtils.isEmpty(srcNode) && !StringUtils.isEmpty(destNode)
				&& !srcNode.equalsIgnoreCase(destNode)){
				createNode(srcNode, destNode, hours);
				createNode(destNode, srcNode, hours);
		}
		else
			throw new NodeServiceException(ADD_NODE_ERROR);
	}
	
	@Override
	public void calculateShortPathForNodes() {
		if(shortPathService != null)
			shortPaths = shortPathService.calculateShortPath(nodeMap);
	}

	private int getNodeIndex(String node) {
		return nodeMap.get(node).getIndex();
	}

	@Override
	public ShortPath getShortPath(String srcNode, String destNode) {
		if(shortPaths != null)
			return shortPaths[getNodeIndex(srcNode)][getNodeIndex(destNode)];
		return null;
	}
	
	@Override
	public Node getNode(String node) {
		return nodeMap.get(node);
	}

	@Override
	public boolean hasValidData() {
		if(nodeMap != null && nodeMap.size() > 0)
			return true;
		return false;
	}
}
