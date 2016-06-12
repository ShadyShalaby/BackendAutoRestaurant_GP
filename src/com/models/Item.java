package com.models;

public class Item {

	private int itemID;
	private String itemName;
	private String description;
	private double price;
	private int likes;
	private int dislikes;
	private String itemPic;

	public Item(int itemID, String itemName, String description, double price,
			int likes, int dislikes) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.description = description;
		this.price = price;
		this.likes = likes;
		this.dislikes = dislikes;
//		this.itemPic = itemPic;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public String getItemPic() {
		return itemPic;
	}

	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}

}
