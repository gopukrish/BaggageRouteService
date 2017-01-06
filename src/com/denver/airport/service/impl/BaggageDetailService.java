package com.denver.airport.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.denver.airport.domain.BaggageDetail;
import com.denver.airport.domain.FlightDepartureDetail;
import com.denver.airport.domain.ShortPath;
import com.denver.airport.exception.BaggageServiceException;
import com.denver.airport.service.BaggageService;
import com.denver.airport.service.DepartureService;
import com.denver.airport.service.NodeService;
import com.denver.airport.util.Section;
import com.denver.airport.util.StringUtils;

public class BaggageDetailService implements BaggageService {
	private Map<String, BaggageDetail> baggageDetails;

	public BaggageDetailService() {
		baggageDetails = new HashMap<>();
	}

	@Override
	public void addBaggage(String baggageId, String entryPoint, String flightId) throws BaggageServiceException {
		if (!StringUtils.isEmpty(baggageId) && !StringUtils.isEmpty(entryPoint) && !StringUtils.isEmpty(flightId)) {
			BaggageDetail baggageDetail = new BaggageDetail(baggageId, entryPoint, flightId, null);
			baggageDetails.put(baggageId, baggageDetail);
		} else {
			throw new BaggageServiceException(ADD_ERROR_MSG);
		}
	}
	
	@Override
	public ShortPath getBaggageShortPath(String baggageId){
		if(baggageDetails.containsKey(baggageId))
			return baggageDetails.get(baggageId).getShortPath();
		return null;
	}

	private String formatPath(BaggageDetail detail) {
		StringBuffer sb = new StringBuffer();
		sb.append(detail.getBaggageId()).append(Section.INPUT_SEPARATOR);
		if (detail.getShortPath() != null) {
			sb.append(detail.getShortPath().getShortPath()).append(":");
			sb.append(detail.getShortPath().getPathLength());
		} else {
			sb.append(NO_OPTIMAL_ROUTE_FOUND);
		}
		return sb.toString();
	}

	@Override
	public List<String> getAllBaggageShortestPath() throws BaggageServiceException {
		try {
			List<String> baggageShortPaths = new ArrayList<>();
			baggageDetails.forEach((id, value) -> {
				BaggageDetail baggageDetail = baggageDetails.get(id);
				baggageShortPaths.add((formatPath(baggageDetail)));
			});
			return baggageShortPaths;
		} catch (Exception e) {
			throw new BaggageServiceException(OPTIMAL_ROUTE_FETCH_ERROR, e);
		}
	}

	@Override
	public void assignShortPathToBaggage(NodeService nodeService, DepartureService departureService)
			throws BaggageServiceException {
		try {
			if (nodeService != null && departureService != null)
				baggageDetails.forEach((id, baggage) -> {
					String bggEntryNode = baggage.getEntryPoint();
					FlightDepartureDetail deptDetl = departureService.getFlightDepartureDetail(baggage.getFlightId());
					ShortPath path = null;
					if (deptDetl != null)
						path = nodeService.getShortPath(bggEntryNode, deptDetl.getGate());
					if (Section.ARRIVAL_NODE.equalsIgnoreCase(baggage.getFlightId()))
						path = nodeService.getShortPath(bggEntryNode, Section.BAGGAGE_CLAIM_NODE);
					baggage.setShortPath(path);
				});
		} catch (Exception e) {
			throw new BaggageServiceException(OPTIMAL_ROUTE_ASSIGN_ERROR, e);
		}
	}

	@Override
	public boolean hasValidData() {
		if (baggageDetails != null && baggageDetails.size() > 0)
			return true;
		return false;
	}
}
