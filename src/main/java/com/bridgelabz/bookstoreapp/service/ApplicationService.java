package com.bridgelabz.bookstoreapp.service;

import java.util.ArrayList;
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
	@Autowired ICustomerService iCustomerService;
	
	// Create Application details
	@Override
	public int createApplication(ApplicationDTO applicationDTO) {
		Optional<CustomerModel> customerModel = customerRepository.findById(applicationDTO.getCustId());
		int applicationId = 0;
		if(customerModel.isPresent()) {
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
	
	// Get all User Details list
	@Override
	public List<ApplicationModel> getAllOverDueLoans() {
		List<ApplicationModel> applicationModel = applicationRepository.findOverDueLoans("overdue");
		if (applicationModel.isEmpty()) {
			throw new BookStoreException("No OverDue Loans found!!!!");
		} else
			return applicationModel;
	}
	
	// Get all User Details list
	@Override
	public List<ApplicationModel> getAllClosedLoans() {
		List<ApplicationModel> applicationModel = applicationRepository.findClosedLoans("closed");
		if (applicationModel.isEmpty()) {
			throw new BookStoreException("No Closed Loans found!!!!");
		} else
			return applicationModel;
	}
	
	// Get all User Details list
	@Override
	public List<ApplicationModel> getActiveLoans() {
		List<ApplicationModel> applicationModel = applicationRepository.findClosedLoans("active");
		if (applicationModel.isEmpty()) {
			throw new BookStoreException("No Active Loans found!!!!");
		} else
			return applicationModel;
	}
	
	// Get customer Data by Name	
	@Override
	public List<ApplicationModel> searchbyName(String name) {
		List<CustomerModel> customerModel = iCustomerService.searchbyName(name);
		List<ApplicationModel> applicationModel = new ArrayList<ApplicationModel>();
		System.out.println(customerModel);
		if (customerModel != null) {
			for(int i=0; i< customerModel.size(); i++) {
				List<ApplicationModel> applicationModel1  = applicationRepository.findByCustomerId(customerModel.get(i).getCustomerId());
				applicationModel.addAll(applicationModel1);
			}
			return applicationModel;

		} else
			throw new BookStoreException("Name: " + name + " is not available");
	}
	
	// Create Application details
	@Override
	public int closeLoan(ApplicationDTO applicationDTO, int loanId) {
		int applicationId = 0;
		ApplicationModel applicationModel = applicationRepository.findById(loanId).orElse(null);
		if(applicationModel != null) {
			applicationModel.setClosingDate(applicationDTO.getClosingDate());
			applicationModel.setLoanStatus("closed");
			applicationRepository.save(applicationModel);
			applicationId = applicationModel.getApplicationId();
		}
		else {
			applicationId = 0;
		}
		return applicationId;
	}
	
}
