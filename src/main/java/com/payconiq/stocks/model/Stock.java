package com.payconiq.stocks.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * model class representing the stocks entity & its attributes
 * 
 * @author Sonee
 *
 */
public class Stock {

	@NotNull
	private int id;

	@NotNull
	private String name;

	@NotNull
	private double currentPrice;

	private String lastUpdate;

	public Stock() {
		this.lastUpdate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}

	public Stock(String name, double currentPrice) {
		super();
		this.name = name;
		this.currentPrice = currentPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
