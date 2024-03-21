package com.bridgelabz.bookstoreapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.bridgelabz.bookstoreapp.dto.TransactionDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "transaction",  indexes = { @Index(name = "idx_application_id", columnList = "application_id") })
public class TransactionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transactionId")
	private int transactionId;
	@Column(name = "application_id")
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
