package com.cart.Controller;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.cart.Entity.Cart;
import com.cart.Service.CartService;

@RequestMapping("cart")
@RestController
public class CartController {
private CartService cartService;
@Autowired
public void setCartService(CartService cartService) {
	this.cartService = cartService;
}
//user name
@GetMapping("/{name}")
public List Display(@PathVariable String name) {
	try {	
			ArrayList al = new ArrayList();
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/jean_db?autoReconnect=true&useSSL=false","root","Basil@4445");
	        Statement stmt3=con1.createStatement();
			ResultSet rs1=stmt3.executeQuery("select uid from user where name="+  "\"" + name + "\"");
			
			if(rs1.next())
			{
				Long uid=rs1.getLong(1);
				ResultSet rs2=stmt3.executeQuery("select productid,quantity from cart where uid="+ uid);
				if(rs2.next())
				{
					Long pid=rs2.getLong(1);
					int qty=rs2.getInt(2);
					ResultSet rs3=stmt3.executeQuery("select * from product where productid="+ pid);
					while(rs3.next())
						{
								al.add(" ID : " +rs3.getLong(1)+"  ||  Descryption : "+rs3.getString(2)+
						"  || Feedback :  "+rs3.getString(3)+"  ||  Price :  "+rs3.getDouble(4)+"  || Name : "+rs3.getString(5)+"  "
								+ "||  Rating : "+rs3.getInt(6)+"  ||  Quantity : "+qty );
		
						}
				return al;
						
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	ArrayList bl = new ArrayList();
	bl.add("There is nothing in Cart");
	return bl;	
}

@PutMapping("/{name}/{productname}/{quantity}")
public ResponseEntity<Cart> CreateNew(@PathVariable String name,@PathVariable String productname,@PathVariable int quantity) {
	
		try {	
				Cart cart=new Cart();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jean_db?autoReconnect=true&useSSL=false","root","Basil@4445");
		        Statement stmt2=con.createStatement();
				ResultSet rs=stmt2.executeQuery("select productid from product where productname="+ "\"" + productname + "\"");
				if(rs.next())
				{
					Long productid=rs.getLong(1);
					cart.setProductid(productid);
				}
				Class.forName("com.mysql.jdbc.Driver");
				Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/jean_db?autoReconnect=true&useSSL=false","root","Basil@4445");
		        Statement stmt3=con1.createStatement();
				ResultSet rs1=stmt3.executeQuery("select uid from user where name="+  "\"" + name + "\"");
				if(rs1.next())
				{
					Long uid=rs1.getLong(1);
					cart.setUid(uid);
				}
			    LocalDateTime now = LocalDateTime.now();  
				cart.setDate(now);
				cart.setQuantity(quantity);
				return ResponseEntity.ok(cartService.save(cart));
			}
		catch(Exception e)
			{
				System.out.println(e);
			}
			return null;
}
@PostMapping("/update/{cartid}/{quantity}")
public void update(@PathVariable Long cartid,@PathVariable int quantity) {
	 	
		Configuration con=new Configuration().configure().addAnnotatedClass(Cart.class);
		ServiceRegistry reg=new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf=con.buildSessionFactory(reg);
		Session session=sf.openSession();
		Transaction tx1=session.beginTransaction();
		Cart cart = session.get(Cart.class, cartid);
		cart.setQuantity(quantity);
		System.out.println("Updated Successfully");
		tx1.commit();
}
@DeleteMapping("/{name}/{productname}")
public String delete(@PathVariable String productname,@PathVariable String name) {
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jean_db?autoReconnect=true&useSSL=false","root","Basil@4445");
    Statement stmt2=con.createStatement();
	ResultSet rs=stmt2.executeQuery("select productid from product where productname="+ "\"" + productname + "\"");
	if(rs.next())
	{
		Long productid=rs.getLong(1);
		ResultSet rs1=stmt2.executeQuery("select uid from user where name="+  "\"" + name + "\"");
		if(rs1.next())
		{
		Long uid=rs1.getLong(1);
		stmt2.executeUpdate("delete from cart where productid="+ productid +" AND "+"uid="+uid );
		return "Product Removed from Cart";
	}
	}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	return "Product not Removed";
}

}
