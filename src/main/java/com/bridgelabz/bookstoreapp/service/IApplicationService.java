package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import com.bridgelabz.bookstoreapp.dto.ApplicationDTO;
import com.bridgelabz.bookstoreapp.dto.TransactionDTO;
import com.bridgelabz.bookstoreapp.model.ApplicationModel;

public interface IApplicationService {

	public int createApplication(ApplicationDTO applicationDTO);
	public List<ApplicationModel> getAllApplicationData();
	public ApplicationModel getApplicationDataById(int id) ;

}
