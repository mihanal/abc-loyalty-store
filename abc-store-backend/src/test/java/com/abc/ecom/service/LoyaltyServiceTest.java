package com.abc.ecom.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.ecom.model.LoyaltyReward;
import com.abc.ecom.repository.LoyaltyRewardRepository;
import com.abc.ecom.service.impl.LoyaltyRewardServiceImpl;

@RunWith(SpringRunner.class)
public class LoyaltyServiceTest {

    @Mock
    private LoyaltyRewardRepository loyaltyRewardRepository;

    @InjectMocks
    private LoyaltyRewardServiceImpl loyaltyRewardService;
    
    @Test
    public void whenSaveCustomer_shouldReturnCustomer() {
    	LoyaltyReward loyaltyReward = new LoyaltyReward();
    	loyaltyReward.setActivated(true);
    	loyaltyReward.setAvailablePoints(100.00);

        when(loyaltyRewardRepository.save(ArgumentMatchers.any(LoyaltyReward.class))).thenReturn(loyaltyReward);

        LoyaltyReward createdReward = loyaltyRewardService.createLoyaltyReward(loyaltyReward);
        		
        assertThat(createdReward.getAvailablePoints()).isSameAs(loyaltyReward.getAvailablePoints());
        verify(loyaltyRewardRepository).save(loyaltyReward);
    }
}
