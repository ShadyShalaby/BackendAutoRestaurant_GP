package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Order;
import com.models.User;

public class OrderBean {

	private Connection conn;

	public OrderBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<String> getFavOrders(int customerId) throws SQLException {
		String sql = "SELECT itemName, itemDescription, itemPrice, orderId "
				+ "FROM Item, OrderItem WHERE Item.itemId = OrderItem.itemId"
				+ "	AND orderId IN "
				+ "(SELECT orderId FROM Orders WHERE 'customerId' =? AND isFavOrder =1)";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<String> favOrders = new ArrayList<String>();

		String order = "";
		while (rs.next()) {
			order += (rs.getString("itemName") + ","
					+ rs.getString("itemDescription") + "," + rs
					.getString("itemPrice"));
			favOrders.add(order);
			order = "";

		}

		return favOrders;
	}

	public int addOrder(int customerId, int receiptId, int cashierId,
			int branchId, int cornerId, int restaurantId, boolean isFavOrder)
			throws SQLException {
		String sql = "Insert into Orders "
				+ "(customerId,receiptId,cashierId,branchId,cornerId,restaurantId,isFavOrder)"
				+ " VALUES  (?,?,?,?,?,?,?) ";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customerId);
		stmt.setInt(2, receiptId);
		stmt.setInt(3, cashierId);
		stmt.setInt(4, branchId);
		stmt.setInt(5, cornerId);
		stmt.setInt(6, restaurantId);
		stmt.setBoolean(7, isFavOrder);

		int id = 0;
		try {
			int nRows = stmt.executeUpdate(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next())
				id = rs.getInt(1);

		} catch (SQLException e) {
		}

		return id;
	}

}