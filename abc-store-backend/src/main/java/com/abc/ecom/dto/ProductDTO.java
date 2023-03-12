package com.abc.ecom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

	private Long id;
	private Double price;
	private String name;
	private String description;
	private Double loyaltyPoints;
}
