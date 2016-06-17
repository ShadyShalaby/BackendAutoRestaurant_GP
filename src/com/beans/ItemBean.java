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
		ArrayList<Item> items = new ArrayList<Item>();

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		ResultSet rs = stmt.executeQuery();

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

	public int[] likeItem(int itemId, int userId, int restaurantId)
			throws SQLException {

		String sql = "Select * from Item where `itemId` = ?";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, itemId);
		ResultSet rs = stmt.executeQuery();

		int likes = 0;
		int disLikes = 0;

		if (rs.next()) {
			likes = rs.getInt("likes");
			disLikes = rs.getInt("disLikes");
		}

		sql = "Select * from ItemCustomer where `itemId` = ?";

		stmt = conn.prepareStatement(sql);

		stmt.setInt(1, itemId);
		ResultSet rs2 = stmt.executeQuery();

		if (rs2.next()) {

			sql = "Update ItemCustomer " + "SET isLike=?"
					+ " where `itemId` = ? AND `customerId` = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 1);
			stmt.setInt(2, itemId);
			stmt.setInt(3, userId);
			int nRows = stmt.executeUpdate();

			if (nRows == 1) {

				likes++;
				sql = "Update Item " + "SET likes=?" + " where `itemId` =?";

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, likes);
				stmt.setInt(2, itemId);
				stmt.executeUpdate();

				disLikes--;
				sql = "Update Item " + "SET disLikes=?" + " where `itemId` =?";

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, disLikes);
				stmt.setInt(2, itemId);
				stmt.executeUpdate();
			}

		} else {

			sql = "Insert into ItemCustomer "
					+ "(customerId,itemId,restaurantId,isLike)"
					+ " VALUES  (?,?,?,?) ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, itemId);
			stmt.setInt(3, restaurantId);
			stmt.setInt(4, 1);
			stmt.executeUpdate();

			likes++;
			sql = "Update Item " + "SET likes=?" + " where `itemId` =?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likes);
			stmt.setInt(2, itemId);
			stmt.executeUpdate();

			disLikes--;
			sql = "Update Item " + "SET disLikes=?" + " where `itemId` =?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, disLikes);
			stmt.setInt(2, itemId);
			stmt.executeUpdate();

		}
		stmt.close();

		int arr[] = { likes, disLikes };

		return arr;
	}

	public int[] disLikeItem(int itemId, int userId, int restaurantId)
			throws SQLException {

		String sql = "Select * from Item where `itemId` = ?";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, itemId);
		ResultSet rs = stmt.executeQuery();

		int likes = 0;
		int disLikes = 0;

		if (rs.next()) {
			likes = rs.getInt("likes");
			disLikes = rs.getInt("disLikes");
		}

		sql = "Select * from ItemCustomer where `itemId` = ?";

		stmt = conn.prepareStatement(sql);

		stmt.setInt(1, itemId);
		ResultSet rs2 = stmt.executeQuery();

		if (rs2.next()) {

			sql = "Update ItemCustomer " + "SET isLike=?"
					+ " where `itemId` = ? AND `customerId` = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setInt(2, itemId);
			stmt.setInt(3, userId);
			int nRows = stmt.executeUpdate();

			if (nRows == 1) {

				likes--;
				sql = "Update Item " + "SET likes=?" + " where `itemId` =?";

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, likes);
				stmt.setInt(2, itemId);
				stmt.executeUpdate();

				disLikes++;
				sql = "Update Item " + "SET disLikes=?" + " where `itemId` =?";

				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, disLikes);
				stmt.setInt(2, itemId);
				stmt.executeUpdate();
			}

		} else {

			sql = "Insert into ItemCustomer "
					+ "(customerId,itemId,restaurantId,isLike)"
					+ " VALUES  (?,?,?,?) ";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userId);
			stmt.setInt(2, itemId);
			stmt.setInt(3, restaurantId);
			stmt.setInt(4, 0);
			stmt.executeUpdate();

			likes--;
			sql = "Update Item " + "SET likes=?" + " where `itemId` =?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likes);
			stmt.setInt(2, itemId);
			stmt.executeUpdate();

			disLikes++;
			sql = "Update Item " + "SET disLikes=?" + " where `itemId` =?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, disLikes);
			stmt.setInt(2, itemId);
			stmt.executeUpdate();

		}

		stmt.close();

		int arr[] = { likes, disLikes };

		return arr;
	}
	/**********************************************************************/

}
