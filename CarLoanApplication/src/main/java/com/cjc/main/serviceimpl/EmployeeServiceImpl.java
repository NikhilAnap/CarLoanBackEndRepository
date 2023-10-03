package com.cjc.main.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.main.model.Employee;
import com.cjc.main.model.EmployeeDocument;
import com.cjc.main.repository.EmployeeRepository;
import com.cjc.main.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	@Override
	public Employee addEmployee(String employeeJ, MultipartFile profImg, MultipartFile aadharDoc, MultipartFile panDoc,
			MultipartFile signDoc) {
		// TODO Auto-generated method stub
		ObjectMapper mapper=new ObjectMapper();
		try {
			Employee employee = mapper.readValue(employeeJ, Employee.class);
			EmployeeDocument employeeDocument=new EmployeeDocument();
			employeeDocument.setAadharCard(aadharDoc.getBytes());
			employeeDocument.setProfileImg(profImg.getBytes());
			employeeDocument.setPanCard(panDoc.getBytes());
			employeeDocument.setSing(signDoc.getBytes());

			return employeeRepository.save(employee);

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Iterable<Employee> viewAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

}
