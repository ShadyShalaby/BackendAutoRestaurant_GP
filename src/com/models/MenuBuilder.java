package com.models;

import java.sql.SQLException;
import java.util.ArrayList;

import com.beans.CategoryBean;
import com.beans.ItemBean;

public class MenuBuilder {

	public Menu buildMenu(int restId) throws SQLException {
		Menu menu = new Menu(restId);
		CategoryBean catBean = new CategoryBean();
		ArrayList<Category> categories = new ArrayList<Category>();

		categories = catBean.getCategoriesInfo(restId);
		ItemBean itemBean = new ItemBean();

		for (Category cat : categories) {
			cat.setItems( itemBean.getCategoryItems(cat.getCategoryID()) );
		}

		menu.setCategories(categories);

		return menu;
	}
}
