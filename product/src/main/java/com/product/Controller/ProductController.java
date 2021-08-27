package com.product.Controller;

import java.util.List;

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

import com.product.Entity.Product;
import com.product.Service.ProductService;


@RequestMapping("product")
@RestController
public class ProductController {
	private ProductService productService;
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAll() {
		return ResponseEntity.ok(productService.findAll());
	}
	
	@GetMapping("/{productid}")
	public ResponseEntity<Product> findById(@PathVariable Long productid) {
		return ResponseEntity.ok(productService.findById(productid).orElse(null));
	}
	
	@PutMapping("/add")
	public ResponseEntity<Product> createNew(@RequestBody Product p) {
		return ResponseEntity.ok(productService.save(p));
	}
	
	@PostMapping("/update")
	public ResponseEntity<Product> update(@RequestBody Product p) {
		return ResponseEntity.ok(productService.save(p));
	}
	
	

	@DeleteMapping("/{productid}")
	public ResponseEntity<Product> delete(@PathVariable Long productid) {
		productService.findById(productid).ifPresent(productService::delete);
		return ResponseEntity.ok().build();
	}
}
