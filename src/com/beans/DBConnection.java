package com.beans;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;

	public static Connection getActiveConnection() {
		/*
		 * String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST"); String port =
		 * System.getenv("OPENSHIFT_MYSQL_DB_PORT"); System.out.println(host);
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// connection = DriverManager
			// .getConnection("jdbc:mysql://127.8.100.2:3306/se2firstapp?"
			// +
			// "user=adminYKFs38v&password=QG9RmdNVFgmc&characterEncoding=utf8");
			connection = DriverManager
					.getConnection("jdbc:mysql://127.4.232.2:3306/autorestaurant?"
							+ "user=adminLRN1eDc&password=tn8MHblsk3EE&characterEncoding=utf8");
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
