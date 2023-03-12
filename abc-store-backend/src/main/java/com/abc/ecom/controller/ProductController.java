package com.abc.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.ecom.model.Product;
import com.abc.ecom.service.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable Long productId) {
		return productService.getProductById(productId);
	}
}
