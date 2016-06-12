package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.beans.OrderBean;
import com.beans.RestaurantBean;
import com.models.Branch;
import com.models.Category;
import com.models.Item;
import com.models.Menu;
import com.models.Restaurant;
import com.models.ResturantBuilder;
import com.models.Review;

public class RestaurantController {

	private static RestaurantController singleInstance = new RestaurantController();

	private ArrayList<Restaurant> restaurants;
	private ArrayList<Integer> menuIds;

	private RestaurantController() {

		try {
			buildAllRestaurants();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static RestaurantController getInstance() {
		return singleInstance;
	}

	private void buildAllRestaurants() throws SQLException {
		RestaurantBean restBean = new RestaurantBean();
		ResturantBuilder restBuilder = new ResturantBuilder();

		restaurants = new ArrayList<Restaurant>();
		menuIds = new ArrayList<Integer>();

		menuIds = restBean.getMenuIds();

		for (int menuID : menuIds) {
			Restaurant restaurant = restBuilder.buildRestaurant(menuID);
			restaurants.add(restaurant);
		}

	}

	public String buildMenuJSON(int restID) {
		JSONObject json = new JSONObject();

		for (Restaurant restaurant : restaurants) {
			Menu menu = restaurant.getMenu();

			if (menu.getMenuId() == restID) {
				for (Category category : menu.getCategories()) {

					json.put("Cat " + category.getCategoryID(),
							category.getCategoryName());

					for (Item item : category.getItems()) {
						json.put(item.getItemName(), item.getPrice());
					}
				}
			}

		}

		return json.toJSONString();
	}

	public String buildRestaurantJSON(int restID) {
		JSONObject json = new JSONObject();

		json.put("sizeMenus", menuIds.size());
		json.put("size", restaurants.size());

		for (Restaurant restaurant : restaurants) {

			if (restaurant.getRestaurantID() == restID) {
				json.put("restName", restaurant.getRestName());
				json.put("Rating", restaurant.getRating());
				json.put("Nusers", restaurant.getnUsers());
				json.put("Hotline", restaurant.getHotLine());
				json.put("deliverTo", restaurant.getDeliverTo());
				json.put("WorkingHours", restaurant.getWorkingHours());
				json.put("TimeToDeliver", restaurant.getTimeForDeliver());

				for (int i = 1; i <= restaurant.getType().length; i++)
					json.put("Type" + i, restaurant.getType()[i - 1]);
			}
		}

		return json.toJSONString();
	}
	
	public ArrayList<String> sendCustomerId(int customerId) throws SQLException
	{
		OrderBean orderBean = new OrderBean();
		ArrayList<String> favOrders = new ArrayList<String>();
		favOrders= orderBean.getFavOrders(customerId);
		
		return favOrders;
	}

}
