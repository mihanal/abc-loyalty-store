package com.abc.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.ecom.exception.ResourceNotFoundException;
import com.abc.ecom.model.Product;
import com.abc.ecom.repository.ProductRespository;
import com.abc.ecom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRespository productRespository;

	@Override
	public List<Product> getAllProducts() {
		return productRespository.findAll();
	}

	@Override
	public Product getProductById(Long productId) {
		return productRespository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
	}
}
