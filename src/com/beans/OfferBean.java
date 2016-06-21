package com.beans;

import java.sql.Connection;

public class OfferBean {

	private Connection conn;

	public OfferBean() {
		conn = DBConnection.getActiveConnection();
	}
}