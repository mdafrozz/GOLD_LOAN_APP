package com.bridgelabz.bookstoreapp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstoreapp.dto.ItemDTO;
import com.bridgelabz.bookstoreapp.dto.TransactionDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transaction")
public class TransactionModel {
	@Id
	
	@GeneratedValue
	@Column(name = "itemId")
	private int transactionId;
	private int applicationId;
	private long transactionAmount;
	private Date transactionDate;
	private String paymentMode;
	private String remarks;
	
	   public TransactionModel(TransactionDTO transactionDTO) {
	    	this.applicationId = transactionDTO.getApplicationId();
	    	this.transactionAmount = transactionDTO.getTransactionAmount();
	    	this.transactionDate = transactionDTO.getTransactionDate();
	    	this.paymentMode = transactionDTO.getPaymentMode();
	    	this.remarks = transactionDTO.getRemarks();
	    }
}
