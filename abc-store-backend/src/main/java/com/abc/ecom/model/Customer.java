package com.abc.ecom.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "abc_customer")
@PrimaryKeyJoinColumn(name = "customerId")
@Getter
@Setter
public class Customer extends User {
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loyaltyReward", referencedColumnName = "loyaltyId")
	private LoyaltyReward loyaltyReward;
}
