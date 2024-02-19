package com.bridgelabz.bookstoreapp.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionDTO {

	private int applicationId;
	private long transactionAmount;
	private Date transactionDate;
	private String paymentMode;
	private String remarks;
}
