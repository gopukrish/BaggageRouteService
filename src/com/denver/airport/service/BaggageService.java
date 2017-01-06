package com.denver.airport.service;

import java.util.List;

import com.denver.airport.domain.ShortPath;
import com.denver.airport.exception.BaggageServiceException;

public interface BaggageService {
	String ADD_ERROR_MSG = "Error in adding Baggage : Inputs should not be empty/null";
	String NO_OPTIMAL_ROUTE_FOUND = "No Optimal route found";
	String OPTIMAL_ROUTE_FETCH_ERROR = "Error in fethcing optimal route";
	String OPTIMAL_ROUTE_ASSIGN_ERROR = "Error in assigning short path"; 
	
	void addBaggage(String baggageId, String entryPoint, String flightId) throws BaggageServiceException;

	void assignShortPathToBaggage(NodeService nodeService, DepartureService departureService)
			throws BaggageServiceException;

	List<String> getAllBaggageShortestPath() throws BaggageServiceException;

	boolean hasValidData();

	ShortPath getBaggageShortPath(String baggageId);
}
