package com.bridgelabz.bookstoreapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.ApplicationDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.ApplicationModel;
import com.bridgelabz.bookstoreapp.model.CustomerModel;
import com.bridgelabz.bookstoreapp.repository.ApplicationRepository;
import com.bridgelabz.bookstoreapp.repository.CustomerRepository;

@Service
public class ApplicationService implements IApplicationService {

	@Autowired ApplicationRepository applicationRepository;
	@Autowired CustomerRepository customerRepository;
	
	// Create Application details
	@Override
	public int createApplication(ApplicationDTO applicationDTO) {
		Optional<CustomerModel> customerModel = customerRepository.findById(applicationDTO.getCustId());
		int applicationId = 0;
		if(customerModel.isPresent()) {
		    System.out.println("JSON String: " + applicationDTO.getItemList());
			ApplicationModel applicationModel = new ApplicationModel(applicationDTO);
			applicationModel.setCustomer(customerModel.get());
			applicationModel.setLoanStatus("active");
			applicationRepository.save(applicationModel);
			applicationId = applicationModel.getApplicationId();
		}
		else {
			applicationId = 0;
		}
		return applicationId;
	}
	
	// Get all User Details list
	@Override
	public List<ApplicationModel> getAllApplicationData() {
		List<ApplicationModel> applicationModel = applicationRepository.findAll();
		if (applicationModel.isEmpty()) {
			throw new BookStoreException("No Application Created yet!!!!");
		} else
			return applicationModel;
	}

	// Get the user data by id
	@Override
	public ApplicationModel getApplicationDataById(int id) {
		ApplicationModel applicationModel = applicationRepository.findById(id).orElse(null);
	    if (applicationModel != null) {
			return applicationModel;
		} else
			throw new BookStoreException("ApplicationId: " + id + ", does not exist");
	}
}
