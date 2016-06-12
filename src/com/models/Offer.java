package com.models;

import java.security.Timestamp;
import java.util.ArrayList;

public class Offer {
	private int offerID;
	private String description;
	private ArrayList<Item> offerItems;
	private double price;
	private Timestamp startDate;
	private Timestamp endDate;

	/************************ Constructor ***************************/

	public Offer() {
		offerID = 0;
		description = "";
		offerItems = new ArrayList<Item>();
		price = 0.0;
		startDate = null;
		endDate = null;
	}

	/************************ Getters ***************************/
	public int getOfferID() {
		return offerID;
	}

	public double getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public ArrayList<Item> getOfferItems() {
		return offerItems;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	/************************ Setters ***************************/
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setOfferItems(ArrayList<Item> offerItems) {
		this.offerItems = offerItems;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

}