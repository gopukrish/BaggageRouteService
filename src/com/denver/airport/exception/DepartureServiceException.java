package com.denver.airport.exception;

public class DepartureServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepartureServiceException() {
		super();
	}

	public DepartureServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DepartureServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DepartureServiceException(String message) {
		super(message);
	}

	public DepartureServiceException(Throwable cause) {
		super(cause);
	}

}
