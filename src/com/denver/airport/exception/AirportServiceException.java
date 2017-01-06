package com.denver.airport.exception;

public class AirportServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AirportServiceException() {
		super();
	}

	public AirportServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AirportServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AirportServiceException(String message) {
		super(message);
	}

	public AirportServiceException(Throwable cause) {
		super(cause);
	}

}
