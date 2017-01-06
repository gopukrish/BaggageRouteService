package com.denver.airport.exception;

public class BaggageServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaggageServiceException() {
		super();
	}

	public BaggageServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaggageServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaggageServiceException(String message) {
		super(message);
	}

	public BaggageServiceException(Throwable cause) {
		super(cause);
	}
}
