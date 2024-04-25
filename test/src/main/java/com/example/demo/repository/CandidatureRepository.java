package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Candidature;
import com.example.demo.model.Gouvernorat;
@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long>{
	
}