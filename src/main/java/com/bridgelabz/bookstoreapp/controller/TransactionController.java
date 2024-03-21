package com.bridgelabz.bookstoreapp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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

import com.bridgelabz.bookstoreapp.dto.DateSearcherDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.dto.TransactionDTO;
import com.bridgelabz.bookstoreapp.model.TransactionModel;
import com.bridgelabz.bookstoreapp.repository.TransactionRepository;
import com.bridgelabz.bookstoreapp.service.TransactionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired TransactionService transactionService;
	@Autowired TransactionRepository transactionRepository;
	
	// Create new application
	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createVoucher(@RequestBody TransactionDTO transactionDTO){
			int id = transactionService.createVoucher(transactionDTO);
			ResponseDTO responseDTO = new ResponseDTO("Voucher Created Successfully..!!!", id);
			return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}

	// Get all item data
	@GetMapping("/getall")
	public List<TransactionModel> getAllTransactionDetails() {
		List<TransactionModel> voucherList = transactionService.getAllTransactionData();
		return voucherList;
	}

	// Get User Data by Email Address
	@GetMapping("/getbyAppId/{id}")
	public List<TransactionModel> getTransactionsByApplicationId(@PathVariable int id) {
		List<TransactionModel> voucherList= transactionService.getTransactionDataByApplicationId(id);
		return voucherList;
	}
	
	@PostMapping("/find")
    public List<TransactionModel> findPaymentByDate(@RequestBody DateSearcherDTO searcherDto) {
		List<TransactionModel> transactionList = null;
		if(searcherDto.getEndDate() == null) {
	        LocalDateTime start = LocalDateTime.of(LocalDate.from(searcherDto.getStartDate()), LocalTime.of(0, 0, 0));
	        LocalDateTime end = LocalDateTime.of(LocalDate.now() , LocalTime.of(23, 59, 59));
	         transactionList = transactionRepository.getAllBetweenDates(start, end);
		}else {
	        LocalDateTime start = LocalDateTime.of(LocalDate.from(searcherDto.getStartDate()), LocalTime.of(0, 0, 0));
	        LocalDateTime end = LocalDateTime.of(LocalDate.from(searcherDto.getEndDate()), LocalTime.of(23, 59, 59));
	         transactionList = transactionRepository.getAllBetweenDates(start, end);
		}
        return transactionList;
	}
}
