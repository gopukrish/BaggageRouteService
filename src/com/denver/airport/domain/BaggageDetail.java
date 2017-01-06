package com.denver.airport.domain;

public class BaggageDetail {
	private String baggageId;
	private String entryPoint;
	private String flightId;
	private ShortPath shortPath;
	
	public BaggageDetail(){
	}
	
	public BaggageDetail(String baggageId, String entryPoint, String flightId, ShortPath shortPath) {
		setBaggageId(baggageId);
		setEntryPoint(entryPoint);
		setFlightId(flightId);
		setShortPath(shortPath);
	}

	public String getBaggageId() {
		return baggageId;
	}

	public void setBaggageId(String baggageId) {
		this.baggageId = baggageId;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(String entryPoint) {
		this.entryPoint = entryPoint;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public ShortPath getShortPath() {
		return shortPath;
	}

	public void setShortPath(ShortPath shortPath) {
		this.shortPath = shortPath;
	}

	@Override
	public String toString() {
		return "BaggageDetail [baggageId=" + baggageId + ", entryPoint=" + entryPoint + ", flightId=" + flightId
				+ ", shortPath=" + shortPath + "]";
	}
}
