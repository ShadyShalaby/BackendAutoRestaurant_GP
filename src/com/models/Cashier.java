package com.models;

public class Cashier {
	int cashierId;
	String cashierName;
	String cashierPassword;

	public Cashier() {
		cashierId = 0;
		cashierName = "";
		cashierPassword = "";
	}

	public Cashier(int cashierId, String cashierName) {
		this.cashierId = cashierId;
		this.cashierName = cashierName;
	}

	public Cashier(int cashierId, String cashierName, String cashierPassword) {
		this(cashierId, cashierName);
		this.cashierPassword = cashierPassword;
	}

	public int getCashierId() {
		return cashierId;
	}

	public void setCashierId(int cashierId) {
		this.cashierId = cashierId;
	}

	public String getCashierName() {
		return cashierName;
	}

	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}

	public String getCashierPassword() {
		return cashierPassword;
	}

	public void setCashierPassword(String cashierPassword) {
		this.cashierPassword = cashierPassword;
	}

}
