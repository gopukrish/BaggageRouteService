package com.denver.airport.domain;

public class ShortPath {
	private String shortPath;
	private int pathLength;
	
	public ShortPath(){
	}
	
	public ShortPath(int pathLength, String shortPath){
		setPathLength(pathLength);
		setShortPath(shortPath);
	}
	
	public String getShortPath() {
		return shortPath;
	}

	public void setShortPath(String shortPath) {
		this.shortPath = shortPath;
	}

	public int getPathLength() {
		return pathLength;
	}

	public void setPathLength(int pathLength) {
		this.pathLength = pathLength;
	}

	@Override
	public String toString() {
		return "ShortPath [shortPath = " + shortPath + ", pathLength = " + pathLength + "]";
	}
	
}
