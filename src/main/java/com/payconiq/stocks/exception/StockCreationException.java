package com.payconiq.stocks.exception;

/**
 * 
 * @author Sonee
 *
 */
public class StockCreationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8721355538269462404L;

	public StockCreationException(String exceptionMessage, Throwable throwable) {
		super(exceptionMessage, throwable);
	}
	
	public StockCreationException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
