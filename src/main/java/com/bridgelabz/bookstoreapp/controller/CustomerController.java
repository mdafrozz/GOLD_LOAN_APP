package com.bridgelabz.bookstoreapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.CustomerDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.CustomerModel;
import com.bridgelabz.bookstoreapp.repository.CustomerRepository;
import com.bridgelabz.bookstoreapp.service.ICustomerService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	ICustomerService iCustomerService;
	
	@Autowired
	CustomerRepository customerRepository;
	
	// insert data and generate Token
	@PostMapping(value = { "/create" })
	public ResponseEntity<ResponseDTO> AddCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		Optional<CustomerModel> customerModel = customerRepository.findByCustomerMobile(customerDTO.getMobileNumber());
		if(customerModel.isPresent()) {
			ResponseDTO responseDTO = new ResponseDTO("Customer Already Exists",400);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		}else {
		int id = iCustomerService.createCustomer(customerDTO);
		ResponseDTO responseDTO = new ResponseDTO("Customer Created Successfully..!!!", id);
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
		}
	}

	// Get all data
	@GetMapping("/getall")
	public List<CustomerModel> getAllCustomerDetails() {
		List<CustomerModel> userList = iCustomerService.getAllCustomersData();
		return userList;
	}

	// Get User Data by ID
	@GetMapping("/getby/{id}")
	public ResponseEntity<ResponseDTO> getUserById(@PathVariable int id) {
		CustomerModel customerModel = iCustomerService.getCustomerDataById(id);
		ResponseDTO responseDTO = new ResponseDTO("User Details with ID: " + id, customerModel);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
	
	// Get User Data by ID
	@GetMapping("/check/{mobileNumber}")
	public ResponseEntity<ResponseDTO> getUserById(@PathVariable String mobileNumber) {
		Optional<CustomerModel> customerModel = customerRepository.findByCustomerMobile(mobileNumber);
		if(customerModel.isPresent()) {
			ResponseDTO responseDTO = new ResponseDTO("Customer Already Exists",400);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		}else {
			ResponseDTO responseDTO = new ResponseDTO("New Customer", 200);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		}
	}
	
	// Search by Book
	@GetMapping("/search/{name}")
	public ResponseEntity<ResponseDTO> SearchByName(@PathVariable String name) {
		List<CustomerModel> customerList = iCustomerService.searchbyName(name);
		ResponseDTO responseDTO = new ResponseDTO("Number of Customers: " + customerList.size(), customerList);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}
}
