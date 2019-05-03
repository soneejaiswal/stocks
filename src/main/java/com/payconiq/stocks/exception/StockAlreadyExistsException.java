package com.payconiq.stocks.exception;

/**
 * 
 * @author Sonee
 *
 */
public class StockAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4316169168302686552L;

	public StockAlreadyExistsException(String message) {

		super(message);
	}

	public StockAlreadyExistsException(String message, Throwable throwable) {

		super(message, throwable);
	}

}
