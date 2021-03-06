package com.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.beans.ItemBean;
import com.beans.OrderBean;
import com.beans.UserBean;
import com.models.Branch;
import com.models.Item;
import com.models.Menu;
import com.models.Order;
import com.models.Restaurant;
import com.models.User;

@Path("/")
public class UserController {

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	public String logIn(@FormParam("email") String email,
			@FormParam("pass") String pass) throws SQLException {

		UserBean userbean = new UserBean();
		User user = userbean.getUser(email, pass);

		JSONObject json = new JSONObject();
		if (user == null)
			json.put("status", "false");
		else {
			json.put("status", "true");
			json.put("userID", user.getUserID());
			json.put("userName", user.getUserName());
			json.put("email", user.getEmail());
			json.put("pass", user.getPassword());
			json.put("phone", user.getPhone());
		}

		return json.toJSONString();
	}

	@POST
	@Path("/signup")
	@Produces(MediaType.TEXT_PLAIN)
	public String signup(@FormParam("userName") String userName,
			@FormParam("email") String email, @FormParam("phone") String phone,
			@FormParam("pass") String pass) throws SQLException {

		UserBean userbean = new UserBean();
		User user = userbean.addUser(userName, email, phone, pass);

		JSONObject json = new JSONObject();
		if (user == null)
			json.put("status", "false");
		else {
			json.put("status", "true");
			json.put("userID", user.getUserID());
			json.put("userName", user.getUserName());
			json.put("email", user.getEmail());
			json.put("pass", user.getPassword());
			json.put("phone", user.getPhone());
		}

		return json.toJSONString();
	}

	@POST
	@Path("/ProcessQRCode")
	@Produces(MediaType.TEXT_PLAIN)
	public String ProcessQRCode(@FormParam("restID") int restID) {

		RestaurantController restController = RestaurantController
				.getInstance();

		Menu menu = restController.getRestaurantMenu(restID);

		return restController.convertMenuToJSON(menu).toJSONString();
	}

	@POST
	@Path("/likeItem")
	@Produces(MediaType.TEXT_PLAIN)
	public String like(@FormParam("itemId") int itemId,
			@FormParam("userId") int userId,
			@FormParam("restaurantId") int restaurantId) throws SQLException {

		RestaurantController restController = RestaurantController
				.getInstance();

		int arr[] = restController.likeItem(restaurantId, itemId, userId);

		JSONObject json = new JSONObject();

		json.put("status", "true");
		json.put("nLikes", arr[0]);
		json.put("nDisLikes", arr[1]);

		return json.toJSONString();
	}

	@POST
	@Path("/disLikeItem")
	@Produces(MediaType.TEXT_PLAIN)
	public String disLike(@FormParam("itemId") int itemId,
			@FormParam("userId") int userId,
			@FormParam("restaurantId") int restaurantId) {

		RestaurantController restController = RestaurantController
				.getInstance();
		int arr[] = null;
		try {
			arr = restController.dislikeItem(restaurantId, itemId, userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject json = new JSONObject();

		json.put("status", "true");
		json.put("nLikes", arr[0]);
		json.put("nDisLikes", arr[1]);

		return json.toJSONString();
	}

	@POST
	@Path("/searchByName")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchByName(
			@FormParam("restaurantName") String restaurantName) {
		RestaurantController restController = RestaurantController
				.getInstance();

		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
		restaurantList = restController.searchRestaurantByName(restaurantName);

		JSONArray jsArray = new JSONArray();

		for (Restaurant restaurant : restaurantList) {
			JSONObject jsObj = restController
					.convertRestaurantToJSON(restaurant);

			jsArray.add(jsObj);
		}

		JSONObject json = new JSONObject();
		json.put("list", jsArray);
		return json.toJSONString();
	}

	@POST
	@Path("/searchByCategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchByCategory(@FormParam("category") String category) {
		RestaurantController restController = RestaurantController
				.getInstance();

		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
		restaurantList = restController.searchRestaurantByCategory(category);

		JSONArray jsArray = new JSONArray();

		for (Restaurant restaurant : restaurantList) {
			JSONObject jsObj = restController
					.convertRestaurantToJSON(restaurant);

			jsArray.add(jsObj);
		}

		JSONObject json = new JSONObject();
		json.put("list", jsArray);
		return json.toJSONString();
	}

	@POST
	@Path("/addFavOrder")
	@Produces(MediaType.TEXT_PLAIN)
	public String addFavOrder(@FormParam("customerId") int customerId,
			@FormParam("orderId") int orderId) throws SQLException {

		RestaurantController restController = RestaurantController
				.getInstance();
		String status = restController.addFavOrder(customerId, orderId);

		JSONObject json = new JSONObject();
		json.put("status", status);
		return json.toJSONString();
	}

	@POST
	@Path("/addFavRestaurant")
	@Produces(MediaType.TEXT_PLAIN)
	public String addFavRestaurant(@FormParam("customerId") int customerId,
			@FormParam("restID") int restID) throws SQLException {

		RestaurantController restController = RestaurantController
				.getInstance();
		String status = restController.addFavRestaurant(customerId, restID);

		JSONObject json = new JSONObject();
		json.put("status", status);

		return json.toJSONString();
	}

	@POST
	@Path("/updateProfile")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProfile(@FormParam("userName") String userName,
			@FormParam("email") String email, @FormParam("phone") String phone,
			@FormParam("password") String pass,
			@FormParam("customerId") int customerId) throws SQLException {

		UserBean userBean = new UserBean();
		String state = userBean.updateUser(userName, email, phone, pass,
				customerId);

		JSONObject json = new JSONObject();
		json.put("status", state);

		return json.toJSONString();
	}

	@POST
	@Path("/getRestTaxServices")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRestaurantTaxServices(@FormParam("restID") int restID)
			throws SQLException {

		RestaurantController restController = RestaurantController
				.getInstance();

		JSONObject json = restController.getRestaurantTaxJSON(restID);

		return json.toJSONString();
	}

	@POST
	@Path("/getRestaurantMenu")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRestaurantMenu(@FormParam("restID") int restID) {

		RestaurantController restController = RestaurantController
				.getInstance();

		Menu menu = restController.getRestaurantMenu(restID);

		return restController.convertMenuToJSON(menu).toJSONString();
	}

	@POST
	@Path("/getRestaurantBranches")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRestaurantBranches(@FormParam("restID") int restID) {

		RestaurantController restController = RestaurantController
				.getInstance();

		ArrayList<Branch> branches = restController.getBranches(restID);

		return restController.convertBranchesToJSON(branches).toJSONString();
	}

	/*********************************************************************/

	@GET
	@Path("/orderProcessing/{subTotal}/{tax}/{service}/{total}/{orderTime}/{tableNumber}/{restId}/{cornerId}/{customerId}/{branchId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String orderProcessing(@PathParam("subTotal") double subTotal,
			@PathParam("tax") double services,
			@PathParam("service") double tax, @PathParam("total") double total,
			@PathParam("orderTime") Timestamp orderTime,
			@PathParam("tableNumber") int tableNumber,
			@PathParam("restId") int resturntId,
			@PathParam("cornerId") int cornerId,
			@PathParam("customerId") int customerId,
			@PathParam("branchId") int branchId) throws SQLException {

		RestaurantController restController = RestaurantController
				.getInstance();

		int orderId = restController.createOrder(false, subTotal, tax,
				services, total, orderTime, tableNumber, resturntId, cornerId,
				customerId, branchId);

		JSONObject json = new JSONObject();

		json.put("status", "true");
		json.put("orderId", orderId);

		return json.toJSONString();

	}

	@GET
	@Path("/viewFavOrders/{customerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewFavOrders(@PathParam("customerId") int customerId)
			throws SQLException {
		ArrayList<Order> favOrders = new ArrayList<Order>();

		RestaurantController restaurantController = RestaurantController
				.getInstance();
		favOrders = restaurantController.findFavOrders(customerId);
		JSONArray jsonArray = new JSONArray();

		for (Order order : favOrders) {
			JSONObject json = new JSONObject();
			String items = "";
			for (Item item : order.getItems()) {
				items += item.getItemName() + ",," + item.getDescription()
						+ ",," + item.getPrice() + ",," + item.getQuantity()
						+ "##";
			}

			json.put(order.getOrderId(), items);
			jsonArray.add(json);
		}

		return jsonArray.toJSONString();
	}

	@GET
	@Path("/viewPrevOrders/{customerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewPrevOrders(@PathParam("customerId") int customerId)
			throws SQLException {
		ArrayList<Order> prevOrders = new ArrayList<Order>();

		RestaurantController restaurantController = RestaurantController
				.getInstance();

		prevOrders = restaurantController.findPrevOrders(customerId);

		JSONArray jsonArray = new JSONArray();

		for (Order order : prevOrders) {
			JSONObject json = new JSONObject();
			String rec = order.getRecipt().getOrderTime() + ",,"
					+ order.getRecipt().getTotal();
			String items = "";
			for (Item item : order.getItems()) {
				items += item.getItemName() + ",," + item.getDescription()
						+ ",," + item.getPrice() + ",," + item.getQuantity()
						+ "##";
			}

			json.put(order.getOrderId(), rec + "@@" + items);
			jsonArray.add(json);
		}

		return jsonArray.toJSONString();
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getJson() {
		return "Hello after editing";
		// Connection URL:
		// mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/

	}
}
