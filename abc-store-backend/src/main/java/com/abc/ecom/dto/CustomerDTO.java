package com.abc.ecom.dto;

import java.util.Date;

import com.abc.ecom.model.GenderType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

	private Long customerId;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String contactNumber;
	private GenderType gender;
	private LoyaltyRewardDTO loyaltyReward;
}
