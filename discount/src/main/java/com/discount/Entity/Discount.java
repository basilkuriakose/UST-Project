package com.discount.Entity;

import javax.persistence.Entity;


public class Discount {
private double discount;
private double price;
public double getDiscount() {
	return discount;
}

public void setDiscount(double discount) {
	this.discount = discount;
}


public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public Discount(double discount, double price) {
	super();
	this.discount = discount;
	this.price = price;
}

public Discount() {
	//super();
	// TODO Auto-generated constructor stub
}

}
