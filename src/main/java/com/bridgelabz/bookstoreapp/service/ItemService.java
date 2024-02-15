package com.bridgelabz.bookstoreapp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstoreapp.dto.ItemDTO;
import com.bridgelabz.bookstoreapp.exception.BookStoreException;
import com.bridgelabz.bookstoreapp.model.ItemModel;
import com.bridgelabz.bookstoreapp.model.UserModel;
import com.bridgelabz.bookstoreapp.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;
	
	// Add Item
	public int addItem(ItemDTO itemDTO) {
		ItemModel itemModel = new ItemModel(itemDTO);
		itemRepository.save(itemModel);
		return itemModel.getItemId();
	}
	
	// Get all Item Details list
	public List<ItemModel> getAllItemData() {
		List<ItemModel> itemModel = itemRepository.findAll();
		if (itemModel.isEmpty()) {
			throw new BookStoreException("No Item Added yet!!!!");
		} else
			return itemModel;
	}
	
	// Delete data by itemId
	public int deleteItem(int id) {
		ItemModel itemModel = itemRepository.findById(id).orElse(null);
		if (itemModel != null) {
			itemRepository.deleteById(id);
		} else
			throw new BookStoreException("Error: Cannot find Item ID " + id);
		return id;
	}
	
	// Get the User Details by Email Address
	public List<ItemModel> getItemDataByCustomerId(int id) {
		List<ItemModel> itemModel= itemRepository.findByCustomerId(id);
		if (itemModel.isEmpty()) {
			throw new BookStoreException("No Item Added yet!!!!");
		} else
			return itemModel;
	}
}
