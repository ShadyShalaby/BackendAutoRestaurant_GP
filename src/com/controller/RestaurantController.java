package com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.beans.OrderBean;
import com.beans.RestaurantBean;
import com.models.Branch;
import com.models.Category;
import com.models.Item;
import com.models.Menu;
import com.models.MenuBuilder;
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

	public Menu getRestaurantMenu(int restID) {

		Menu retMenu = null;
		for (Restaurant restaurant : restaurants) {
			Menu menu = restaurant.getMenu();
			if (menu.getMenuId() == restID) {
				retMenu = menu;
				break;
			}
		}

		return retMenu;
	}

	public String convertMenuToJSON(Menu menu) {

		JSONArray jsonArray = new JSONArray();
		JSONObject jsonStatus = new JSONObject();

		if (menu == null) {
			jsonStatus.put("status", "false");
			jsonArray.add(jsonStatus);
			return jsonArray.toJSONString();
		}

		jsonStatus.put("status", "true");

		for (Category cat : menu.getCategories()) {
			String items = "";
			JSONObject json = new JSONObject();

			for (Item item : cat.getItems()) {
				items += item.getItemID() + ",," + item.getItemName() + ",,"
					    + item.getDescription() + ",," + item.getPrice() + ",,"
						+ item.getLikes() + ",," + item.getDislikes() + ",,"
						+ item.getItemPic() + "##";
			}

			json.put(cat.getCategoryID(), cat.getCategoryName() + "||" + items);
			jsonArray.add(json);
		}

		return jsonArray.toJSONString();
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

	public ArrayList<String> sendCustomerId(int customerId) throws SQLException {
		OrderBean orderBean = new OrderBean();
		ArrayList<String> favOrders = new ArrayList<String>();
		favOrders = orderBean.getFavOrders(customerId);

		return favOrders;
	}

	/************************** By Sheref Shokry **************************/

	public ArrayList<Restaurant> searchRestaurantByName(String restaurantName)
			throws SQLException {

		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();

		for (Restaurant rest : restaurants) {
			if (rest.getRestName().contains(restaurantName))
				restaurantList.add(rest);
		}

		return restaurantList;
	}

	public ArrayList<Menu> searchMenuByCategory(String category)
			throws SQLException {

		ArrayList<Menu> menuList = new ArrayList<Menu>();

		for (Restaurant rest : restaurants) {
			MenuBuilder menu = new MenuBuilder();
			for (Category cat : rest.getMenu().getCategories()) {
				if (cat.getCategoryName().contains(category))
					menuList.add(menu.buildMenu(rest.getRestaurantID()));
			}
		}

		return menuList;
	}

	/**********************************************************************/

}
