package com.bridgelabz.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.model.ApplicationModel;
import com.bridgelabz.bookstoreapp.model.ItemModel;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel, Integer> {

	// Using custom query
	@Query(value = "SELECT * FROM application WHERE loan_status = :type", nativeQuery = true)
	List<ApplicationModel> findOverDueLoans(String type);
	
	// Using custom query
	@Query(value = "SELECT * FROM application WHERE loan_status = :type", nativeQuery = true)
	List<ApplicationModel> findClosedLoans(String type);
	
	// Using custom query
	@Query(value = "SELECT * FROM application WHERE loan_status = :type", nativeQuery = true)
	List<ApplicationModel> findActiveLoans(String type);
	
	// Using custom query
	@Query(value = "SELECT * FROM application WHERE cust_id = :id", nativeQuery = true)
	List<ApplicationModel> findByCustomerId(int id);
}
