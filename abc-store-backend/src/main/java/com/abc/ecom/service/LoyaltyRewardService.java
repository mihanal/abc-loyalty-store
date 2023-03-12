package com.abc.ecom.service;

import com.abc.ecom.model.LoyaltyReward;

public interface LoyaltyRewardService {

	/**
	 * Create loyalty reward
	 * @param loyaltyReward
	 * @return
	 */
	public LoyaltyReward createLoyaltyReward(LoyaltyReward loyaltyReward);
	
	/**
	 * Update loyalty reward
	 * @param customerId
	 * @param loyaltyReward
	 * @return
	 */
	public LoyaltyReward updateLoyaltyReward(Long customerId, LoyaltyReward loyaltyReward);
}
