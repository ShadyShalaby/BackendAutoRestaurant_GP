package com.models;

import java.sql.Timestamp;

public class Reciept {
	int reciptID;
	double subTotal;
	double tax;
	double services;
	double total;
	Timestamp orderTime; // date

	public Reciept() {
		reciptID = 0;
		subTotal = 0.0;
		tax = 0.0;
		services = 0.0;
		total = 0.0;
		orderTime = new Timestamp(System.currentTimeMillis());
	}

	public Reciept(int ReciptID, double SubTotal, double Tax, double Services,
			double Total, Timestamp OrderTime) {
		this.reciptID = ReciptID;
		this.subTotal = SubTotal;
		this.tax = Tax;
		this.services = Services;
		this.total = Total;
		this.orderTime = OrderTime;
	}

	// setter & getters

	public void setReciptID(int ReciptID) {
		this.reciptID = ReciptID;
	}

	public int getReciptID() {
		return reciptID;
	}

	public void setSubTotal(double SubTotal) {
		this.subTotal = SubTotal;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setTax(double Tax) {
		this.tax = Tax;
	}

	public double getTax() {
		return tax;
	}

	public void setServices(double Services) {
		this.services = Services;
	}

	public double getServices() {
		return services;
	}

	public void setTotal(double Total) {
		this.total = Total;
	}

	public double getTotal() {
		return total;
	}

	public void setOrderTime(Timestamp OrderTime) {
		this.orderTime = OrderTime;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}
}
