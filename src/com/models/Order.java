package com.models;

public class Order {
	int OrderID;
	boolean isFavOrder;
	Reciept Reciept;
	Corner Corner;
	Cashier Cashier;

	public Order(int OrderID, boolean isFavOrder, Reciept reciept, Corner corner,
			Cashier cashier) {
		this.OrderID = OrderID;
		this.isFavOrder = isFavOrder;

		this.Reciept = reciept;
		this.Corner = corner;
		this.Cashier = cashier;
	}

	// Setters & Getters
	public void setOrderID(int OrderID) {
		this.OrderID = OrderID;
	}

	public int getOrderId() {
		return OrderID;
	}

	public void setIsFav(boolean isFavOrder) {
		this.isFavOrder = isFavOrder;
	}

	public boolean getIsFav() {
		return isFavOrder;
	}

	public void setRecipt(Reciept Recipt) {
		this.Reciept = Recipt;
	}

	public Reciept getRecipt() {
		return Reciept;
	}

	public void setcorner(Corner Corner) {
		this.Corner = Corner;
	}

	public Corner getcorner() {
		return Corner;
	}

	public void setCashier(Cashier Cashier) {
		this.Cashier = Cashier;
	}

	public Cashier getCashier() {
		return Cashier;
	}
}
