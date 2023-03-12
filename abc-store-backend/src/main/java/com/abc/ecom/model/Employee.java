package com.abc.ecom.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "abc_employee")
@PrimaryKeyJoinColumn(name = "employeeId")
@Getter
@Setter
public class Employee extends User {

	@Enumerated(EnumType.ORDINAL)
	private EmployeeType employeeType;
}
