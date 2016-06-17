package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.models.User;

public class RecieptBean {

	private Connection conn;

	public RecieptBean() {
		conn = DBConnection.getActiveConnection();
	}

	public int addReceipt(double subTotal, double tax, double services,
			double total, Timestamp orderTime) throws SQLException {
		String sql = "Insert into Receipt "
				+ "(subTotal,tax,restServices,totalPrice,orderTime)"
				+ " VALUES  (?,?,?,?) ";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setDouble(1, subTotal);
		stmt.setDouble(2, tax);
		stmt.setDouble(3, services);
		stmt.setDouble(4, total);
		stmt.setTimestamp(5, orderTime);

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