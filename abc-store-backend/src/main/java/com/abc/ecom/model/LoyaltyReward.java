package com.abc.ecom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "abc_loyaltyreward")
@Getter
@Setter
public class LoyaltyReward {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loyaltyId;
	private boolean activated;
	private Double availablePoints;
	private Double redeemablePoints;

}