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
	
	public ArrayList<String> getFavOrders(int customerId) throws SQLException
	{
		String sql= "SELECT itemName, itemDescription, itemPrice, orderId "
				+ "FROM Item, OrderItem WHERE Item.itemId = OrderItem.itemId"
				+ "	AND orderId IN "
				+ "(SELECT orderId FROM Orders WHERE 'customerId' =? AND isFavOrder =1)";
		
		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customerId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<String> favOrders = new ArrayList<String>();

		String order = "";
		while(rs.next())
		{
			order+=(rs.getString("itemName")+ "," + 
		    rs.getString("itemDescription")+ "," + rs.getString("itemPrice"));
			favOrders.add(order);
			order = "";
			
		}

		return favOrders;
	}
}