package com.bridgelabz.bookstoreapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.ApplicationDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.ApplicationModel;
import com.bridgelabz.bookstoreapp.service.IApplicationService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/application")
public class ApplicationController {

	@Autowired IApplicationService iApplicationService;

	// Create new application
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> create(@RequestBody ApplicationDTO applicationDTO){
			int id = iApplicationService.createApplication(applicationDTO);
			ResponseDTO responseDTO = new ResponseDTO("Application Created Successfully..!!!", id);
			return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}

	// Get all data
	@GetMapping("/getall")
	public List<ApplicationModel> getAllApplicationDetails() {
		List<ApplicationModel> applicationList = iApplicationService.getAllApplicationData();
		return applicationList;
	}

	// Get User Data by ID
	@GetMapping("/getby/{id}")
	public ResponseEntity<ResponseDTO> getApplicationById(@PathVariable int id) {
		ApplicationModel applicationModel = iApplicationService.getApplicationDataById(id);
		ResponseDTO responseDTO = new ResponseDTO("Application Details with ID: " + id, applicationModel);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	// Get all data
	@GetMapping("/getActiveLoans")
	public List<ApplicationModel> getActiveLoans() {
		List<ApplicationModel> applicationList = iApplicationService.getActiveLoans();
		return applicationList;
	}
	
	// Get all data
	@GetMapping("/getOverDueLoans")
	public List<ApplicationModel> getAllOverDueLoans() {
		List<ApplicationModel> applicationList = iApplicationService.getAllOverDueLoans();
		return applicationList;
	}
	
	// Get all data
	@GetMapping("/getClosedLoans")
	public List<ApplicationModel> getAllClosedLoans() {
		List<ApplicationModel> applicationList = iApplicationService.getAllClosedLoans();
		return applicationList;
	}
	
	// Search by Book
	@GetMapping("/search/{name}")
	public ResponseEntity<ResponseDTO> SearchByName(@PathVariable String name) {
		List<ApplicationModel> applicationList = iApplicationService.searchbyName(name);
		ResponseDTO responseDTO = new ResponseDTO("Number of Customers: " + applicationList.size(), applicationList);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	// Update by Book ID
	@PostMapping("/closeLoan/{loanId}")
	public ResponseEntity<ResponseDTO> closeLoan(@RequestBody ApplicationDTO applicationDTO, @PathVariable int loanId){
		int applicationModel = iApplicationService.closeLoan(applicationDTO, loanId);
			ResponseDTO responseDTO = new ResponseDTO("", "Updated Successfully");
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
    @GetMapping("/generate/{loanId}")
    public ResponseEntity<byte[]> generatePdf( @PathVariable int loanId) {
        byte[] pdfContent = iApplicationService.generatePdfContent(loanId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename("ApplicationForm.pdf").build());
        return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
    }
    

}
