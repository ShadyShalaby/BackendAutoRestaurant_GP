package com.models;

import java.sql.SQLException;

import com.beans.BranchBean;
import com.beans.RestaurantBean;

public class ResturantBuilder {

	public Restaurant buildRestaurant(int restID) throws SQLException {

		Restaurant restaurant = new Restaurant();

		RestaurantBean restBean = new RestaurantBean();
		restaurant = restBean.getRestaurantInfo(restID);

		MenuBuilder menuBuilder = new MenuBuilder();
		restaurant.setMenu(menuBuilder.buildMenu(restID));
		
		BranchBean branchBean = new BranchBean();
		restaurant.setBranches(branchBean.getBranches(restID));
		
		// TODO Reviews and Offers is missing

		return restaurant;
	}
}
