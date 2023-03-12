package com.abc.ecom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.abc.ecom.dto.LoyaltyRewardDTO;
import com.abc.ecom.model.LoyaltyReward;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface LoyaltyMapper {

	LoyaltyRewardDTO toLoyaltyRewardDTO(LoyaltyReward loyaltyReward);
}
