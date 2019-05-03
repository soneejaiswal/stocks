package com.payconiq.stocks.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payconiq.stocks.exception.InvalidStockException;
import com.payconiq.stocks.exception.StockAlreadyExistsException;
import com.payconiq.stocks.exception.StockCreationException;
import com.payconiq.stocks.exception.StockNotFoundException;
import com.payconiq.stocks.model.Stock;
import com.payconiq.stocks.service.StockService;

/**
 * Entry point for request-mappings of stock application's CRUD APIs
 * 
 * @author Sonee
 *
 */
@RestController
@RequestMapping("/api/stocks")
public class StocksController {

	@Autowired
	private StockService stockService;

	/**
	 * create stock
	 * 
	 * @param stock
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Stock> create(@RequestBody Stock stock) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(stockService.createStock(stock));

		} catch (StockAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (StockCreationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidStockException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/**
	 * gets the list of stocks
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Collection<Stock>> getStocksList() {
		if (stockService.getStocksList() == null)
			return ResponseEntity.noContent().build();
		return ResponseEntity.accepted().body(stockService.getStocksList());
	}

	/**
	 * get single stock using stock id parameter
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Stock> getStock(@PathVariable int id) {

		try {
			return ResponseEntity.accepted().body(stockService.getSingleStock(id));
		} catch (StockNotFoundException e) {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * update single stock price base on stock id
	 * 
	 * @param id
	 * @param newStock
	 * @return
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Stock> updateStock(@PathVariable int id, @RequestBody Stock newStock) {

		try {
			return ResponseEntity.accepted().body(stockService.updateSingleStockPrice(id, newStock.getCurrentPrice()));
		} catch (StockNotFoundException e) {
			return ResponseEntity.noContent().build();
		}
	}

	/**
	 * deletes a single stock based on stock id
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> deleteStock(@PathVariable int id) {

		try {
			return ResponseEntity.accepted().body(stockService.deleteStock(id));
		} catch (StockNotFoundException e) {
			return ResponseEntity.noContent().build();
		}
	}
}
