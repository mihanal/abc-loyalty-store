package com.abc.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.ecom.model.Product;

public interface ProductRespository extends JpaRepository<Product, Long> {

}
