package com.bridgelabz.bookstoreapp.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {

	@NotNull(message = "First Name cannot be Empty")
	private String firstName;
	@NotNull(message = "Last Name cannot be Empty")
	private String lastName;
	@NotNull(message = "DOB cannot be Empty")
	private Date DOB;
	@NotNull(message = "Gender cannot be Empty")
	private String gender;
	@NotNull(message = "Address cannot be Empty")
	private String address;
	@NotNull(message = "Father/Husband name cannot be Empty")
    private String father_husband_name;
	@NotNull(message = "Mobile Number cannot be Empty")
	private String mobileNumber;
	private String aadharNumber;
	private String panNumber;
	@NotNull(message = "Marital Status cannot be Empty")
	private String maritalStatus;
	private String nomineeName;
	private String nomineeRelation;
	private String nomineeGender;
	private Date nomineeDob;
	private String nomineeAddress;
	private String nomineeMobile;
	private byte[] photo;
}
