package com.beans;

public class Cart {
	private int id;
	private String name;
	private String price;
	
	
	
	public Cart(int id, String name, String price) {
		
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public String getPrice() {
		return price;
	}
	public int getId() {
		return id;
	}
}
