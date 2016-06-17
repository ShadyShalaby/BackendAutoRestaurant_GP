package com.models;

import java.util.ArrayList;

public class Order {
	int orderID;
	boolean isFavOrder;
	Reciept reciept;
	Corner corner;
	Cashier cashier;
	ArrayList<Item> items;

	public Order() {
		orderID = 0;
		isFavOrder = false;
		reciept = new Reciept();
		corner = new Corner();
		cashier = new Cashier();
		items = new ArrayList<Item>();
	}

	public Order(int OrderID, boolean isFavOrder, Reciept reciept,
			Corner corner, Cashier cashier) {

		this.orderID = OrderID;
		this.isFavOrder = isFavOrder;
		this.reciept = reciept;
		this.corner = corner;
		this.cashier = cashier;
		items = new ArrayList<Item>();
	}

	public Order(int orderID, boolean isFavOrder, Reciept reciept,
			Corner corner, Cashier cashier, ArrayList<Item> items) {
		super();
		this.orderID = orderID;
		this.isFavOrder = isFavOrder;
		this.reciept = reciept;
		this.corner = corner;
		this.cashier = cashier;
		this.items = items;
	}

	// Setters & Getters
	public void setOrderID(int OrderID) {
		this.orderID = OrderID;
	}

	public int getOrderId() {
		return orderID;
	}

	public void setIsFav(boolean isFavOrder) {
		this.isFavOrder = isFavOrder;
	}

	public boolean getIsFav() {
		return isFavOrder;
	}

	public void setRecipt(Reciept Recipt) {
		this.reciept = Recipt;
	}

	public Reciept getRecipt() {
		return reciept;
	}

	public void setcorner(Corner Corner) {
		this.corner = Corner;
	}

	public Corner getcorner() {
		return corner;
	}

	public void setCashier(Cashier Cashier) {
		this.cashier = Cashier;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public void setItems(ArrayList<Item> Items) {
		this.items = Items;
	}

	public ArrayList<Item> getItems() {
		return items;
	}
}
