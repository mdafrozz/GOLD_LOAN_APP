package com.bridgelabz.bookstoreapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.bookstoreapp.model.ApplicationModel;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationModel, Integer> {
	
}
