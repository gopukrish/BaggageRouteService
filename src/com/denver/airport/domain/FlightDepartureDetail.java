package com.denver.airport.domain;

public class FlightDepartureDetail {
	private String flightId;
	private String gate;
	private String destination;
	private String travelTime;
	
	public FlightDepartureDetail(){
	}

	public FlightDepartureDetail(String flightId, String gate, String destination, String travelTime) {
		setFlightId(flightId);
		setGate(gate);
		setDestination(destination);
		setTravelTime(travelTime);
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}

	@Override
	public String toString() {
		return "FlightDetail [flightId=" + flightId + ", gate=" + gate + ", destination=" + destination
				+ ", travelTime=" + travelTime + "]";
	}
}
