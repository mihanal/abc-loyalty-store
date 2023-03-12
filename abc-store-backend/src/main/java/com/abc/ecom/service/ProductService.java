package com.abc.ecom.service;

import java.util.List;

import com.abc.ecom.model.Product;

public interface ProductService {

	/**
	 * Get all products
	 * @return product list
	 */
	public List<Product> getAllProducts();
	
	/**
	 * Get product by id
	 * @param productId
	 * @return product
	 */
	public Product getProductById(Long productId);
}
