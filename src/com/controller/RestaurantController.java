package com.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.beans.CashierBean;
import com.beans.OrderBean;
import com.beans.RecieptBean;
import com.beans.RestaurantBean;
import com.models.Branch;
import com.models.Cashier;
import com.models.Category;
import com.models.Corner;
import com.models.Item;
import com.models.Menu;
import com.models.MenuBuilder;
import com.models.Order;
import com.models.Reciept;
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

			for (Item item : cat.getItems()) {
				items += item.getItemID() + ",," + item.getItemName() + ",,"
						+ item.getDescription() + ",," + item.getPrice() + ",,"
						+ item.getLikes() + ",," + item.getDislikes() + ",,"
						+ item.getItemPic() + "##";
			}

			jsonArray.add(cat.getCategoryName() + "@@" + items);
		}

		JSONObject json = new JSONObject();
		json.put("menu", jsonArray);
		return json.toJSONString();
	}

	/************************** By Sheref Shokry **************************/

	public ArrayList<Restaurant> searchRestaurantByName(String restaurantName) {

		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();

		for (Restaurant rest : restaurants) {
			if (rest.getRestName().contains(restaurantName))
				restaurantList.add(rest);
		}

		return restaurantList;
	}

	public ArrayList<Restaurant> searchRestaurantByCategory(String category) {

		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();

		for (Restaurant rest : restaurants) {

			for (String type : rest.getType()) {
				if (type.contains(category)) {
					restaurantList.add(rest);
					break;
				}
			}
		}

		/*
		 * ArrayList<Menu> menuList = new ArrayList<Menu>(); MenuBuilder menu =
		 * new MenuBuilder();
		 * 
		 * for (Category cat : rest.getMenu().getCategories()) { if
		 * (cat.getCategoryName().contains(category))
		 * menuList.add(menu.buildMenu(rest.getRestaurantID())); } return
		 * menuList;
		 */

		return restaurantList;
	}

	public JSONObject convertRestaurantToJSON(Restaurant restaurant) {
		JSONObject jsObj = new JSONObject();

		jsObj.put("RestID", restaurant.getRestaurantID());
		jsObj.put("RestName", restaurant.getRestName());
		jsObj.put("RestLogo", restaurant.getLogo());
		jsObj.put("RestRating", restaurant.getRating());
		jsObj.put("RestHotline", restaurant.getHotLine());
		jsObj.put("RestType", String.join(",", restaurant.getType().toString()));
		jsObj.put("RestWHours", restaurant.getWorkingHours());

		return jsObj;
	}

	/**********************************************************************/

	public int createOrder(boolean isFavOrder, double subTotal, double tax,
			double services, double total, Timestamp orderTime,
			int tableNumber, int restaurantId, int cornerId, int customerId,
			int branchId) throws SQLException {
		CashierBean cashierBean = new CashierBean();
		RecieptBean recieptBean = new RecieptBean();
		OrderBean orderBean = new OrderBean();

		Cashier cashier = cashierBean.getCashierInfo(restaurantId);
		int recieptID = recieptBean.addReceipt(subTotal, tax, services, total,
				orderTime);
		int orderID = orderBean.addOrder(customerId, recieptID,
				cashier.getCashierId(), branchId, cornerId, restaurantId,
				isFavOrder);

		Reciept receipt = new Reciept(recieptID, subTotal, tax, services,
				total, orderTime);
		Corner corner = new Corner(cornerId, tableNumber);
		Order order = new Order(orderID, isFavOrder, receipt, corner, cashier);

		return orderID;
	}

	public ArrayList<String> sendCustomerId(int customerId) throws SQLException {
		OrderBean orderBean = new OrderBean();
		ArrayList<String> favOrders = new ArrayList<String>();
		favOrders = orderBean.getFavOrders(customerId);

		return favOrders;
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

}
