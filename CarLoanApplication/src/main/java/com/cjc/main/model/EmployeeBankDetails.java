package com.cjc.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeeBankDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bankDetailsId;
	private long employeeAccNo;
	private String employeeBankname;
	private String employeeBranchname;
	private long employeeIFSCNo;
	private long employeeBankCode;
	private long employeeCIFNo;


}
