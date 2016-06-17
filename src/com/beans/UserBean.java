package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.User;
import com.mysql.jdbc.Statement;

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
			user.setUserID(rs.getInt("customerId"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("customerPassword"));
			user.setPhone(rs.getString("phone"));
			/*
			 * String favRestaurants = rs.getString("favRestaurant");
			 */
		}

		return user;
	}

	public User addUser(String userName, String email, String phone, String pass) {

		String sql = "Insert into Customer"
				+ " (userName, email, phone, customerPassword)" + " VALUES ('"
				+ userName + "','" + email + "','" + phone + "','" + pass
				+ "')";

		User user = null;
		int id = 0;
		try {
			Statement stmt = (Statement) conn.createStatement();
			int nRows = stmt
					.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			if (nRows == 1) {
				user = new User();

				user.setUserName(userName);
				user.setUserID(id);
				user.setEmail(email);
				user.setPassword(pass);
				user.setPhone(phone);
			}
		} catch (SQLException e) {
		}

		return user;
	}

	/******************************************************************/

	public String updateUser(String userName, String email, String phone,
			String pass, int customerId) throws SQLException {
		String sql = "UPDATE Customer SET userName =?"
				+ ",email =?,phone =?,customerPassword=? WHERE customerId =? ";

		String state = "false";

		try {
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, email);
			stmt.setString(3, phone);
			stmt.setString(4, pass);
			stmt.setInt(5, customerId);

			int numberOfRows = stmt.executeUpdate();

			if (numberOfRows == 1) {
				state = "true";
			}
		} catch (SQLException e) {
		}

		return state;
	}

}
