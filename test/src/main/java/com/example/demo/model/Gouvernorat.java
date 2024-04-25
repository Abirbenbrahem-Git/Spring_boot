package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="gouvernorat")
public class Gouvernorat {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id_gouvernorat;
	String nom;
	
	//@OneToMany(mappedBy="gouvernorat")
	//public List<Offre> offres;
	
	
	public Gouvernorat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Gouvernorat(long id_gouvernorat, String nom) {
		super();
		this.nom = nom;
	}
	

	public long getId_gouvernorat() {
		return id_gouvernorat;
	}

	public void setId_gouvernorat(long id_gouvernorat) {
		this.id_gouvernorat = id_gouvernorat;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
}
