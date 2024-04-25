package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.demo.model.Candidature;
import com.example.demo.model.Gouvernorat;
import com.example.demo.repository.CandidatureRepository;
import com.example.demo.repository.GouvernoratRepository;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
@RestController
@RequestMapping("candidature")
@CrossOrigin(origins= {"http://localhost:4200"})
public class CandidatureController {
	@GetMapping
	String getBonjour(){
		return "Bonjour candidature";
	}

	private final CandidatureRepository candidatureRepository;
	private final GouvernoratRepository gouvernoratRepository;
	
	@Autowired
	public CandidatureController(CandidatureRepository candidatureRepository,GouvernoratRepository gouvernoratRepository) {
		this.candidatureRepository = candidatureRepository;
		this.gouvernoratRepository = gouvernoratRepository;
		}
	
	
	@PostMapping
	public ResponseEntity<?> createCandidature(
	    @RequestParam String prenom,
	    @RequestParam long id_offre,
	    @RequestParam String nom,
	    @RequestParam String mail,
	    @RequestParam Integer telephone,
	    @RequestParam String competence,
	    @RequestParam String gouvernoratName,
	    @RequestParam("fichier") MultipartFile fichier) {
	    
	    try {
	        byte[] fichierBytes = fichier.getBytes();

	        Gouvernorat gouvernorat = gouvernoratRepository.findByNom(gouvernoratName);
	        if (gouvernorat == null) {
	            throw new RuntimeException("Gouvernorat not found with name: " + gouvernoratName);
	        }
	        Candidature candidature = new Candidature();
	        candidature.setOffre(id_offre);
	        candidature.setPrenom(prenom);
	        candidature.setNom(nom);
	        candidature.setMail(mail);
	        candidature.setTelephone(telephone);
	        candidature.setCompetence(competence);
	        candidature.setGouvernorat(gouvernorat);
	        candidature.setNomficher(fichier.getOriginalFilename());
	        candidature.setFichiertype(fichier.getContentType());
	        candidature.setFichier(fichierBytes);
	        Candidature newCandidature = candidatureRepository.save(candidature);


	        return new ResponseEntity<>(newCandidature, HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Failed to create candidature", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/all")
	public ResponseEntity<List<Candidature>> getAllCandidatures() {
	        List<Candidature> candidatures = candidatureRepository.findAll();
	        return new ResponseEntity<>(candidatures, HttpStatus.OK);
	   
	}

	@GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        Candidature candidature = candidatureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidature not found with id: " + id));
        ByteArrayResource resource = new ByteArrayResource(candidature.getFichier());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(candidature.getFichiertype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + candidature.getNomficher() + "\"")
                .body(resource);
    }
}