package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.User;

public class AdminBean {

	private Connection conn;

	public AdminBean() {
		conn = DBConnection.getActiveConnection();
	}
}