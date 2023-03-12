package com.abc.ecom.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.abc.ecom.dto.CustomerDTO;
import com.abc.ecom.mapper.CustomerMapper;
import com.abc.ecom.model.Customer;
import com.abc.ecom.service.CustomerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerMapper customerMapper;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		return ResponseEntity.ok().body(customerService.getAllCustomers().stream()
				.map(customer -> customerMapper.toCustomerDTO(customer)).collect(Collectors.toList()));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CustomerDTO> addCustomer(@RequestBody Customer customer) {
		Customer createdCustomer = customerService.addCustomer(customer);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdCustomer.getUserId()).toUri();
		return ResponseEntity.created(uri).body(customerMapper.toCustomerDTO(createdCustomer));
	}

	@GetMapping("/{customerId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("customerId") Long customerId) {
		return ResponseEntity.ok().body(customerMapper.toCustomerDTO(customerService.getCustomerById(customerId)));
	}

	@GetMapping("/contact")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CustomerDTO> getCustomerByContactNumber(@RequestParam("contactNumber") String contactNumber) {
		return ResponseEntity.ok()
				.body(customerMapper.toCustomerDTO(customerService.getCustomerByContactNumber(contactNumber)));
	}
}
