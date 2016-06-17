package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Restaurant;
import com.mysql.jdbc.Statement;

public class RestaurantBean {

	private Connection conn;

	public RestaurantBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<Integer> getMenuIds() throws SQLException {
		String sql = "Select restaurantId from Restaurant";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		ArrayList<Integer> menuIds = new ArrayList<Integer>();
		while (rs.next()) {
			menuIds.add(Integer.parseInt(rs.getString("restaurantId")));
		}

		return menuIds;
	}

	public Restaurant getRestaurantInfo(int restId) throws SQLException {

		Restaurant restaurant;

		String sql = "Select * from Restaurant where restaurantId = ?";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, restId);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			restaurant = new Restaurant();
			int restaurantID = rs.getInt("restaurantId");
			String restName = rs.getString("restaurantName");
			double rating = rs.getDouble("rating");
			int nUsers = rs.getInt("nUsers");
			int hotLine = rs.getInt("hotLine");
			String deliverTo = rs.getString("deliverTo");
			String workingHours = rs.getString("workingHours");
			String timeForDeliver = rs.getString("timeForDeliver");
			String[] type = rs.getString("restaurantType").split(",");

			restaurant.setRestaurantID(restaurantID);
			restaurant.setRestName(restName);
			restaurant.setRating(rating);
			restaurant.setnUsers(nUsers);
			restaurant.setHotLine(hotLine);
			restaurant.setDeliverTo(deliverTo);
			restaurant.setWorkingHours(workingHours);
			restaurant.setTimeForDeliver(timeForDeliver);
			restaurant.setType(type);
		} else
			restaurant = null;

		/*
		 * ArrayList<Review> reviews = new ArrayList<Review>();
		 * ArrayList<Branch> restaurantBranches = new ArrayList<Branch>();
		 * String logo;
		 */

		return restaurant;
	}

	/******************************************************************/

	public String getFavouriteRestaurants(int customerId) throws SQLException {
		String sql = "Select favRestaurant from Customer where customerId =?";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customerId);

		String rest = "";
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			rest = rs.getString("favRestaurant");
		}

		return rest;
	}

	public String AddFavRestaurant(int customerId, int restID)
			throws SQLException {

		String value = getFavouriteRestaurants(customerId);

		if (value.length() == 0 || value.equals(0))
			value = String.valueOf(restID);

		else if (value.length() != 0)
			value += "," + String.valueOf(restID);

		int nRows;
		try {
			String sql = "UPDATE Customer " + "SET favRestaurant = '" 
					+ value + "' " + "WHERE customerId = " + customerId;

			Statement stmt;
			stmt = (Statement) conn.createStatement();
			nRows = stmt.executeUpdate(sql);
			if (nRows > 0)
				return "true";
		} catch (SQLException e) {
		}

		return "false";
	}

}
