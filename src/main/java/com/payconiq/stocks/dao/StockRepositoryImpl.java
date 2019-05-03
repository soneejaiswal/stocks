package com.payconiq.stocks.dao;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.payconiq.stocks.model.Stock;

/**
 * 
 * @author Sonee
 *
 */
@Repository
public class StockRepositoryImpl implements StockRepository {

	private Map<Integer, Stock> stocks;

	public StockRepositoryImpl() {
		this.stocks = StocksFactory.getStocks();
	}

	private static AtomicInteger count = new AtomicInteger(0);

	public void save(Stock stock) {
		stock.setId(count.incrementAndGet());
		stocks.put(stock.getId(), stock);
	}

	public Stock get(int id) {
		return stocks.get(id);
	}

	public Collection<Stock> getAll() {
		return stocks.values();
	}

	public void update(Stock stock) {
		stocks.put(stock.getId(), stock);
	}

	public void delete(int id) {
		stocks.remove(id);
	}
}
