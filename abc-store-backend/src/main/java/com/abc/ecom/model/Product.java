package com.abc.ecom.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "abc_product")
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	private Double price;
	private String name;
	private String description;
	private Double loyaltyPoints;
	
}
