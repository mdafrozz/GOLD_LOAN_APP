package com.bridgelabz.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.model.TransactionModel;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Integer>{

	// Using custom query
	@Query(value = "SELECT * FROM transaction WHERE application_id = :id", nativeQuery = true)
	List<TransactionModel> findByApplicationId(int id);

}
