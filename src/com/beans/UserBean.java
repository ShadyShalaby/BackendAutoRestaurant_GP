package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.User;

public class UserBean {

	private Connection conn;

	public UserBean() {
		conn = DBConnection.getActiveConnection();
	}

	public User getUser(String email, String pass) throws SQLException {
		String sql = "Select * from Customer where `email` = ? and `customerPassword` = ?";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.setString(2, pass);
		ResultSet rs = stmt.executeQuery();

		User user = null;

		if (rs.next()) {
			user = new User();

			user.setUserName(rs.getString("userName"));
			user.setUserName(rs.getString("customerId"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("customerPassword"));
			user.setPhone(rs.getString("phone"));
			/*
			 * String favRestaurants = rs.getString("favRestaurant");
			 */
		}

		return user;
	}

	public int addUser(String userName, String email, String phone, String pass)
			throws SQLException {
		String sql = "Insert into Customer"
				+ " (userName,email,phone ,customerPassword)"
				+ " VALUES  (?,?,?,?)";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, userName);
		stmt.setString(2, email);
		stmt.setString(3, phone);
		stmt.setString(4, pass);

		int nRows = stmt.executeUpdate();
		
		if(nRows == 1){
			
		} else {
			
		}
			
		
		return nRows;
	}

}
