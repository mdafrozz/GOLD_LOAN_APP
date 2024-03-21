package com.bridgelabz.bookstoreapp.service;

import java.io.IOException;
import java.util.List;

import com.bridgelabz.bookstoreapp.dto.CustomerDTO;
import com.bridgelabz.bookstoreapp.model.CustomerModel;

public interface ICustomerService {

	public int createCustomer(CustomerDTO customerDTO) throws IOException;
	public List<CustomerModel> getAllCustomersData();
	public CustomerModel getCustomerDataById(int id);
	public List<CustomerModel> searchbyName(String name);
}
