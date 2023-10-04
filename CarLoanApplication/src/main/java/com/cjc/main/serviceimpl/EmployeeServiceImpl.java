package com.cjc.main.serviceimpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.main.model.Employee;
import com.cjc.main.model.EmployeeBankDetails;
import com.cjc.main.model.EmployeeDocument;
import com.cjc.main.model.EmployeeLocalAddress;
import com.cjc.main.model.EmployeePermanentaddress;
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
	public Employee addEmployee(String employee, String permanentAddress, String localAddress, String bankDetails,
			MultipartFile profImg, MultipartFile aadharDoc, MultipartFile panDoc, MultipartFile signDoc) {
		// TODO Auto-generated method stub
		ObjectMapper mapper=new ObjectMapper();
		try {
			Employee Employee = mapper.readValue(employee, Employee.class);
			EmployeePermanentaddress PAddr = mapper.readValue(employee, EmployeePermanentaddress.class);
			EmployeeLocalAddress lAddr = mapper.readValue(employee, EmployeeLocalAddress.class);
			EmployeeBankDetails bank = mapper.readValue(employee, EmployeeBankDetails.class);
			
			EmployeeDocument employeeDocument=new EmployeeDocument();
			employeeDocument.setAadharCard(aadharDoc.getBytes());
			employeeDocument.setProfileImg(profImg.getBytes());
			employeeDocument.setPanCard(panDoc.getBytes());
			employeeDocument.setSing(signDoc.getBytes());

			Employee.setPermanentaddress(PAddr);
			Employee.setLocalAddress(lAddr);
			Employee.setBankDetails(bank);
			Employee.setEmployeeDocument(employeeDocument);
			
			return employeeRepository.save(Employee);

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
