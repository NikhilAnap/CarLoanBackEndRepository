package com.cjc.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.main.model.Employee;
import com.cjc.main.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployee(
			@RequestPart(name="employee",required = true) String Employee,
			@RequestPart(name="permanentAddr",required = true) String permanentAddress,
			@RequestPart(name="localAddr",required = true) String localAddress,
			@RequestPart(name="bankDetails",required = true) String bankDetails,
			
			
			
			@RequestPart(name="prof",required = false) MultipartFile profImg,
			@RequestPart(name="adhar") MultipartFile aadharDoc,
			@RequestPart(name="pan") MultipartFile panDoc,
			@RequestPart(name="sign") MultipartFile signDoc


			)
	{
		Employee empDb=employeeService.addEmployee(Employee,permanentAddress,localAddress,bankDetails,profImg,aadharDoc,panDoc,signDoc);
		return new ResponseEntity<Employee>(empDb, HttpStatus.CREATED);
	}
	@GetMapping("/viewAllEmployee")
	public ResponseEntity<Iterable<Employee>> viewAllEmployee()
	{
		Iterable<Employee>  empList=employeeService.viewAllEmployee();
		return new ResponseEntity<Iterable<Employee>>(empList, HttpStatus.OK);
		
	}


}
