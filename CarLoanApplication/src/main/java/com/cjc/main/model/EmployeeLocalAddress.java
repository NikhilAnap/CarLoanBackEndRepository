package com.cjc.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmployeeLocalAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int localId;
	private String employeeLState;
	private String employeeLDistrict;
	private String employeeLCity;
	private String employeeLArea;
	private String employeeLLandmark;
	private long employeeLPinCode;


}
