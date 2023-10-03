package com.cjc.main.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int employeeId;
	private String employeename;
	private String employeeDOB;
	private String employeeEmail;
	private Long employeeMobileNo;
	private String employeeGender;
	private String employeeRole;
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeDocument employeeDocument;
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeePermanentaddress permanentaddress;
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeLocalAddress localAddress;
	@OneToOne(cascade = CascadeType.ALL)
	private EmployeeBankDetails bankDetails;

}
