package com.denver.airport.exception;

public class NodeServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NodeServiceException() {
		super();
	}

	public NodeServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NodeServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public NodeServiceException(String message) {
		super(message);
	}

	public NodeServiceException(Throwable cause) {
		super(cause);
	}

}
