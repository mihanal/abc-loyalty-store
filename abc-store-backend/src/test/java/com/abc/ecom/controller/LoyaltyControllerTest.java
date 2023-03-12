package com.abc.ecom.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.abc.ecom.dto.LoyaltyRewardDTO;
import com.abc.ecom.mapper.LoyaltyMapper;
import com.abc.ecom.model.LoyaltyReward;
import com.abc.ecom.service.LoyaltyRewardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(LoyaltyController.class)
public class LoyaltyControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private LoyaltyRewardService loyaltyService;

	@MockBean
	private LoyaltyMapper loyaltyMapper;
	
	private static String BASE_API_PATH = "/api/v1";
	
	@Test
	public void testAddLoyaltyPoints() throws Exception {

		LoyaltyReward reward = new LoyaltyReward();
		reward.setActivated(true);
		reward.setAvailablePoints(150.00);
		
		LoyaltyRewardDTO rewardDto = new LoyaltyRewardDTO();
		rewardDto.setActivated(true);
		rewardDto.setAvailablePoints(150.00);
		
		when(loyaltyService.updateLoyaltyReward(Mockito.any(Long.class), Mockito.any(LoyaltyReward.class))).thenReturn(reward);
		when(loyaltyMapper.toLoyaltyRewardDTO(Mockito.any(LoyaltyReward.class))).thenReturn(rewardDto);

		mockMvc.perform(MockMvcRequestBuilders.put(BASE_API_PATH + "/loyalty/{customerId}/add", 100)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(reward)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.availablePoints", is(reward.getAvailablePoints())));
	}
	
	@Test
	public void testRedeemLoyaltyPoints() throws Exception {

		LoyaltyReward reward = new LoyaltyReward();
		reward.setActivated(true);
		reward.setAvailablePoints(150.00);
		
		LoyaltyRewardDTO rewardDto = new LoyaltyRewardDTO();
		rewardDto.setActivated(true);
		rewardDto.setAvailablePoints(150.00);
		
		when(loyaltyService.updateLoyaltyReward(Mockito.any(Long.class), Mockito.any(LoyaltyReward.class))).thenReturn(reward);
		when(loyaltyMapper.toLoyaltyRewardDTO(Mockito.any(LoyaltyReward.class))).thenReturn(rewardDto);

		mockMvc.perform(MockMvcRequestBuilders.put(BASE_API_PATH + "/loyalty/{customerId}/redeem", 100)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(reward)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.availablePoints", is(reward.getAvailablePoints())));
	}
}
