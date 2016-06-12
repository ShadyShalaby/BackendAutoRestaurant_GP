package com.models;

import java.sql.Timestamp;

public class Reciept {
	int ReciptID;
	double SubTotal;
	double Tax;
	double Services;
	double Total;
	Timestamp OrderTime; // date

	Reciept(int ReciptID, double SubTotal, double Tax, double Services,
			double Total, Timestamp OrderTime) {
		this.ReciptID = ReciptID;
		this.SubTotal = SubTotal;
		this.Tax = Tax;
		this.Services = Services;
		this.Total = Total;
		this.OrderTime = OrderTime;
	}

	// setter & getters

	public void setReciptID(int ReciptID) {
		this.ReciptID = ReciptID;
	}

	public int getReciptID() {
		return ReciptID;
	}

	public void setSubTotal(double SubTotal) {
		this.SubTotal = SubTotal;
	}

	public double getSubTotal() {
		return SubTotal;
	}

	public void setTax(double Tax) {
		this.Tax = Tax;
	}

	public double getTax() {
		return Tax;
	}

	public void setServices(double Services) {
		this.Services = Services;
	}

	public double getServices() {
		return Services;
	}

	public void setTotal(double Total) {
		this.Total = Total;
	}

	public double getTotal() {
		return Total;
	}

	public void setOrderTime(Timestamp OrderTime) {
		this.OrderTime = OrderTime;
	}

	public Timestamp getOrderTime() {
		return OrderTime;
	}
}
