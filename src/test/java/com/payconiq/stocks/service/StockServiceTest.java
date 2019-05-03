package com.payconiq.stocks.service;

import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.payconiq.stocks.dao.StocksFactory;
import com.payconiq.stocks.exception.InvalidStockException;
import com.payconiq.stocks.exception.StockAlreadyExistsException;
import com.payconiq.stocks.exception.StockCreationException;
import com.payconiq.stocks.exception.StockNotFoundException;
import com.payconiq.stocks.model.Stock;

public class StockServiceTest {

	private static StockService stockService;
	private static Map<Integer, Stock> stocks;

	@BeforeClass
	public static void setUp() {
		stockService = new StockServiceImpl();
		stocks = StocksFactory.getStocks();
//		stocks.put(1, new Stock("setup stock 1", 100));

		stocks = StocksFactory.getStocks();
		Stock stock1 = new Stock("setup stock 1", 100);
		stock1.setId(10);

		stocks.put(stock1.getId(), stock1);
	}

	@Test(expected = InvalidStockException.class)
	public void testCreateStockWithInvalidName()
			throws StockAlreadyExistsException, StockCreationException, InvalidStockException {
		stockService.createStock(new Stock(null, 1000)); // here stock name can not be null
	}

	@Test(expected = InvalidStockException.class)
	public void testCreateStockWithEmptyName()
			throws StockAlreadyExistsException, StockCreationException, InvalidStockException {
		stockService.createStock(new Stock("", 1000)); // here stock name can not be empty
	}

	@Test(expected = InvalidStockException.class)
	public void testCreateStockWithInvalidPrice()
			throws StockAlreadyExistsException, StockCreationException, InvalidStockException {
		stockService.createStock(new Stock("stock-name-2", 0)); // here stock price can not be 0
	}

	@Test(expected = StockAlreadyExistsException.class)
	public void testCreateAlreadyExistingStock()
			throws StockAlreadyExistsException, StockCreationException, InvalidStockException {
		int stockId = 10;
		assertNotNull(stocks.get(stockId));

		Stock stock = new Stock("stock-name-2", 1000);
		stock.setId(stockId);
		stockService.createStock(stock);
	}

	@Test(expected = StockNotFoundException.class)
	public void testReadThrowsException() throws StockNotFoundException {
		stockService.getSingleStock(100);
	}

	@Test(expected = StockNotFoundException.class)
	public void testUpdateThrowsException() throws StockNotFoundException {
		stockService.updateSingleStockPrice(100, 1111);
	}

	@Test(expected = StockNotFoundException.class)
	public void testDeleteThrowsException() throws StockNotFoundException {
		stockService.deleteStock(100);
	}

	@AfterClass
	public static void tearDown() {
		stocks = null;
		stockService = null;
	}
}
