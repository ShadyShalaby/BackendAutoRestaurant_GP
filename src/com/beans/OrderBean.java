package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Item;
import com.models.Order;
import com.models.Reciept;

public class OrderBean {

	private Connection conn;

	public OrderBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<Item> getItemsOfOrder(int orderId) throws SQLException {
		String sql = "SELECT * FROM Item, OrderItem"
				+ " WHERE Item.itemId = OrderItem.itemId"
				+ " AND OrderItem.orderId =?";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, orderId);
		ResultSet rs = stmt.executeQuery();

		ArrayList<Item> items = new ArrayList<Item>();

		while (rs.next()) {
			Item item = new Item();
			item.setItemName(rs.getString("itemName"));
			item.setDescription(rs.getString("itemDescription"));
			item.setPrice(rs.getDouble("itemPrice"));
			item.setQuantity(rs.getInt("quantity"));

			items.add(item);
		}
		return items;
	}

	public ArrayList<Order> getFavOrders(int customerId) throws SQLException {
		String sql = "SELECT orderId" + " FROM Orders, Customer"
				+ " WHERE Orders.customerId = Customer.customerId"
				+ " AND Orders.isFavOrder = 1" + " AND Orders.customerId = ?";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Order> favOrders = new ArrayList<Order>();

		while (rs.next()) {

			Order order = new Order();
			ArrayList<Item> items = new ArrayList<Item>();

			order.setOrderID(rs.getInt("orderId"));
			items = getItemsOfOrder(order.getOrderId());
			order.setItems(items);
			favOrders.add(order);

		}

		return favOrders;
	}

	public ArrayList<Order> getPrevOrders(int customerId) throws SQLException {
		String sql = "SELECT orderId, orderTime, totalPrice"
				+ " FROM Orders, Customer, Receipt"
				+ " WHERE Orders.customerId = Customer.customerId"
				+ " AND Orders.receiptId = Receipt.receiptId"
				+ " AND Orders.customerId = ?";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Order> prevOrders = new ArrayList<Order>();

		while (rs.next()) {
			Order order = new Order();
			Reciept reciept = new Reciept();
			ArrayList<Item> items = new ArrayList<Item>();

			order.setOrderID(rs.getInt("orderId"));
			items = getItemsOfOrder(order.getOrderId());
			reciept.setOrderTime(rs.getTimestamp("orderTime"));
			reciept.setTotal(rs.getDouble("totalPrice"));
			order.setRecipt(reciept);
			order.setItems(items);

			prevOrders.add(order);

		}

		return prevOrders;
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