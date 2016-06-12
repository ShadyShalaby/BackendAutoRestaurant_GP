package com.models;

import java.util.ArrayList;

public class Branch {
	int branchId;
	String location;
	String address;
	ArrayList<Corner> corners = new ArrayList<Corner>();

	public Branch(int branchId, String location, String address) {
		this.branchId = branchId;
		this.location = location;
		this.address = address;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Corner> getCorners() {
		return corners;
	}

	public void setCorners(ArrayList<Corner> corners) {
		this.corners = corners;
	}

}
