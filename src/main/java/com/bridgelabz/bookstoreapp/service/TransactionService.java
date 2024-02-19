package com.bridgelabz.bookstoreapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.TransactionDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.ApplicationModel;
import com.bridgelabz.bookstoreapp.model.TransactionModel;
import com.bridgelabz.bookstoreapp.repository.ApplicationRepository;
import com.bridgelabz.bookstoreapp.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired TransactionRepository transactionRepository;
	@Autowired ApplicationRepository applicationRepository;

	// Add Item
	public int createVoucher(TransactionDTO transactionDTO) {
		ApplicationModel applicationModel = applicationRepository.findById(transactionDTO.getApplicationId()).orElse(null);
		int returnId = 0;
		if(applicationModel != null) {
			TransactionModel transactionModel = new TransactionModel(transactionDTO);
			transactionRepository.save(transactionModel);
			returnId =  transactionModel.getTransactionId();
		}
		return returnId;

	}

	// Get all Item Details list
	public List<TransactionModel> getAllTransactionData() {
		List<TransactionModel> transactionModel = transactionRepository.findAll();
		if (transactionModel.isEmpty()) {
			throw new BookStoreException("No voucher Added yet!!!!");
		} else
			return transactionModel;
	}

	// Get the User Details by Email Address
	public List<TransactionModel> getTransactionDataByApplicationId(int id) {
		List<TransactionModel> transactionModel= transactionRepository.findByApplicationId(id);
		if (transactionModel.isEmpty()) {
			throw new BookStoreException("No transaction Added yet!!!!");
		} else
			return transactionModel;
	}
}
