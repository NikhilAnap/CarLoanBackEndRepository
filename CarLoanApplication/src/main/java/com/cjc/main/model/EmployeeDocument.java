package com.cjc.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class EmployeeDocument {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int docId;
	@Lob
	private byte[] profileImg;
	@Lob
	private byte[] aadharCard;
	@Lob
	private byte[] panCard;
	@Lob
	private byte[] sing;



}
