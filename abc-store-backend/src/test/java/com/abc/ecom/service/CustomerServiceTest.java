package com.abc.ecom.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.ecom.model.Customer;
import com.abc.ecom.repository.CustomerRepository;
import com.abc.ecom.service.impl.CustomerServiceImpl;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;
    
    @Test
    public void shouldReturnAllCustomers() {
        List<Customer> users = new ArrayList<>();
        users.add(new Customer());

        given(customerRepository.findAll()).willReturn(users);

        List<Customer> expected = customerService.getAllCustomers();

        assertEquals(expected, users);
        verify(customerRepository).findAll();
    }
    
    @Test
    public void whenSaveCustomer_shouldReturnCustomer() {
    	Customer customer = new Customer();
    	customer.setFirstName("Amal");

        when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(customer);

        Customer createdCustomer = customerService.addCustomer(customer);

        assertThat(createdCustomer.getFirstName()).isSameAs(customer.getFirstName());
        verify(customerRepository).save(customer);
    }
    
    @Test
    public void whenGivenId_shouldReturnCustomer_ifFound() {
    	Customer customer = new Customer();
    	customer.setUserId(100L);

        when(customerRepository.findById(customer.getUserId())).thenReturn(Optional.of(customer));

        Customer expected = customerService.getCustomerById(customer.getUserId());

        assertThat(expected).isSameAs(customer);
        verify(customerRepository).findById(customer.getUserId());
    }
}
