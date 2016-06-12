package com.models;

public class Review {

	int reviewId;
	String comment;
	int helpful;
	int notHelpful;
	int rating;
	User reviewOwner;

	public Review(int reviewId, String comment, int helpful, int notHelpful,
			int rating, User reviewOwner) {
		this.reviewId = reviewId;
		this.comment = comment;
		this.helpful = helpful;
		this.notHelpful = notHelpful;
		this.rating = rating;
		this.reviewOwner = reviewOwner;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setHelpful(int helpful) {
		this.helpful = helpful;
	}

	public void setNotHelpful(int notHelpful) {
		this.notHelpful = notHelpful;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setReviewOwner(User reviewOwner) {
		this.reviewOwner = reviewOwner;
	}

	public int getReviewId() {
		return reviewId;
	}

	public String getComment() {
		return comment;
	}

	public int getHelpful() {
		return helpful;
	}

	public int getNotHelpful() {
		return notHelpful;
	}

	public int getRating() {
		return rating;
	}

	public User getReviewOwner() {
		return reviewOwner;
	}

}
