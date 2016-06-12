package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.User;

public class ReviewBean {

	private Connection conn;

	public ReviewBean() {
		conn = DBConnection.getActiveConnection();
	}
}