package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
@Entity
@Table(name="offre")
public class Offre {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id_offre;
	String titre;
	String description;
	String detail;
	Integer salaire;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name="id_gouvernorat")
	public Gouvernorat gouvernorat;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name="id_niveau")
	public Niveau niveau;

	public Offre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Offre(long id_offre, String titre, String description,String detail,Integer salaire,Gouvernorat gouvernorat,Niveau niveau) {
		super();
		this.titre = titre;
		this.description = description;
		this.detail = detail;
		this.salaire=salaire;
		this.gouvernorat=gouvernorat;
		this.niveau=niveau;
	}

	public long getId_offre() {
		return id_offre;
	}

	public void setId_offre(long id_offre) {
		this.id_offre = id_offre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getSalaire() {
		return salaire;
	}

	public void setSalaire(Integer salaire) {
		this.salaire = salaire;
	}
	
	public Gouvernorat getGouvernorat() {
		return gouvernorat;
	}

	public void setGouvernorat(Gouvernorat gouvernorat) {
		this.gouvernorat = gouvernorat;
	}
	
	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	
}
