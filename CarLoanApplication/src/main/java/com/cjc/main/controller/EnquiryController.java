package com.cjc.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.main.model.EnquiryDetails;
import com.cjc.main.service.EnquiryService;

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
}
