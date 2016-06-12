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

		String sql = "Select itemId,itemName,itemDescription,itemPrice,likes,disLikes from Item where `categoryId` = ? ";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, categoryId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Item> items = new ArrayList<Item>();

		while (rs.next()) {

			String itemName, itemDescription;
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
}
