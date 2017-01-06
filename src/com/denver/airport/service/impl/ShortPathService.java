package com.denver.airport.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.denver.airport.domain.Node;
import com.denver.airport.domain.ShortPath;
import com.denver.airport.service.PathService;

public class ShortPathService implements PathService {
	private ShortPath[][] shortPaths;
	private Stack<Node> nodes = new Stack<>();
	
	public ShortPath[][] calculateShortPath(Map<String, Node> nodeMap) {
		if(nodeMap != null){
			shortPaths = new ShortPath[nodeMap.size()][nodeMap.size()];
			for (String node : nodeMap.keySet()) {
				int index = nodeMap.get(node).getIndex();
				setShortPathInBiDirArray(index, index, new ShortPath(ZERO_PATH_LENGTH, node));
				nodes.removeAllElements();
				nodes.push(nodeMap.get(node));
				doDFSToFindShortPath(nodeMap.get(node), nodeMap);
			}
		}
		return shortPaths;
	}
	
	private String getPath() {
		StringBuffer path = new StringBuffer();
		List<Node> nodesLst = new ArrayList<>(nodes);
		nodesLst.forEach((node) -> path.append(node.getName()).append(PATH_SEPARATOR));
		return path.toString();
	}

	private int getPathLen(Map<String, Node> nodeMap) {
		int pathLen = 0;
		List<Node> nodesLst = new ArrayList<>(nodes);
		for (int i = 1; i < nodesLst.size(); i++) {
			String currentNode = nodesLst.get(i).getName();
			String previousNode = nodesLst.get(i - 1).getName();
			pathLen = pathLen + nodeMap.get(currentNode).getConnectedNodes().get(previousNode);
		}
		return pathLen;
	}

	private void setShortPathInBiDirArray(int srcIndex, int destIndex, ShortPath sp) {
		if (shortPaths[srcIndex][destIndex] == null
				|| shortPaths[srcIndex][destIndex].getPathLength() > sp.getPathLength()) {
			shortPaths[srcIndex][destIndex] = sp;
		}
	}

	private List<String> getNodesListFromStack() {
		List<String> lst = new ArrayList<>();
		nodes.forEach((node) -> lst.add(node.getName()));
		return lst;
	}

	private void doDFSToFindShortPath(Node srcNode, Map<String, Node> nodeMap) {
		Set<String> connNodes = srcNode.getConnectedNodes(getNodesListFromStack()).keySet();
		for (String connNode : connNodes) {
			nodes.push(nodeMap.get(connNode));
			ShortPath sp = new ShortPath(getPathLen(nodeMap), getPath());
			setShortPathInBiDirArray(nodes.firstElement().getIndex(), nodeMap.get(connNode).getIndex(), sp);
			doDFSToFindShortPath(nodeMap.get(connNode), nodeMap);
		}
		nodes.pop();
	}
}
