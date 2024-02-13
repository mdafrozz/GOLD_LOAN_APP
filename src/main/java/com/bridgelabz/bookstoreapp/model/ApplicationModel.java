package com.bridgelabz.bookstoreapp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bridgelabz.bookstoreapp.dto.ApplicationDTO;
import com.google.gson.Gson;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "application")
@EntityListeners(AuditingEntityListener.class)
public class ApplicationModel {

	@Id
	@GeneratedValue
	@Column(name = "applicationId")
	private int applicationId;
	private String loanStatus;
    @CreatedDate
    private Date dateCreated;
    @LastModifiedDate
    private Date lastUpdated;
    @ManyToOne
    @JoinColumn(name="cust_id")
    private CustomerModel customer;
    private long loanAmount;
    private int tenure;
    private BigDecimal interestRate;
    private Date startDate;
    private Date endDate;
    @Lob
    private String itemList;
    private String paymentMode;
    private String remarks;
    private String processingFees;
    private String stampDuty;
    private String disbursedAmount;
    
    public ApplicationModel(ApplicationDTO applicationDTO) {
    	this.loanAmount = applicationDTO.getLoanAmount();
    	this.tenure = applicationDTO.getTenure();	
    	this.interestRate = applicationDTO.getInterestRate();
    	this.startDate = applicationDTO.getStartDate();
    	this.endDate = applicationDTO.getEndDate();
    	this.itemList = new Gson().toJson(applicationDTO.getItemList());
    	this.paymentMode = applicationDTO.getPaymentMode();
    	this.remarks = applicationDTO.getRemarks();
    	this.processingFees = applicationDTO.getProcessingFees();
    	this.stampDuty = applicationDTO.getStampDuty();
    	this.disbursedAmount = applicationDTO.getDisbursedAmount();
     }
    
}
