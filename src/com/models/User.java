package com.models;

import java.util.ArrayList;

public class User {

	private String userName;
	private String email;
	private int userID;
	private String phone;
	private String password;
	private String pic;
	private ArrayList<Integer> favRestuarants = new ArrayList<Integer>();
	private ArrayList<Order> orders = new ArrayList<Order>();
	private ArrayList<Order> favOrders = new ArrayList<Order>();

	public String getUserName() {
		return userName;
	}

	public User() {
		this.userName = "";
		this.email = "";
		this.userID = 0;
		this.phone = "";
		this.password = "";
		this.pic = "";
		this.favRestuarants = null;
		this.orders = null;
		this.favOrders = null;

	}

	public User(String userName, String email, int userID, String phone,
			String password, String pic, ArrayList<Integer> favRestuarants,
			ArrayList<Order> orders, ArrayList<Order> favOrders) {
		super();
		this.userName = userName;
		this.email = email;
		this.userID = userID;
		this.phone = phone;
		this.password = password;
		this.pic = pic;
		this.favRestuarants = favRestuarants;
		this.orders = orders;
		this.favOrders = favOrders;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public ArrayList<Integer> getFavRestuarants() {
		return favRestuarants;
	}

	public void setFavRestuarants(ArrayList<Integer> favRestuarants) {
		this.favRestuarants = favRestuarants;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Order> getFavOrders() {
		return favOrders;
	}

	public void setFavOrders(ArrayList<Order> favOrders) {
		this.favOrders = favOrders;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
