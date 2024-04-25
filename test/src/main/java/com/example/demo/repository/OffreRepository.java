package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Offre;
@Repository
public interface OffreRepository  extends JpaRepository<Offre, Long>  {
	List<Offre> findByGouvernoratNom(String nom);
	List<Offre> findByNiveauNom(String nom);
	@Transactional
	@Modifying
	@Query("delete from Offre o where o.id_offre = ?1")
	void deleteByid(Long id_offre);
}

