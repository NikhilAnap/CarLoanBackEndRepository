package com.cjc.main.serviceimpl;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.main.model.Customer;
import com.cjc.main.model.CustomerBankDetails;
import com.cjc.main.model.CustomerDocuments;
import com.cjc.main.model.CustomerLocalAddress;
import com.cjc.main.model.CustomerPermanentAddress;
import com.cjc.main.model.EnquiryDetails;
import com.cjc.main.repository.CustomerRepository;
import com.cjc.main.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Value("${spring.mail.username}")
	private String fromMail;

	@Autowired
	JavaMailSender sender;

	// Generating username
	public String generateUsername() {
		String lowercase = "abcdefghijklmnopqrstuvwxyz";
		String number = "0123456789";
		String combination = lowercase + number;
		int length = 8;
		char[] un = new char[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {

			un[i] = combination.charAt(random.nextInt(combination.length()));
		}

		String username = new String(un);

		return username;

	}

	// Generating password
	public String generatePassword() {
		String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowercase = "abcdefghijklmnopqrstuvwxyz";
		String number = "0123456789";
		String specialCharacters = ".@*";
		String combination = uppercase + lowercase + specialCharacters + number;
		int length = 8;
		char[] pass = new char[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {

			pass[i] = combination.charAt(random.nextInt(combination.length()));
		}

		String password = new String(pass);

		return password;

	}

	// Generating Application Number
	public int generateApplicationNo() {

		Random random = new Random();

		int applicationNo = 100000 + random.nextInt(900000);

		return applicationNo;

	}

	@Override
	public void saveCustomer(String customerProfile, String customerPaddr, String customerLaddr, String bankDetails, MultipartFile customerAadhar, MultipartFile customerPan,
			MultipartFile customerProfilePhoto, MultipartFile customerSignature, MultipartFile customerSalaryslip,
			MultipartFile customerDrivingLicense, MultipartFile customerBankStatement,
			MultipartFile customerCarQuotation, MultipartFile customerForm16, MultipartFile customerITR) {

		ObjectMapper mapper = new ObjectMapper();

		try {

			Customer customer = mapper.readValue(customerProfile, Customer.class);
			
			CustomerPermanentAddress customerPermanentaddr=mapper.readValue(customerPaddr, CustomerPermanentAddress.class);
			CustomerLocalAddress customerLocaladd = mapper.readValue(customerLaddr, CustomerLocalAddress.class);
			
			CustomerBankDetails customerbankdetaila = mapper.readValue(bankDetails, CustomerBankDetails.class);
			
			customer.setCustomerPermanentAddress(customerPermanentaddr);
			customer.setCustomerLocalAddress(customerLocaladd);
			customer.setCustomerBankDetails(customerbankdetaila);

			CustomerDocuments customerDocuments = new CustomerDocuments();

			customerDocuments.setCustomerAadhar(customerAadhar.getBytes());
			customerDocuments.setCustomerPan(customerPan.getBytes());
			customerDocuments.setCustomerProfilePhoto(customerProfilePhoto.getBytes());
			customerDocuments.setCustomerSignature(customerSignature.getBytes());
			customerDocuments.setCustomerSalaryslip(customerSalaryslip.getBytes());
			customerDocuments.setCustomerDrivingLicense(customerDrivingLicense.getBytes());
			customerDocuments.setCustomerBankStatement(customerBankStatement.getBytes());
			customerDocuments.setCustomerCarQuotation(customerCarQuotation.getBytes());
			customerDocuments.setCustomerForm16(customerForm16.getBytes());
			customerDocuments.setCustomerITR(customerITR.getBytes());
			
			customer.setCustomerDocuments(customerDocuments);

			String username = generateUsername();
			String password = generatePassword();
			int applicationNo = generateApplicationNo();

			customer.setCustomerUsername(username);
			customer.setCustomerPassword(password);
			customer.setApplicationNo(applicationNo);

			customerRepository.save(customer);

			SimpleMailMessage simpleMail = new SimpleMailMessage();
			simpleMail.setFrom(fromMail);
			simpleMail.setTo(customer.getCustomerEmail());
			simpleMail.setSubject("About Car Loan Application");
			simpleMail.setText("Username = " + customer.getCustomerUsername() + "\n" + "Password = "
					+ customer.getCustomerPassword());

			sender.send(simpleMail);

		} catch (JsonProcessingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@Override
	public Iterable<Customer> getAllCustomer() {

		return customerRepository.findAll();
	}

	@Override
	public Customer getSingleCustomer(int customerId) {

		Optional<Customer> customer = customerRepository.findById(customerId);

		if (customer.isPresent()) {

			return customer.get();

		}

		return null;
	}

	@Override
	public void deleteCustomer(int customerId) {

		customerRepository.deleteById(customerId);

	}

}
