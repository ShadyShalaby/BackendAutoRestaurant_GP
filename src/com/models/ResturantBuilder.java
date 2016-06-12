package com.models;

import java.sql.SQLException;

import com.beans.RestaurantBean;

public class ResturantBuilder {

	public Restaurant buildRestaurant(int restID) throws SQLException {

		Restaurant restaurant = new Restaurant();

		RestaurantBean restBean = new RestaurantBean();
		MenuBuilder menuBuilder = new MenuBuilder();

		restaurant = restBean.getRestaurantInfo(restID);
		restaurant.setMenu(menuBuilder.buildMenu(restID));

		return restaurant;
	}
}
