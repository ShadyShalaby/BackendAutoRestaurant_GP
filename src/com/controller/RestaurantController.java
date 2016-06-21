package com.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.models.ResturantBuilder;
import com.beans.CashierBean;
import com.beans.ItemBean;
import com.beans.OrderBean;
import com.beans.RecieptBean;
import com.beans.RestaurantBean;
import com.models.Branch;
import com.models.Cashier;
import com.models.Category;
import com.models.Corner;
import com.models.Item;
import com.models.Menu;
import com.models.Order;
import com.models.Reciept;
import com.models.Restaurant;

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

	public JSONObject convertRestaurantToJSON(Restaurant restaurant) {
		JSONObject jsObj = new JSONObject();

		jsObj.put("RestID", restaurant.getRestaurantID());
		jsObj.put("RestName", restaurant.getRestName());
		jsObj.put("RestLogo", restaurant.getLogo());
		jsObj.put("RestRating", restaurant.getRating());
		jsObj.put("RestHotline", restaurant.getHotLine());
		jsObj.put("RestType",
				Arrays.toString(restaurant.getType()).replace(", ", ",")
						.replaceAll("[\\[\\]]", ""));
		jsObj.put("RestWHours", restaurant.getWorkingHours());

		return jsObj;
	}

	public JSONObject convertMenuToJSON(Menu menu) {

		JSONObject json = new JSONObject();

		if (menu == null) {
			json.put("status", "false");
			return json;
		}

		JSONArray jsonArray = new JSONArray();

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

		json.put("status", "true");
		json.put("menu", jsonArray);

		return json;
	}

	public JSONObject convertBranchesToJSON(ArrayList<Branch> branches) {

		JSONObject json = new JSONObject();

		if (branches == null) {
			json.put("status", "false");
			return json;
		}

		JSONArray jsonArray = new JSONArray();

		for (Branch branch : branches) {
			JSONObject jsObj = new JSONObject();

			jsObj.put("branchID", branch.getBranchId());
			jsObj.put("address", branch.getAddress());
			jsObj.put("location", branch.getLocation());

			jsonArray.add(jsObj);
		}

		json.put("status", "true");
		json.put("branches", jsonArray);

		return json;
	}

	public JSONObject getRestaurantTaxJSON(int restID) {

		JSONObject json = new JSONObject();
		;
		for (Restaurant rest : restaurants) {
			if (rest.getRestaurantID() == restID) {
				json.put("tax", rest.getTax());
				json.put("services", rest.getServices());
				json.put("status", "true");
				return json;
			}
		}

		json.put("status", "false");
		return json;
	}

	public ArrayList<Branch> getBranches(int restID) {

		for (Restaurant restaurant : restaurants) {
			if (restaurant.getRestaurantID() == restID) {
				return restaurant.getBranches();
			}
		}
		return null;
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

		return restaurantList;
	}


	
	public int[] likeItem(int restID, int itemID, int userID)
			throws SQLException {

		ItemBean itembean = new ItemBean();
		int[] arr = itembean.likeItem(itemID, userID, restID);

		updateItemLikes(restID, itemID, arr);

		return arr;
	}

	public int[] dislikeItem(int restID, int itemID, int userID)
			throws SQLException {

		ItemBean itembean = new ItemBean();
		int[] arr = itembean.disLikeItem(itemID, userID, restID);

		updateItemLikes(restID, itemID, arr);

		return arr;
	}

	public boolean updateItemLikes(int restID, int itemID, int[] likes) {

		for (Restaurant restaurant : restaurants) {

			if (restaurant.getRestaurantID() == restID) {
				for (Category category : restaurant.getMenu().getCategories()) {
					for (Item item : category.getItems()) {
						if (item.getItemID() == itemID) {
							item.setLikes(likes[0]);
							item.setDislikes(likes[1]);
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**************************************************************************/

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

	public ArrayList<Order> findFavOrders(int customerId) throws SQLException {

		OrderBean orderBean = new OrderBean();
		ArrayList<Order> favOrders = new ArrayList<Order>();
		favOrders = orderBean.getFavOrders(customerId);

		return favOrders;
	}

	public ArrayList<Order> findPrevOrders(int customerId) throws SQLException {

		OrderBean orderBean = new OrderBean();
		ArrayList<Order> prevOrders = new ArrayList<Order>();
		prevOrders = orderBean.getPrevOrders(customerId);

		return prevOrders;
	}

	public String addFavRestaurant(int customerId, int restID)
			throws SQLException {
		RestaurantBean restaurantBean = new RestaurantBean();
		return restaurantBean.AddFavRestaurant(customerId, restID);
	}

	public String addFavOrder(int customerId, int orderId) throws SQLException {
		OrderBean orderBean = new OrderBean();
		return orderBean.addFavOrder(customerId, orderId);
	}


	/**************************************************************************/

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
