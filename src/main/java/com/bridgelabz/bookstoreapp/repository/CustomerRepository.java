package com.bridgelabz.bookstoreapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.model.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Integer>{
	// Using custom query
	@Query(value = "SELECT * FROM customer WHERE mobile_number = :mobileNumber", nativeQuery = true)
	Optional<CustomerModel> findByCustomerMobile(String mobileNumber);

	@Query(value = "SELECT * FROM customer WHERE first_name LIKE %:name% OR last_name LIKE %:name%", nativeQuery = true)
	List<CustomerModel> findByName(String name);
}
