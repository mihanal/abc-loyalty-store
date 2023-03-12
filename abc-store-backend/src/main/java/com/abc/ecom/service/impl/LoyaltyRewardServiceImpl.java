package com.abc.ecom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.ecom.model.Customer;
import com.abc.ecom.model.LoyaltyReward;
import com.abc.ecom.repository.LoyaltyRewardRepository;
import com.abc.ecom.service.CustomerService;
import com.abc.ecom.service.LoyaltyRewardService;

@Service
public class LoyaltyRewardServiceImpl implements LoyaltyRewardService {

	@Autowired
	private LoyaltyRewardRepository loyaltyRepository;
	
	@Autowired
	private CustomerService customerService;

	@Override
	public LoyaltyReward createLoyaltyReward(LoyaltyReward loyaltyReward) {
		return loyaltyRepository.save(loyaltyReward);
	}

	@Override
	public LoyaltyReward updateLoyaltyReward(Long customerId, LoyaltyReward loyaltyReward) {
		Customer customer = customerService.getCustomerById(customerId);
		customer.setLoyaltyReward(loyaltyReward);
		
		Customer updatedCustomer = customerService.updateCustomer(customer);
		return updatedCustomer.getLoyaltyReward();
	}
}
