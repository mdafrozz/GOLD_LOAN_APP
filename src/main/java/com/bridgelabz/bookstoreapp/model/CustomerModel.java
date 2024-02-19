package com.bridgelabz.bookstoreapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bridgelabz.bookstoreapp.dto.CustomerDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class CustomerModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "customerId")
	private int customerId;
    @CreatedDate
    Date dateCreated;
    @LastModifiedDate
    Date lastUpdated;
    String firstName;
    String lastName;
    Date DOB;
    String gender;
    String address;
    String father_husband_name;
    String maritalStatus;
    String nomineeName;
    String nomineeRelation;
    String nomineeGender;
    Date nomineeDob;
    String nomineeAddress;
    String nomineeMobile;
    String mobileNumber;
    String aadharNumber;
    String panNumber;
    @Lob
	@Column(length = 1000)
    byte[] photo;


    public CustomerModel(CustomerDTO customerDTO) {
    	this.firstName = customerDTO.getFirstName();
    	this.lastName = customerDTO.getLastName();
    	this.DOB = customerDTO.getDOB();
    	this.gender = customerDTO.getGender();
    	this.address = customerDTO.getAddress();
    	this.father_husband_name = customerDTO.getFather_husband_name();
    	this.maritalStatus = customerDTO.getMaritalStatus();
    	this.nomineeName = customerDTO.getNomineeName();
    	this.nomineeRelation = customerDTO.getNomineeRelation();
    	this.nomineeGender = customerDTO.getNomineeGender();
    	this.nomineeDob = customerDTO.getNomineeDob();
    	this.nomineeAddress = customerDTO.getNomineeAddress();
    	this.nomineeMobile = customerDTO.getNomineeMobile();
    	this.mobileNumber = customerDTO.getMobileNumber();
    	this.aadharNumber = customerDTO.getAadharNumber();
    	this.panNumber = customerDTO.getPanNumber();
    	this.photo = customerDTO.getPhoto();
    }
}
