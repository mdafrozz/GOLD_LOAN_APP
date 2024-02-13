package com.bridgelabz.bookstoreapp.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDTO {

	private int customerId;
	private String itemType;
	private String carat;
	private int qty;
	private BigDecimal grossWeight;
	private BigDecimal netWeight;
	private int amountPerGm;
}
