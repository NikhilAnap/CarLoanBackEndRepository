package com.cjc.main.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.main.model.CustomerCibilDetails;
import com.cjc.main.model.EnquiryDetails;
import com.cjc.main.service.EnquiryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/enquiry")
public class EnquiryController {

	@Autowired
	EnquiryService enquiryService;

	@PostMapping("/save_enquiry")
	public ResponseEntity<EnquiryDetails> saveEnquiry(@RequestBody EnquiryDetails enquiryDetails) {

		EnquiryDetails enquiry = enquiryService.saveEnquiry(enquiryDetails);

		return new ResponseEntity<EnquiryDetails>(enquiry, HttpStatus.CREATED);

	}

	@GetMapping("/getAll_enquiry")
	public ResponseEntity<Iterable<EnquiryDetails>> getAllEnquiry() {

		Iterable<EnquiryDetails> enquiries = enquiryService.getAllEnquiry();

		return new ResponseEntity<Iterable<EnquiryDetails>>(enquiries, HttpStatus.OK);

	}

	@GetMapping("/getSingle_enquiry/{enquiryId}")
	public ResponseEntity<EnquiryDetails> getSingleEnquiry(@PathVariable int enquiryId) {

		EnquiryDetails enquiry = enquiryService.getSingleEnquiry(enquiryId);

		return new ResponseEntity<EnquiryDetails>(enquiry, HttpStatus.OK);

	}

	@PutMapping("/update_enquiry")
	public ResponseEntity<EnquiryDetails> updateEnquiry(@RequestBody EnquiryDetails enquiryDetails) {

		EnquiryDetails enquiry = enquiryService.saveEnquiry(enquiryDetails);

		return new ResponseEntity<EnquiryDetails>(enquiry, HttpStatus.OK);

	}

	@DeleteMapping("/delete_enquiry/{enquiryId}")
	public ResponseEntity<String> deleteEnquiry(@PathVariable int enquiryId) {

		enquiryService.deleteEnquiry(enquiryId);

		return new ResponseEntity<String>("Enquiry Deleted!!!", HttpStatus.OK);

	}

	@PutMapping("/check_cibil")
	public ResponseEntity<String> checkCibilScore(@RequestBody EnquiryDetails enquiryDetails) {

		int min = 600;
		int max = 900;

		int cibilScore = (int) (Math.random() * (max - min + 1) + min);

		CustomerCibilDetails customerCibilDetails = new CustomerCibilDetails();

		if (cibilScore < 700) {

			customerCibilDetails.setCibilScore(cibilScore);
			customerCibilDetails.setCibilStatus("CIBIL_BAD");

			enquiryDetails.setCustomerCibilDetails(customerCibilDetails);

			enquiryService.saveEnquiry(enquiryDetails);

			return new ResponseEntity<String>("CIBIL_BAD", HttpStatus.OK);

		} else if (cibilScore > 699 && cibilScore < 750) {

			customerCibilDetails.setCibilScore(cibilScore);
			customerCibilDetails.setCibilStatus("CIBIL_GOOD");

			enquiryDetails.setCustomerCibilDetails(customerCibilDetails);

			enquiryService.saveEnquiry(enquiryDetails);

			return new ResponseEntity<String>("CIBIL_GOOD", HttpStatus.OK);

		} else if (cibilScore > 749 && cibilScore < 800) {

			customerCibilDetails.setCibilScore(cibilScore);
			customerCibilDetails.setCibilStatus("CIBIL_BEST");

			enquiryDetails.setCustomerCibilDetails(customerCibilDetails);

			enquiryService.saveEnquiry(enquiryDetails);

			return new ResponseEntity<String>("CIBIL_BEST", HttpStatus.OK);

		} else {

			customerCibilDetails.setCibilScore(cibilScore);
			customerCibilDetails.setCibilStatus("CIBIL_EXCELLENT");

			enquiryDetails.setCustomerCibilDetails(customerCibilDetails);

			enquiryService.saveEnquiry(enquiryDetails);

			return new ResponseEntity<String>("CIBIL_EXCELLENT", HttpStatus.OK);

		}

	}
}
