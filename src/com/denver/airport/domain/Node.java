package com.denver.airport.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
	private String name;
	private int index;
	private Map<String, Integer> connectedNodes = new HashMap<>();
	
	public Node(String nodeName, String destName, int index, int hours){
		setName(nodeName);
		setIndex(index);
		addConnectedNode(destName, hours);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Map<String, Integer> getConnectedNodes() {
		return connectedNodes;
	}
	public Map<String, Integer> getConnectedNodes(List<String> excludeNodes) {
		Map<String, Integer> tempConnNodes = new HashMap<>(connectedNodes);
		excludeNodes.forEach(node -> tempConnNodes.remove(node));
		return tempConnNodes;
	}
	public void setConnectedNodes(Map<String, Integer> connectedNodes) {
		this.connectedNodes = connectedNodes;
	}
	
	public void addConnectedNode(String node,int hours){
		this.connectedNodes.put(node, hours);
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", index=" + index + ", connectedNodes=" + connectedNodes
				+ "]";
	}
	
}
