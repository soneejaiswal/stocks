package com.payconiq.stocks.service;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payconiq.stocks.dao.StockRepository;
import com.payconiq.stocks.dao.StocksFactory;
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
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stocksRepository;

	private Map<Integer, Stock> stocks = StocksFactory.getStocks();

	@Override
	public Collection<Stock> getStocksList() {
		if (stocks.size() != 0)
			return stocksRepository.getAll();

		return null;
	}

	@Override
	public Stock createStock(Stock stock)
			throws StockCreationException, StockAlreadyExistsException, InvalidStockException {

		if (stocks.containsKey(stock.getId()))
			throw new StockAlreadyExistsException("Stock already exists!");
		if (stock.getName() == null || stock.getName().isEmpty() || stock.getCurrentPrice() == 0)
			throw new InvalidStockException("Stock does not have a valid name or price!");

		stocksRepository.save(stock);

		if (stocks.containsKey(stock.getId()))
			return stocksRepository.get(stock.getId());

		throw new StockCreationException("Stock Could not be created!");
	}

	@Override
	public Stock getSingleStock(int id) throws StockNotFoundException {

		if (stocks.containsKey(id))
			return stocksRepository.get(id);

		throw new StockNotFoundException("Could not found stock, stock id is : " + id);
	}

	@Override
	public Stock updateSingleStockPrice(int id, double currentPrice) throws StockNotFoundException {

		if (stocks.containsKey(id)) {
			Stock currStock = stocksRepository.get(id);
			currStock.setCurrentPrice(currentPrice);
			currStock.setLastUpdate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
			stocksRepository.update(currStock);
			return stocksRepository.get(id);
		}

		throw new StockNotFoundException("Could not found stock to update, stock id is : " + id);

	}

	@Override
	public boolean deleteStock(int id) throws StockNotFoundException {
		if (stocks.containsKey(id)) {
			stocksRepository.delete(id);
			return true;
		}
		throw new StockNotFoundException("Could not found stock to delete, stock id is : " + id);
	}
}
