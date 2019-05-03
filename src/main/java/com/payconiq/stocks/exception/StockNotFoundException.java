package com.payconiq.stocks.exception;

/**
 * 
 * @author Sonee
 *
 */
public class StockNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3018229096951959881L;

	public StockNotFoundException(String exceptionMessage, Throwable throwable) {
		super(exceptionMessage, throwable);
	}
	
	public StockNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
