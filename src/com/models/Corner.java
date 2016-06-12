package com.models;

public class Corner {

	private int cornerID;
	private int tableNumber;

	public Corner(int cornerID, int tableNumber) {
		super();
		this.cornerID = cornerID;
		this.tableNumber = tableNumber;
	}

	public int getCornerID() {
		return cornerID;
	}

	public void setCornerID(int cornerID) {
		this.cornerID = cornerID;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

}
