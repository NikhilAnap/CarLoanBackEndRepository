package com.cjc.main.service;

import org.springframework.web.multipart.MultipartFile;

import com.cjc.main.model.Employee;

public interface EmployeeService {

//	Employee addEmployee(String employeeJ, MultipartFile profImg, MultipartFile aadharDoc, MultipartFile panDoc,
//			MultipartFile signDoc);

	Iterable<Employee> viewAllEmployee();

	Employee addEmployee(String employee, String permanentAddress, String localAddress, String bankDetails,
			MultipartFile profImg, MultipartFile aadharDoc, MultipartFile panDoc, MultipartFile signDoc);


}
