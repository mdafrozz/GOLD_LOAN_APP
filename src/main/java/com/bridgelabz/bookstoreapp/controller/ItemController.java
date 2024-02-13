package com.bridgelabz.bookstoreapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstoreapp.dto.ItemDTO;
import com.bridgelabz.bookstoreapp.dto.ResponseDTO;
import com.bridgelabz.bookstoreapp.model.ItemModel;
import com.bridgelabz.bookstoreapp.service.ItemService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	// insert data and generate Token
	@PostMapping(value = { "/add" })
	public ResponseEntity<ResponseDTO> AddCustomer(@Valid @RequestBody ItemDTO itemDTO) {
		int id = itemService.addItem(itemDTO);
		ResponseDTO responseDTO = new ResponseDTO("Item Added Successfully..!!!", id);
		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
	}

	// Get all item data
	@GetMapping("/getall")
	public List<ItemModel> getAllItemDetails() {
		List<ItemModel> itemList = itemService.getAllItemData();
		return itemList;
	}
	
	// Delete item by itemID
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> deleteItemDataByID(@PathVariable int id) {
		int response = itemService.deleteItem(id);
		ResponseDTO respDTO = new ResponseDTO("Deleted Successfully Id:", response);
		return new ResponseEntity<>(respDTO, HttpStatus.OK);
	}
	
	// Get User Data by Email Address
	@GetMapping("/getbycustId/{id}")
	public List<ItemModel> getItemsByCustomerId(@PathVariable int id) {
		List<ItemModel> itemList= itemService.getItemDataByCustomerId(id);
		return itemList;	}
}
