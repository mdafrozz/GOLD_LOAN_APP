package com.bridgelabz.bookstoreapp.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DateSearcherDTO {

	  private LocalDate startDate;
	  private LocalDate endDate;
}
