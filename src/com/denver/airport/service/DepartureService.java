package com.denver.airport.service;

import com.denver.airport.domain.FlightDepartureDetail;
import com.denver.airport.exception.DepartureServiceException;

public interface DepartureService {
	String ADD_DEPARTURE_ERROR_MSG = "Error in adding flight departure details : Inputs should not empty/null";
	
	public void addDeparture(String flightId, String gate, String destination, String flightTime) throws DepartureServiceException;
	
	public FlightDepartureDetail getFlightDepartureDetail(String flightId);
	
	boolean hasValidData();
}
