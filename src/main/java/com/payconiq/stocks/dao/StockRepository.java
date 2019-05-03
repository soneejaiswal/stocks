package com.payconiq.stocks.dao;

import java.util.Collection;

import com.payconiq.stocks.model.Stock;

/**
 * Repository to manage all the CRUD operations done on Stocks
 * 
 * @author Sonee
 *
 */
public interface StockRepository {
	/**
	 * creates a stock
	 * 
	 * @param stock
	 */
	void save(Stock stock);

	/**
	 * reads a stock based on id
	 * 
	 * @param id
	 * @return
	 */
	Stock get(int id);

	/**
	 * reads all the stocks
	 * 
	 * @return
	 */
	Collection<Stock> getAll();

	/**
	 * updates a stock based on id
	 * 
	 * @param stock
	 */

	void update(Stock stock);

	/**
	 * deletes a stock based on id
	 * 
	 * @param id
	 */
	void delete(int id);

}
