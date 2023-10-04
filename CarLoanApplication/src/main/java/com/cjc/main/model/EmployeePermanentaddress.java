package com.cjc.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeePermanentaddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int permanentId;
	private String employeeState;
	private String employeeDistrict;
	private String employeeCity;
	private String employeeArea;
	private String employeeLandmark;
	private long employeePinCode;


}
