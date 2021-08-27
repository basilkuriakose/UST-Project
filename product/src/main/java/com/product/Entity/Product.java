package com.product.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productid;
	@Column
	private String productname;
	@Column
	private double price;
	@Column
	private String description;
	@Column
	private String feedback;
	@Column
	private int rating;
	
	
	public Product(Long id, String name, double price, String description, String feedback, int rating) {
		super();
		this.productid = id;
		this.productname = name;
		this.price = price;
		this.description = description;
		this.feedback = feedback;
		this.rating = rating;
	}
	
	public Product() {
		//super();
		// TODO Auto-generated constructor stub
	}

	
	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
