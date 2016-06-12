package com.models;

import java.util.ArrayList;

public class Category {
	private int categoryID;
	private String categoryName;
	private ArrayList<Item> items;

	/************************ Constructor ***************************/
	public Category() {
		categoryID = 0;
		categoryName = "";
		items = new ArrayList<Item>();
	}

	/********************** Constructor *****************************/
	public Category(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		items = new ArrayList<Item>();
	}

	/************************ Getters ***************************/
	public int getCategoryID() {
		return categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	/************************ Setters ***************************/
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
