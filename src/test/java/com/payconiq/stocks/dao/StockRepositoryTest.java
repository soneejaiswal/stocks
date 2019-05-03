package com.payconiq.stocks.dao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.payconiq.stocks.dao.StockRepository;
import com.payconiq.stocks.dao.StockRepositoryImpl;
import com.payconiq.stocks.dao.StocksFactory;
import com.payconiq.stocks.exception.InvalidStockException;
import com.payconiq.stocks.exception.StockAlreadyExistsException;
import com.payconiq.stocks.exception.StockCreationException;
import com.payconiq.stocks.exception.StockNotFoundException;
import com.payconiq.stocks.model.Stock;

public class StockRepositoryTest {

	private static StockRepository stockRepository;
	private static Map<Integer, Stock> stocks;

	@BeforeClass
	public static void setUp() {
		stockRepository = new StockRepositoryImpl();
		stocks = StocksFactory.getStocks();
		Stock stock1 = new Stock("setup stock 1", 1000);
		stock1.setId(10);
		Stock stock2 = new Stock("setup stock 2", 2000);
		stock2.setId(20);

		stocks.put(stock1.getId(), stock1);
		stocks.put(stock2.getId(), stock2);
	}

	@Test
	public void testCreate() throws StockAlreadyExistsException, StockCreationException, InvalidStockException {
		assertTrue(stocks.size() == 2);

		stockRepository.save(new Stock("stock-name-1", 1000));
		assertTrue(stocks.size() == 3);
	}

	@Test
	public void testRead() throws StockNotFoundException {
		Stock stock = stockRepository.get(10);
		assertNotNull(stock);
		assertArrayEquals(new Object[] { 10, "setup stock 1", 1000.0 },
				new Object[] { stock.getId(), stock.getName(), stock.getCurrentPrice() });
	}

	@Test
	public void testReadAll() {
		assertTrue(stockRepository.getAll().size() > 1);
	}

	@Test
	public void testUpdate() throws StockNotFoundException {
		int stockId = 10;
		Stock stock = stockRepository.get(stockId);
		assertTrue(stock.getCurrentPrice() == 1000);

		stock.setCurrentPrice(2000);
		stockRepository.update(stock);
		stock = stockRepository.get(stockId);
		assertTrue(stock.getCurrentPrice() == 2000);
	}

	@Test
	public void testDelete() throws StockNotFoundException {
		int stockId = 1;
		Stock stock = stockRepository.get(stockId);
		assertNotNull(stock);

		stockRepository.delete(1);
		stock = stockRepository.get(stockId);
		assertNull(stock);
	}

	@AfterClass
	public static void tearDown() {
		stocks = null;
		stockRepository = null;
	}

}
