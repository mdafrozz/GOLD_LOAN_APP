package com.bridgelabz.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.model.ItemModel;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Integer>{

	// Using custom query
		@Query(value = "SELECT * FROM items WHERE customer_id = :id", nativeQuery = true)
		List<ItemModel> findByCustomerId(int id);
}
