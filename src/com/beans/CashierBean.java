package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.User;

public class CashierBean {

	private Connection conn;

	public CashierBean() {
		conn = DBConnection.getActiveConnection();
	}
}