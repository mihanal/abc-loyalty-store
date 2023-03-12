package com.abc.ecom.service;

import java.util.List;

import com.abc.ecom.model.Customer;

public interface CustomerService {

	/**
	 * Get all registered customers
	 * @return
	 */
	public List<Customer> getAllCustomers();
	
	/**
	 * Get customer by customer id
	 * @param userId Customer Id
	 * @return customer
	 */
	public Customer getCustomerById(Long userId);
	
	/**
	 * Get customer by contact number
	 * @param contactNumber contact number
	 * @return customer
	 */
	public Customer getCustomerByContactNumber(String contactNumber);
	
	/**
	 * Create customer
	 * @param customer customer 
	 * @return created customer
	 */
	public Customer addCustomer(Customer customer);
	
	/**
	 * Delete customer
	 * @param userId customer id
	 */
	public void deleteCustomer(Long userId);
	
	/**
	 * Update customer 
	 * @param customer customer
	 * @return customer
	 */
	public Customer updateCustomer(Customer customer);
}
