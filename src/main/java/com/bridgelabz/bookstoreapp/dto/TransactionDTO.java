package com.bridgelabz.bookstoreapp.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

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
