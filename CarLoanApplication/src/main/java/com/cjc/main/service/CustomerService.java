package com.cjc.main.service;

import org.springframework.web.multipart.MultipartFile;

import com.cjc.main.model.Customer;

public interface CustomerService {

	public void saveCustomer(String customerDetails, MultipartFile customerAadhar, MultipartFile customerPan,
			MultipartFile customerProfilePhoto, MultipartFile customerSignature, MultipartFile customerSalaryslip,
			MultipartFile customerDrivingLicense, MultipartFile customerBankStatement,
			MultipartFile customerCarQuotation, MultipartFile customerForm16, MultipartFile customerITR);

	public Iterable<Customer> getAllCustomer();

	public Customer getSingleCustomer(int customerId);

	public void deleteCustomer(int customerId);

}
