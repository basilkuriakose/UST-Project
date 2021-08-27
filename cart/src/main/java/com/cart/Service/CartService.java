package com.cart.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.cart.Entity.Cart;

@Service
public interface CartService extends JpaRepository<Cart, Long>{
	
}
