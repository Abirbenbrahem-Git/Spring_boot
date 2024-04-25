package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Offre;
import com.example.demo.repository.GouvernoratRepository;
import com.example.demo.repository.OffreRepository;
@Service
public class OffreService {
private final OffreRepository offreRepo;
	@Autowired
	public OffreService(OffreRepository offreRepo) {
	super();
	this.offreRepo = offreRepo;
	}
	
	public List<Offre> getOffres()
	{
	return offreRepo.findAll();
	}
	
	public List<Offre> getOffreByGouvernorat(String nom) {
	    return offreRepo.findByGouvernoratNom(nom);
	}

	public List<Offre> getOffreByNiveau(String nom) {
	    return offreRepo.findByNiveauNom(nom);
	}
	public Offre addOffre(Offre offre) {
		offre = offreRepo.save(offre);
	    return offre;
	}
	
	public Offre getOffreById(Long id_offre){
		Offre offre;
		offre = offreRepo.findById(id_offre).get();
		return offre;
	}
	
	public void deleteOffreByid(Long id_offre){
		offreRepo.deleteByid(id_offre);
	}
	
	 public Offre updateOffre(Offre offre) {
	        return offreRepo.save(offre);
	    }
}
