package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Gouvernorat;
import com.example.demo.model.Niveau;
import com.example.demo.model.Offre;
import com.example.demo.service.GouvernoratService;
import com.example.demo.service.NiveauService;
import com.example.demo.service.OffreService;

@RestController
@RequestMapping("offre")
@CrossOrigin(origins= {"http://localhost:4200"})
public class OffreController {
	@GetMapping
	String getBonjour(){
		return "Bonjour offre";
	}

	private final OffreService offreService;
	private final GouvernoratService gouvernoratService;
	private final NiveauService niveauService;
	
	@Autowired
	public OffreController(OffreService offreService,GouvernoratService gouvernoratService,NiveauService niveauService) {
		this.offreService = offreService;
		this.gouvernoratService=gouvernoratService;
		this.niveauService=niveauService;
		}
	
	@GetMapping(value ="all" )
	public List<Offre> getOffres(){
	return offreService.getOffres();
	}
	
	@GetMapping(value ="gouvernorat" )
	public List<Gouvernorat> getGouvernorats(){
	return gouvernoratService.getGouvernorats();
	}
	
	@GetMapping(value ="gouvernorat/noms" )
	public List<String> getGouvernoratNames() {
	    List<Gouvernorat> gouvernorats = gouvernoratService.getGouvernorats();
	    List<String> gouvernoratNames = new ArrayList<>();
	    for (Gouvernorat gouvernorat : gouvernorats) {
	        gouvernoratNames.add(gouvernorat.getNom());
	    }
	    return gouvernoratNames;
	}
	
	@GetMapping(value="gouvernorat/{nom}")
	public List<Offre> getOffreByGouvernorat(@PathVariable String nom){
	    List<Offre> listOffre;
	    listOffre = offreService.getOffreByGouvernorat(nom);
	    return listOffre;
	}
	
	@GetMapping(value ="niveau" )
	public List<Niveau> getNiveaus(){
	return niveauService.getNiveaus();
	}
	
	@GetMapping(value ="niveau/noms" )
	public List<String> getNiveauNames() {
	    List<Niveau> niveaus = niveauService.getNiveaus();
	    List<String> niveauNames = new ArrayList<>();
	    for (Niveau niveau : niveaus) {
	    	niveauNames.add(niveau.getNom());
	    }
	    return niveauNames;
	}
	
	@GetMapping(value="niveau/{nom}")
	public List<Offre> getOffreByNiveau(@PathVariable String nom){
	    List<Offre> listOffre;
	    listOffre = offreService.getOffreByNiveau(nom);
	    return listOffre;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Offre addOffre(@RequestBody Offre offre){
		offre = offreService.addOffre(offre);
		return offre;
	}
	
	@GetMapping(value="{id_offre}")
	public Offre getOffreById(@PathVariable Long id_offre){
		Offre offre;
		offre = offreService.getOffreById(id_offre);
		return offre;
	}
	
	@DeleteMapping(value="/id/{id_offre}")
	public void deleteOffreByid(@PathVariable Long id_offre){
		offreService.deleteOffreByid(id_offre);
	}
	
	@PutMapping(value="{id_offre}")
	public void UpdateOffre(@RequestBody Offre offre1, @PathVariable Long id_offre){
	    Offre offre = offreService.getOffreById(id_offre);
	    if(offre != null && offre1 != null) {
	        offre.setTitre(offre1.getTitre());
	        offre.setDescription(offre1.getDescription());
	        offre.setDetail(offre1.getDetail());
	        offre.setSalaire(offre1.getSalaire());
	        offre.setGouvernorat(offre1.getGouvernorat());
	        offre.setNiveau(offre1.getNiveau());
	        offreService.updateOffre(offre);
	    }
	}


}
