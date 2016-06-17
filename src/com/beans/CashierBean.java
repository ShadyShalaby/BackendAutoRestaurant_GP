package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.Cashier;
import com.models.User;

public class CashierBean {

	private Connection conn;

	public CashierBean() {
		conn = DBConnection.getActiveConnection();
	}

	public Cashier getCashierInfo(int resturntId) throws SQLException {

		String sql = "Select * from Cashier where `isloged` =? AND  `resturntId` =? ";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, 1);
		stmt.setInt(2, resturntId);
		ResultSet rs = stmt.executeQuery();
		int cashierId = 0;
		String cashierName = "";
		if (rs.next()) {
			cashierId = rs.getInt("cashierId");
			cashierName = rs.getString("cashierName");
		}
		Cashier cashier = new Cashier(cashierId, cashierName);

		return cashier;
	}

}