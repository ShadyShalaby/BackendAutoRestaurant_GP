package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Category;

public class CategoryBean {

	private Connection conn;

	public CategoryBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<Category> getCategoriesInfo(int restId)
			throws SQLException {

		String sql = "Select categoryId,categoryName from Category where `menuId` = ? ";

		PreparedStatement stmt;
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, restId);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Category> categories = new ArrayList<Category>();

		while (rs.next()) {
			Category category = new Category(rs.getInt("categoryId"),
					rs.getString("categoryName"));

			categories.add(category);

		}
		return categories;
	}

}
