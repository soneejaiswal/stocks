package com.payconiq.stocks.service;

import java.util.Collection;

import com.payconiq.stocks.exception.InvalidStockException;
import com.payconiq.stocks.exception.StockAlreadyExistsException;
import com.payconiq.stocks.exception.StockCreationException;
import com.payconiq.stocks.exception.StockNotFoundException;
import com.payconiq.stocks.model.Stock;

/**
 * 
 * @author Sonee
 *
 */
public interface StockService {

	/**
	 * service to get all the stocks
	 * 
	 * @return
	 */
	Collection<Stock> getStocksList();

	/**
	 * validates correct stock attributes and creates a stock
	 * 
	 * @param stock
	 * @return
	 * @throws StockAlreadyExistsException
	 * @throws StockCreationException
	 * @throws InvalidStockException
	 */
	Stock createStock(Stock stock) throws StockAlreadyExistsException, StockCreationException, InvalidStockException;

	/**
	 * reads a single stock, if stock id exists in the Data set
	 * 
	 * @param id - stock id
	 * @return
	 * @throws StockNotFoundException
	 */
	Stock getSingleStock(int id) throws StockNotFoundException;

	/**
	 * if stock exists, its price is updated
	 * 
	 * @param id           - stock id
	 * @param currentPrice - stock price
	 * @return
	 * @throws StockNotFoundException
	 */
	Stock updateSingleStockPrice(int id, double currentPrice) throws StockNotFoundException;

	/**
	 * deletes the existing stock using stock id
	 * 
	 * @param id - stock id
	 * @return
	 * @throws StockNotFoundException
	 */
	boolean deleteStock(int id) throws StockNotFoundException;

}
