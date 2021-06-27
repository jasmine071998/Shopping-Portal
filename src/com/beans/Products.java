package com.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Products implements Comparable<Products>{
	private int id;
	private String name;
	private String price;
	private String category;
	private String rating;
	private String featured;
	
	public String getFeatured() {
		return featured;
	}

	public void setFeatured(String featured) {
		this.featured = featured;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getCategory() {
		return category;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return name + "  Price: Rs." + price;
	}
	public void setCategory(String category) {
		this.category = category;
		
	}
	@Override
	public int compareTo(Products p) {
		// TODO Auto-generated method stub
		return Integer.parseInt(this.price) - Integer.parseInt(p.price);
	}
	
	public ArrayList<Products> sortLowHigh(ArrayList<Products> list) {
		
		Collections.sort(list);
		return list;
	}
	
	public ArrayList<Products> sortHighLow(ArrayList<Products> list) {
		
		Collections.sort(list, new Temp());
		return list;
	}

	
	
}

class Temp implements Comparator<Products>{

	@Override
	public int compare(Products p1, Products p2) {
		// TODO Auto-generated method stub
		return Integer.parseInt(p2.getPrice()) - Integer.parseInt(p1.getPrice());
	}

}
