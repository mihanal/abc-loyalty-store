package com.abc.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.ecom.exception.ResourceNotFoundException;
import com.abc.ecom.model.Customer;
import com.abc.ecom.repository.CustomerRepository;
import com.abc.ecom.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long userId) {
		return customerRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Not Found"));
	}

	@Override
	public Customer getCustomerByContactNumber(String contactNumber) {
		return customerRepository.findByContactNumber(contactNumber);
	}
	
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long userId) {
		customerRepository.deleteById(userId);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
}
