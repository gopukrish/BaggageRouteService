package com.denver.airport.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.denver.airport.domain.FlightDepartureDetail;
import com.denver.airport.exception.DepartureServiceException;
import com.denver.airport.service.DepartureService;
import com.denver.airport.util.StringUtils;

public class FlightDepartureService implements DepartureService {
	private Map<String, FlightDepartureDetail> departureDetails;

	public FlightDepartureService() {
		departureDetails = new HashMap<>();
	}

	@Override
	public void addDeparture(String flightId, String gate, String destination, String flightTime) throws DepartureServiceException{
		if(!StringUtils.isEmpty(flightId) && !StringUtils.isEmpty(gate)
				&& !StringUtils.isEmpty(destination) && !StringUtils.isEmpty(flightTime)){
		FlightDepartureDetail flightDepartureDetail = 
					new FlightDepartureDetail(flightId, gate, destination, flightTime);
			departureDetails.put(flightId, flightDepartureDetail);
		}else{
			throw new DepartureServiceException(ADD_DEPARTURE_ERROR_MSG); 
		}
	}
	
	@Override
	public FlightDepartureDetail getFlightDepartureDetail(String flightId){
		return departureDetails.get(flightId);
	}

	@Override
	public boolean hasValidData() {
		if(departureDetails != null && departureDetails.size() > 0)
			return true;
		return false;
	}

}
