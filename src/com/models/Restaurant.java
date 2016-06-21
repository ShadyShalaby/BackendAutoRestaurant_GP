package com.models;

import java.util.ArrayList;

public class Restaurant {

	private int restaurantID;
	private String restName;
	private String logo;
	private double rating;
	private int nUsers;
	private String[] type;
	private Menu menu;
	private int hotLine;
	private String deliverTo;
	private String workingHours;
	private String timeForDeliver;
	private double tax;
	private double services;
	private ArrayList<Offer> offers;
	private ArrayList<Branch> branches = new ArrayList<Branch>();
	private ArrayList<Review> reviews = new ArrayList<Review>();

	public Restaurant() {

		restaurantID = nUsers = hotLine = 0;
		restName = logo = deliverTo = workingHours = timeForDeliver = "";
		rating = tax = services = 0.0;
		type = null;
		menu = new Menu(0);
		branches = new ArrayList<Branch>();
		reviews = new ArrayList<Review>();
		offers = new ArrayList<Offer>();
	}

	public Restaurant(int restaurantID, String restName, String logo,
			double averageRating, int nUsers, String[] type, Menu menu,
			ArrayList<Branch> restaurantBranches, int hotLine,
			String deliverTo, String workingHours, String timeForDeliver,
			ArrayList<Review> reviews) {
		this.restaurantID = restaurantID;
		this.restName = restName;
		this.logo = logo;
		this.rating = averageRating;
		this.nUsers = nUsers;
		this.type = type;
		this.menu = menu;
		this.branches = restaurantBranches;
		this.hotLine = hotLine;
		this.deliverTo = deliverTo;
		this.workingHours = workingHours;
		this.timeForDeliver = timeForDeliver;
		this.reviews = reviews;
		offers = new ArrayList<Offer>();
		tax = services = 0.0;
	}

	public Restaurant(int restaurantID, String restName, String logo,
			double rating, int nUsers, String[] type, Menu menu, int hotLine,
			String deliverTo, String workingHours, String timeForDeliver,
			double tax, double services, ArrayList<Offer> offers,
			ArrayList<Branch> restaurantBranches, ArrayList<Review> reviews) {
		super();
		this.restaurantID = restaurantID;
		this.restName = restName;
		this.logo = logo;
		this.rating = rating;
		this.nUsers = nUsers;
		this.type = type;
		this.menu = menu;
		this.hotLine = hotLine;
		this.deliverTo = deliverTo;
		this.workingHours = workingHours;
		this.timeForDeliver = timeForDeliver;
		this.tax = tax;
		this.services = services;
		this.offers = offers;
		this.branches = restaurantBranches;
		this.reviews = reviews;
	}

	public int getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(int restaurantID) {
		this.restaurantID = restaurantID;
	}

	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getnUsers() {
		return nUsers;
	}

	public void setnUsers(int nUsers) {
		this.nUsers = nUsers;
	}

	public String[] getType() {
		return type;
	}

	public void setType(String[] type) {
		this.type = type;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public ArrayList<Branch> getBranches() {
		return branches;
	}

	public void setBranches(ArrayList<Branch> branches) {
		this.branches = branches;
	}

	public int getHotLine() {
		return hotLine;
	}

	public void setHotLine(int hotLine) {
		this.hotLine = hotLine;
	}

	public String getDeliverTo() {
		return deliverTo;
	}

	public void setDeliverTo(String deliverTo) {
		this.deliverTo = deliverTo;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getTimeForDeliver() {
		return timeForDeliver;
	}

	public void setTimeForDeliver(String timeForDeliver) {
		this.timeForDeliver = timeForDeliver;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public double getServices() {
		return services;
	}

	public double getTax() {
		return tax;
	}

	public void setServices(double services) {
		this.services = services;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public ArrayList<Offer> getOffers() {
		return offers;
	}

	public void setOffers(ArrayList<Offer> offers) {
		this.offers = offers;
	}
}
