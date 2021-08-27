package com.order.Entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Order_tbl")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderid;
	@Column
	private Long productid;
	@Column
	private Long cartid;
	@Column
	private Long uid;
	@Column
	private String paymentmethod;
	@Column
	private LocalDateTime date;
	@Column
	private double totalprice;
	@Column
	private int quantity;
	@Column
	private String status;
	
	public Long getOrderid() {
		return orderid;
	}
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	public Long getProductid() {
		return productid;
	}
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	public Long getCartid() {
		return cartid;
	}
	public void setCartid(Long cartid) {
		this.cartid = cartid;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getPaymentmethod() {
		return paymentmethod;
	}
	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public Order(Long orderid, Long productid, Long cartid, Long uid, String paymentmethod, LocalDateTime date,
			double totalprice, int quantity, String status) {
		super();
		this.orderid = orderid;
		this.productid = productid;
		this.cartid = cartid;
		this.uid = uid;
		this.paymentmethod = paymentmethod;
		this.date = date;
		this.totalprice = totalprice;
		this.quantity = quantity;
		this.status = status;
	}
	public Order() {
		//super();
		// TODO Auto-generated constructor stub
	}
	
}
