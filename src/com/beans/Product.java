package com.beans;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private int				id = 0;
	private String			name = "";
	private float			price = 0.0f;
	private String			description = "";
	private List<Comment>	comments = new ArrayList<Comment>();
	
	public Product(String n, float p, String desc) {
		name = n;
		price = p;
		description = desc;
	}
	
	public Product(int id, String n, float p, String desc, List<Comment> commentsList) {
		this.id = id;
		name = n;
		price = p;
		description = desc;
		comments = commentsList;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Comment> getComments() {
		return comments;
	}
	
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
	}
}
