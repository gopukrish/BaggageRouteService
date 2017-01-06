package com.denver.airport.service.impl;

import java.util.List;

import com.denver.airport.exception.AirportServiceException;
import com.denver.airport.exception.BaggageServiceException;
import com.denver.airport.exception.DepartureServiceException;
import com.denver.airport.exception.NodeServiceException;
import com.denver.airport.service.AirportService;
import com.denver.airport.service.BaggageService;
import com.denver.airport.service.DepartureService;
import com.denver.airport.service.NodeService;

public class DenverAirportService implements AirportService {
	private BaggageService baggageService;
	private DepartureService departureService;
	private NodeService nodeService;

	public DenverAirportService() {
		this(new BaggageDetailService(), new FlightDepartureService(), new AirportNodeService());
	}

	public DenverAirportService(BaggageService baggageService, DepartureService departureService,
			NodeService nodeService) {
		this.baggageService = baggageService;
		this.departureService = departureService;
		this.nodeService = nodeService;
	}

	@Override
	public void addNode(String srcNode, String destNode, int hours) throws AirportServiceException {
		try {
			nodeService.addNode(srcNode, destNode, hours);
		} catch (NodeServiceException e) {
			throw new AirportServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void addDeparture(String flightId, String gate, String destination, String flightTime)
			throws AirportServiceException {
		try {
			departureService.addDeparture(flightId, gate, destination, flightTime);
		} catch (DepartureServiceException e) {
			throw new AirportServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void addBaggage(String baggageId, String entryPoint, String flightId) throws AirportServiceException {
		try {
			baggageService.addBaggage(baggageId, entryPoint, flightId);
		} catch (BaggageServiceException e) {
			throw new AirportServiceException(e.getMessage(), e);
		}
	}

	@Override
	public List<String> getAllBaggageShortestPath() throws AirportServiceException {
		List<String> baggageShortPaths = null;
		try {
			nodeService.calculateShortPathForNodes();
			baggageService.assignShortPathToBaggage(nodeService, departureService);
			baggageShortPaths = baggageService.getAllBaggageShortestPath();
		} catch (BaggageServiceException e) {
			throw new AirportServiceException(e.getMessage(), e);
		}
		return baggageShortPaths;
	}

	@Override
	public boolean hasValidData() {
		if (nodeService.hasValidData() && departureService.hasValidData() && baggageService.hasValidData())
			return true;
		return false;
	}

}
