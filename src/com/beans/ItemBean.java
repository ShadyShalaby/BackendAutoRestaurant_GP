package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Item;

public class ItemBean {

	private Connection conn;

	public ItemBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<Item> getCategoryItems(int categoryId) throws SQLException {

		String sql = "Select * from Item where `categoryId` = ? ";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Item> items = new ArrayList<Item>();

		while (rs.next()) {

			String itemName, itemDescription, imagePath;
			int itemPrice, likes, disLikes, itemId;

			itemName = rs.getString("itemName");
			itemDescription = rs.getString("itemDescription");

			itemPrice = rs.getInt("itemPrice");
			likes = rs.getInt("likes");
			disLikes = rs.getInt("dislikes");
			itemId = rs.getInt("itemId");

			Item item = new Item(itemId, itemName, itemDescription, itemPrice,
					likes, disLikes);

			items.add(item);
		}

		return items;
	}

	/************************** By Sheref Shokry **************************/

	public int likeItem(int itemId, int userId, int restaurantId)
			throws SQLException {

		String sql = "Select * from Item where `itemId` =? ";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);

		stmt.setInt(1, itemId);
		ResultSet rs = stmt.executeQuery();

		int likes = 0;
		int disLikes = 0;
		if (rs.next()) {
			likes = rs.getInt("likes");
			disLikes = rs.getInt("disLikes");
			likes++;

			sql = "Update Item " + "SET likes=?" + " where `itemId` =?";

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql);
			stmt2.setInt(1, likes);
			stmt2.setInt(2, itemId);
			stmt2.executeUpdate();

		}

		sql = "Select * from ItemCustomer where `itemId` =? ";

		PreparedStatement stmt3;
		stmt3 = conn.prepareStatement(sql);

		stmt3.setInt(1, itemId);
		ResultSet rs2 = stmt3.executeQuery();

		if (!rs2.next()) {

			sql = "Insert into ItemCustomer "
					+ "(customerId,itemId,restaurantId,isLike)"
					+ " VALUES  (?,?,?,?) ";

			PreparedStatement stmt4;
			stmt4 = conn.prepareStatement(sql);
			stmt4.setInt(1, userId);
			stmt4.setInt(2, itemId);
			stmt4.setInt(3, restaurantId);
			stmt4.setInt(4, 1);
			stmt4.executeUpdate();

		} else {

			sql = "Update ItemCustomer " + "SET isLike=?"
					+ " where `itemId` =?";

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql);
			stmt2.setInt(1, 1);
			stmt2.setInt(2, itemId);
			stmt2.executeUpdate();

			disLikes--;
			sql = "Update Item " + "SET disLikes=?" + " where `itemId` =?";

			PreparedStatement stmt5;
			stmt5 = conn.prepareStatement(sql);
			stmt5.setInt(1, disLikes);
			stmt5.setInt(2, itemId);
			stmt5.executeUpdate();

		}

		return likes;

	}

	public int disLikeItem(int itemId, int userId, int restaurantId)
			throws SQLException {

		String sql = "Select * from Item where `itemId` =? ";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);

		stmt.setInt(1, itemId);
		ResultSet rs = stmt.executeQuery();

		int disLikes = 0;
		int likes = 0;
		if (rs.next()) {
			disLikes = rs.getInt("disLikes");
			likes = rs.getInt("likes");
			disLikes++;

			sql = "Update Item " + "SET disLikes=?" + " where `itemId` =?";

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql);
			stmt2.setInt(1, disLikes);
			stmt2.setInt(2, itemId);
			stmt2.executeUpdate();
		}

		sql = "Select * from ItemCustomer where `itemId` =? ";

		PreparedStatement stmt3;
		stmt3 = conn.prepareStatement(sql);

		stmt3.setInt(1, itemId);
		ResultSet rs2 = stmt3.executeQuery();

		if (!rs2.next()) {

			sql = "Insert into ItemCustomer "
					+ "(customerId,itemId,restaurantId,isLike)"
					+ " VALUES  (?,?,?,?) ";

			PreparedStatement stmt4;
			stmt4 = conn.prepareStatement(sql);
			stmt4.setInt(1, userId);
			stmt4.setInt(2, itemId);
			stmt4.setInt(3, restaurantId);
			stmt4.setInt(4, 0);
			stmt4.executeUpdate();
		} else {
			sql = "Update ItemCustomer " + "SET isLike=?"
					+ " where `itemId` =?";

			PreparedStatement stmt2;
			stmt2 = conn.prepareStatement(sql);
			stmt2.setInt(1, 0);
			stmt2.setInt(2, itemId);
			stmt2.executeUpdate();

			likes--;
			sql = "Update Item " + "SET likes=?" + " where `itemId` =?";

			PreparedStatement stmt5;
			stmt5 = conn.prepareStatement(sql);
			stmt5.setInt(1, likes);
			stmt5.setInt(2, itemId);
			stmt5.executeUpdate();

		}

		return disLikes;
	}
	/**********************************************************************/

}
