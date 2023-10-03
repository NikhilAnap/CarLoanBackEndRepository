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
	private String employeePState;
	private String employeePDistrict;
	private String employeePCity;
	private String employeePArea;
	private String employeePLandmark;
	private long employeePinCode;


}
