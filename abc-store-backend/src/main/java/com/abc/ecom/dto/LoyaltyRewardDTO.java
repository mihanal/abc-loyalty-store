package com.abc.ecom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoyaltyRewardDTO {

	private Long loyaltyId;
	private boolean activated;
	private Double availablePoints;
	private Double redeemablePoints;
}
