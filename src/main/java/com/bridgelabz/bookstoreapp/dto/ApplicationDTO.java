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
public class ApplicationDTO {

	@NotNull(message = "Loan Amount cannot be Empty")
    private long loanAmount;
	@NotNull(message = "Tenure cannot be Empty")
    private int tenure;
	@NotNull(message = "Interest Rate cannot be Empty")
    private BigDecimal interestRate;
	@NotNull(message = "Start Date cannot be Empty")
    private Date startDate;
    private Date endDate;
	private String loanProduct;
	private int custId;
    private List<Map<String, String>> itemList;
    private String paymentMode;
    private String remarks;
    private long processingFees;
    private long stampDuty;
    private long disbursedAmount;
    private List<Map<String, String>> transactionList;
    private Date closingDate;
	private String openingPhoto;
	private String closingPhoto;
}
