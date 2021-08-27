package com.cart.Entity;

import java.time.LocalDateTime;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartid;
	@Column
	private Long productid;
	@Column
	private Long uid;
	@Column
	private LocalDateTime date;
	@Column
	private int quantity;
	public Long getCartid() {
		return cartid;
	}
	public void setCartid(Long cartid) {
		this.cartid = cartid;
	}
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Cart(Long productid, Long uid, LocalDateTime now, int quantity) {
		super();
		//this.cartid = cartid;
		this.productid = productid;
		this.uid = uid;
		this.date = now;
		this.quantity = quantity;
	}
	public Cart() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
}
