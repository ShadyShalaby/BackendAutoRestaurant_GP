package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.User;

public class RecieptBean {

	private Connection conn;

	public RecieptBean() {
		conn = DBConnection.getActiveConnection();
	}
}