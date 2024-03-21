package com.bridgelabz.bookstoreapp.model;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bridgelabz.bookstoreapp.dto.ApplicationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "application", indexes = { @Index(name = "idx_loan_status", columnList = "loan_status") })
@EntityListeners(AuditingEntityListener.class)
public class ApplicationModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "application_id")
	private int applicationId;
	@Column(name = "loan_status")
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
    private long processingFees;
    private long stampDuty;
    private long disbursedAmount;
    @Lob
    private String transactionList;
    private long closingPrincipal;
    private Date closingDate;
    @Lob
   	@Column(length = 1000)
     byte[] openingPhoto;
    @Lob
   	@Column(length = 1000)
     byte[] closingPhoto;
    
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
    	this.transactionList = new Gson().toJson(applicationDTO.getTransactionList());
    	this.closingDate = applicationDTO.getClosingDate();
    	this.openingPhoto = Base64.getDecoder().decode(applicationDTO.getOpeningPhoto());
     }
    
    public List<ItemModel> getItemList() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			return objectMapper.readValue(itemList, new TypeReference<List<ItemModel>>() {});
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

}
