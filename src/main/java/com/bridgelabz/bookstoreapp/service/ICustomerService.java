package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import com.bridgelabz.bookstoreapp.dto.CustomerDTO;
import com.bridgelabz.bookstoreapp.model.CustomerModel;
import com.bridgelabz.bookstoreapp.model.UserModel;

public interface ICustomerService {

	public int createCustomer(CustomerDTO customerDTO);
	public List<CustomerModel> getAllCustomersData();
	public CustomerModel getCustomerDataById(int id);
	public List<CustomerModel> searchbyName(String name);
}
