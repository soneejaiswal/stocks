package com.payconiq.stocks.exception;

/**
 * 
 * @author Sonee
 *
 */
public class InvalidStockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1576315469245194942L;

	public InvalidStockException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public InvalidStockException(String message) {
		super(message);
	}

}
