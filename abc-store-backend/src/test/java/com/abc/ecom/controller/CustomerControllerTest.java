package com.abc.ecom.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.abc.ecom.dto.CustomerDTO;
import com.abc.ecom.mapper.CustomerMapper;
import com.abc.ecom.model.Customer;
import com.abc.ecom.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;

	@MockBean
	private CustomerMapper customerMapper;
	
	private static String BASE_API_PATH = "/api/v1";

	@Test
	public void testGetCustomers() throws Exception {

		Customer customerOne = new Customer();
		customerOne.setFirstName("Amal");

		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Amal");

		List<Customer> customers = Arrays.asList(customerOne);

		given(customerService.getAllCustomers()).willReturn(customers);
		when(customerMapper.toCustomerDTO(Mockito.any(Customer.class))).thenReturn(customer);

		mockMvc.perform(MockMvcRequestBuilders.get(BASE_API_PATH + "/customer")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void testSaveCustomer() throws Exception {

		Customer customerOne = new Customer();
		customerOne.setUserId(100L);
		customerOne.setFirstName("Amal");

		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(100L);
		customer.setFirstName("Amal");

		when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(customerOne);
		when(customerMapper.toCustomerDTO(Mockito.any(Customer.class))).thenReturn(customer);

		mockMvc.perform(MockMvcRequestBuilders.post(BASE_API_PATH + "/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(customerOne)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.firstName", is(customerOne.getFirstName())));
	}

	@Test
	public void testGetCustomerById() throws Exception {

		Customer customerOne = new Customer();
		customerOne.setUserId(100L);
		customerOne.setFirstName("Amal");

		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerId(100L);
		customer.setFirstName("Amal");

		when(customerService.getCustomerById(Mockito.any(Long.class))).thenReturn(customerOne);
		when(customerMapper.toCustomerDTO(Mockito.any(Customer.class))).thenReturn(customer);

		mockMvc.perform(MockMvcRequestBuilders.get(BASE_API_PATH + "/customer/{userId}", 100)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(customerOne)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName", is(customerOne.getFirstName())));
	}
}
