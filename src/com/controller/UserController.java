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

import com.beans.UserBean;
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

	@GET
	@Path("/signup/{userName}/{email}/{phone}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public String signup(@PathParam("userName") String userName,
			@PathParam("email") String email, @PathParam("phone") String phone,
			@PathParam("password") String pass) throws SQLException {

		UserBean userbean = new UserBean();
		int user = userbean.addUser(userName, email, phone, pass);

		JSONObject json = new JSONObject();

		json.put("status", "true");
		json.put("nRows", user);

		return json.toJSONString();
	}

	@GET
	@Path("/viewFavOrders/{customerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewFavOrders(@PathParam("customeId") int customerId)
			throws SQLException {
		ArrayList<String> favOrders = new ArrayList<String>();	

		RestaurantController restaurantController  = RestaurantController.getInstance();
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
		return restController.buildRestaurantJSON(restID);
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
