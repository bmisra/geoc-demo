package com.shopmanager.dao;

import java.io.Serializable;

public class Shop implements Serializable {

	private static final long serialVersionUID = -4806191908939935264L;

	private long shopId;
	
	private String shopName;
	
	private Address shopAddress;
	
	private double shopLatitude;
	
	private double shopLongitude;

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Address getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(Address shopAddress) {
		this.shopAddress = shopAddress;
	}

	public double getShopLatitude() {
		return shopLatitude;
	}

	public void setShopLatitude(double shopLatitude) {
		this.shopLatitude = shopLatitude;
	}

	public double getShopLongitude() {
		return shopLongitude;
	}

	public void setShopLongitude(double shopLongitude) {
		this.shopLongitude = shopLongitude;
	}
	
	
	
	
}
