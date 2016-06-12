package com.models;

import java.util.ArrayList;

public class Menu {
	int MenuID;
	ArrayList<Category> Categories = new ArrayList<Category>();

	// Constructor
	public Menu(int MenuID) {
		this.MenuID = MenuID;

	}

	public void setMenuID(int MenuID) {
		this.MenuID = MenuID;
	}

	public int getMenuId() {
		return MenuID;
	}

	public void setCategories(ArrayList<Category> Categories) {
		this.Categories = Categories;
	}

	public ArrayList<Category> getCategories() {
		return Categories;
	}

	public void addCategory(Category category) {

		Categories.add(category);

	}

}