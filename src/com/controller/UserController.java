package com.controller;

import java.sql.SQLException;
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
import com.beans.UserBean;
import com.models.Item;
import com.models.Menu;
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

	@GET
	@Path("/viewFavOrders/{customerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewFavOrders(@PathParam("customeId") int customerId)
			throws SQLException {
		ArrayList<String> favOrders = new ArrayList<String>();

		RestaurantController restaurantController = RestaurantController
				.getInstance();
		favOrders = restaurantController.sendCustomerId(customerId);
		JSONArray jsonArray = new JSONArray();

		for (int iter = 0; iter < favOrders.size(); iter++) {
			JSONObject json = new JSONObject();
			json.put("favOrder " + iter, favOrders.get(iter));
			jsonArray.add(json);
		}

		return jsonArray.toJSONString();
	}

	@GET
	@Path("/ProcessQRCode/{restID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String ProcessQRCode(@PathParam("restID") int restID) {

		RestaurantController restController = RestaurantController
				.getInstance();

		Menu menu = restController.getRestaurantMenu(restID);

		return restController.convertMenuToJSON(menu);
	}

	/************************** By Sheref Shokry **************************/
	@POST
	@Path("/likeItem")
	@Produces(MediaType.TEXT_PLAIN)
	public String like(@FormParam("itemId") int itemId,
			@FormParam("userId") int userId,
			@FormParam("restaurantId") int restaurantId) throws SQLException {

		ItemBean itembean = new ItemBean();
		int likes = itembean.likeItem(itemId, userId, restaurantId);

		JSONObject json = new JSONObject();

		json.put("status", "true");
		json.put("#ofLikes", likes);

		return json.toJSONString();
	}

	@POST
	@Path("/disLikeItem")
	@Produces(MediaType.TEXT_PLAIN)
	public String disLike(@FormParam("itemId") int itemId,
			@FormParam("userId") int userId,
			@FormParam("restaurantId") int restaurantId) throws SQLException {

		ItemBean itembean = new ItemBean();
		int disLikes = itembean.disLikeItem(itemId, userId, restaurantId);

		JSONObject json = new JSONObject();

		json.put("status", "true");
		json.put("#ofdisLikes", disLikes);

		return json.toJSONString();
	}

	@POST
	@Path("/searchByName")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchByName(
			@FormParam("restaurantName") String restaurantName)
			throws SQLException {
		RestaurantController restController = RestaurantController
				.getInstance();

		ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
		restaurantList = restController.searchRestaurantByName(restaurantName);

		JSONArray jsArray = new JSONArray();

		for (int i = 0; i < restaurantList.size(); i++) {
			JSONObject jsObj = new JSONObject();

			jsObj.put("RestaurantID", restaurantList.get(i).getRestaurantID());
			jsObj.put("RestaurantName", restaurantList.get(i).getRestName());
			jsObj.put("RestaurantLogo", restaurantList.get(i).getLogo());
			jsObj.put("RestaurantRating", restaurantList.get(i).getRating());
			jsObj.put("RestaurantHotline", restaurantList.get(i).getHotLine());
			jsObj.put("RestaurantType", restaurantList.get(i).getType()
					.toString());
			jsObj.put("RestaurantWorkingHours", restaurantList.get(i)
					.getWorkingHours());

			jsArray.add(jsObj);
		}

		return jsArray.toJSONString();
	}

	@POST
	@Path("/searchByCategory")
	@Produces(MediaType.TEXT_PLAIN)
	public String searchByCategory(@FormParam("category") String category)
			throws SQLException {
		RestaurantController restController = RestaurantController
				.getInstance();

		ArrayList<Menu> menuList = new ArrayList<Menu>();
		menuList = restController.searchMenuByCategory(category);

		JSONArray jsArray = new JSONArray();

		for (int i = 0; i < menuList.size(); i++) {

			JSONObject jsObj = new JSONObject();
			jsObj.put("MenuID", menuList.get(i).getMenuId());
			jsArray.add(jsObj);
		}

		return jsArray.toJSONString();
	}

	/**********************************************************************/
	
	@GET
	@Path("/testCatItems/{catID}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCatItems(@PathParam("catID") int category) {
		ItemBean itemBean = new ItemBean();
		String str = "";
		try {
			ArrayList<Item>items = itemBean.getCategoryItems(category);
			for(Item item : items){
				str += item.getItemID() + ",," + item.getItemName() + ",,"
					    + item.getDescription() + ",," + item.getPrice() + ",,"
						+ item.getLikes() + ",," + item.getDislikes() + ",,"
						+ item.getItemPic() + "##";				
			}
		} catch (SQLException e) {
			str += e.toString();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
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
