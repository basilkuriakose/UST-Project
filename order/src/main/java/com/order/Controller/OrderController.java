package com.order.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.order.Entity.Order;
import com.order.server.OrderService;


@RequestMapping("order")
@RestController
public class OrderController {
	
	private OrderService orderService;
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Autowired
	private RestTemplate temp;
	
	//All Orders
	@GetMapping("/all")
	public ResponseEntity<List<Order>> getAll() {
		return ResponseEntity.ok(orderService.findAll());
	}
	
	
	//Purchase Product without Cart
	@PutMapping("/{name}/{productname}/{quantity}/{paymentmethod}")
	public ResponseEntity<Order> CreateNew(@PathVariable String name,@PathVariable String productname,@PathVariable int quantity,@PathVariable String paymentmethod) {
		
			try {	
					Order order=new Order();
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jean_db?autoReconnect=true&useSSL=false","root","Basil@4445");
			        Statement stmt2=con.createStatement();
					ResultSet rs=stmt2.executeQuery("select productid,price from product where productname="+ "\"" + productname + "\"");
					if(rs.next())
					{
						Long productid=rs.getLong(1);
						double price=rs.getDouble(2);
						order.setProductid(productid);
						double discount =this.temp.getForObject("http://localhost:8084/discount/"+price,double.class);
						double total=(price*quantity)-discount;
						order.setTotalprice(total);
					}
					ResultSet rs1=stmt2.executeQuery("select uid from user where name="+  "\"" + name + "\"");
					if(rs1.next())
					{
						Long uid=rs1.getLong(1);
						order.setUid(uid);
					}
					order.setCartid(null);			
					order.setQuantity(quantity);
				    LocalDateTime now = LocalDateTime.now();  
					order.setDate(now);
					order.setPaymentmethod(paymentmethod);
					order.setStatus("Order Placed");
					return ResponseEntity.ok(orderService.save(order));
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				return null;
	}
	//Purchase Product with Cart
	@PutMapping("/cart/{name}/{productname}/{paymentmethod}")
	public ResponseEntity<Order> CreateNew1(@PathVariable String name,@PathVariable String productname,@PathVariable String paymentmethod) {
		
			try {	
					Order order=new Order();
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jean_db?autoReconnect=true&useSSL=false","root","Basil@4445");
			        Statement stmt2=con.createStatement();
					ResultSet rs=stmt2.executeQuery("select productid,price from product where productname="+ "\"" + productname + "\"");
					if(rs.next())
					{
						Long productid=rs.getLong(1);
						double price=rs.getDouble(2);
						order.setProductid(productid);
						
					ResultSet rs1=stmt2.executeQuery("select uid from user where name="+  "\"" + name + "\"");
					if(rs1.next())
					{
						Long uid=rs1.getLong(1);
						order.setUid(uid);
					
					ResultSet rs2=stmt2.executeQuery("select quantity from cart where uid="+ uid +" AND "+"productid="+productid);
					if(rs2.next())
					{
						int qty=rs2.getInt(1);
						order.setQuantity(qty);
						double discount =this.temp.getForObject("http://localhost:8084/discount/"+price,double.class);
						double total=(price*qty)-discount;
						order.setTotalprice(total);
					ResultSet rs3=stmt2.executeQuery("select cartid from cart where uid="+ uid +" AND "+"productid="+productid);
					if(rs3.next())
					{	
						Long cartid1=rs3.getLong(1);
						order.setCartid(cartid1);
					}
					}
					}
					}
					
				    LocalDateTime now = LocalDateTime.now();  
					order.setDate(now);
					order.setPaymentmethod(paymentmethod);
					order.setStatus("Order Placed");
					return ResponseEntity.ok(orderService.save(order));
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				return null;
	}
	
	// Updating status by Manager
	@PostMapping("/manager/update/{orderid}")
	public void update(@PathVariable Long orderid) {
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jean_db?autoReconnect=true&useSSL=false","root","Basil@4445");
	    Statement stmt2=con.createStatement();
	    String status="Order Delivered";
	    stmt2.executeUpdate("update order_tbl set status= "+ "\"" + status + "\"" +"where orderid="+orderid );
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
	}
	
	@DeleteMapping("/{name}/CancelOrder/{productname}")
	public void delete(@PathVariable String productname,@PathVariable String name) {
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
			stmt2.executeUpdate("delete from order_tbl where productid="+ productid +" AND "+"uid="+uid );
//			if(rs2.next())
//			{
//				Long orderid=rs2.getLong(1);
//				orderService.findById(orderid).ifPresent(orderService::delete);
//				return ResponseEntity.ok().build();
//			}
		}
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		//return null;  ResponseEntity<Order>
	}
	
}
