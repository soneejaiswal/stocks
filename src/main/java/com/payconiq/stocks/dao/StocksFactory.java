package com.payconiq.stocks.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.payconiq.stocks.model.Stock;

/**
 * 
 * @author Sonee
 *
 */
public final class StocksFactory {

	private StocksFactory() {
	}

	volatile private static Map<Integer, Stock> stocks = null;

	/**
	 * in memory single instance for storing stocks based on stock id.
	 * 
	 * @return
	 */
	public static Map<Integer, Stock> getStocks() {

		if (stocks == null) {
			synchronized (StocksFactory.class) {
				if (stocks == null)
					stocks = new ConcurrentHashMap<Integer, Stock>();
			}
		}
		return stocks;
	}
}
