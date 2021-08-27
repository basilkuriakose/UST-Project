package com.product.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.product.Entity.Product;

@Service
public interface ProductService extends JpaRepository<Product, Long>{

}
