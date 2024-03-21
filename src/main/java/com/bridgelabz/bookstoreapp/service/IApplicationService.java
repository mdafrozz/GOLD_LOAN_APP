package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import com.bridgelabz.bookstoreapp.dto.ApplicationDTO;
import com.bridgelabz.bookstoreapp.model.ApplicationModel;

public interface IApplicationService {

	public int createApplication(ApplicationDTO applicationDTO);
	public List<ApplicationModel> getAllApplicationData();
	public ApplicationModel getApplicationDataById(int id) ;
	public List<ApplicationModel> getAllOverDueLoans();
	public List<ApplicationModel> getAllClosedLoans();
	public List<ApplicationModel> getActiveLoans();
	public List<ApplicationModel> searchbyName(String name);
	public int closeLoan(ApplicationDTO applicationDTO, int loanId);
	public String replaceContractVariables(String html, int loanId);
	public byte[] generatePdfContent(int loanId);
}
