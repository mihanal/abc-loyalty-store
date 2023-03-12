package com.abc.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abc.ecom.dto.LoyaltyRewardDTO;
import com.abc.ecom.mapper.LoyaltyMapper;
import com.abc.ecom.model.LoyaltyReward;
import com.abc.ecom.service.LoyaltyRewardService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/loyalty")
public class LoyaltyController {

	@Autowired
	private LoyaltyRewardService loyaltyService;

	@Autowired
	private LoyaltyMapper loyaltyMapper;

	@PutMapping("/{customerId}/add")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LoyaltyRewardDTO> addLoyaltyPoints(@PathVariable("customerId") Long customerId,
			@RequestBody LoyaltyReward loyaltyReward) {
		LoyaltyReward updatedLoyaltyReward = loyaltyService.updateLoyaltyReward(customerId, loyaltyReward);
		return ResponseEntity.ok().body(loyaltyMapper.toLoyaltyRewardDTO(updatedLoyaltyReward));
	}

	@PutMapping("/{customerId}/redeem")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LoyaltyRewardDTO> redeemLoyaltyPoints(@PathVariable("customerId") Long customerId,
			@RequestBody LoyaltyReward loyaltyReward) {
		LoyaltyReward updatedLoyaltyReward = loyaltyService.updateLoyaltyReward(customerId, loyaltyReward);
		return ResponseEntity.ok().body(loyaltyMapper.toLoyaltyRewardDTO(updatedLoyaltyReward));
	}
}
