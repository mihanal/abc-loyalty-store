package com.abc.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.ecom.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Customer findByContactNumber(String contactNumber);
}
