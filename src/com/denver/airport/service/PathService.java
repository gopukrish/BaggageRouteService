package com.denver.airport.service;

import java.util.Map;

import com.denver.airport.domain.Node;
import com.denver.airport.domain.ShortPath;

public interface PathService {
	int ZERO_PATH_LENGTH = 0;
	String PATH_SEPARATOR = " ";
	
	public ShortPath[][] calculateShortPath(Map<String, Node> nodeMap);
}
