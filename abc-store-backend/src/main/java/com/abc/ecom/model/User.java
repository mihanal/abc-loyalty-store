package com.abc.ecom.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "abc_user")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCE")
    @SequenceGenerator(name = "USER_SEQUENCE", sequenceName = "USER_SEQUENCE", allocationSize = 1)
	private Long userId;
	private String firstName;
	private String lastName;
	private Date birthDate;
	
	@Column(unique = true)
	private String contactNumber;
	
	@Enumerated(EnumType.ORDINAL)
	private GenderType gender;
}
