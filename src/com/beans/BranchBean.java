package com.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Branch;
import com.mysql.jdbc.Statement;

public class BranchBean {

	private Connection conn;

	public BranchBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<Branch> getBranches(int restID) throws SQLException {

		ArrayList<Branch> branches = new ArrayList<Branch>();

		String sql = "Select * form Branch where restaurantId = " + restID;

		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setAddress(rs.getString("branchAddress"));
			branch.setLocation(rs.getString("location"));
			// TODO Corners is missing

			branches.add(branch);
		}

		return branches;
	}
}