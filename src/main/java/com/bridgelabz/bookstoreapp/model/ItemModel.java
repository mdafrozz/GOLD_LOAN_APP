package com.bridgelabz.bookstoreapp.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bridgelabz.bookstoreapp.dto.ItemDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "items")
public class ItemModel {

	@Id
	@GeneratedValue
	@Column(name = "itemId")
	private int itemId;
	private int customerId;
	private String itemType;
	private String carat;
	private int qty;
	private BigDecimal grossWeight;
	private BigDecimal netWeight;
	private int amountPerGm;
	private long totalAmt;
	
    public ItemModel(ItemDTO itemDTO) {
    	this.customerId = itemDTO.getCustomerId();
    	this.itemType = itemDTO.getItemType();
    	this.carat = itemDTO.getCarat();
    	this.qty = itemDTO.getQty();
    	this.grossWeight = itemDTO.getGrossWeight();
    	this.netWeight = itemDTO.getNetWeight();
    	this.amountPerGm = itemDTO.getAmountPerGm();
    	this.totalAmt = itemDTO.getTotalAmt();
    }
}
