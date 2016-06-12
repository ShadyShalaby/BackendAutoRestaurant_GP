package com.models;

public class Admin {
	private int adminID;
	private String name;
	private String password;

	/************************ Constructor ***************************/
	public Admin() {
		adminID = 0;
		name = "";
		password = "";
	}

	/************************ Getters ***************************/
	public int getAdminID() {
		return adminID;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	/************************ Setters ***************************/
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
