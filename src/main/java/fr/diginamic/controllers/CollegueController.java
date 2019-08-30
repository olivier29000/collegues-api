package fr.diginamic.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entites.Collegue;
import fr.diginamic.entites.Corps;
import fr.diginamic.exceptions.CollegueInvalideException;
import fr.diginamic.exceptions.CollegueNonTrouve;
import fr.diginamic.services.CollegueService;

@RestController
@RequestMapping(path = "/collegues")
public class CollegueController {

	@Autowired
	CollegueService collegueService;
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CollegueController.class);

	@RequestMapping(method = RequestMethod.GET)
	public List<Collegue> reqParam() {

		return collegueService.afficherCollegues();
	}

	@RequestMapping(method = RequestMethod.GET, params = { "nom" })
	public List<Collegue> reqParam(@RequestParam String nom) {

		return collegueService.afficherParNom(nom);

	}

	@RequestMapping(path = "/{matricule}", method = RequestMethod.GET)
	public Collegue collegueEnFonctionMatricule(@PathVariable String matricule) {
		return collegueService.afficherParMatricule(matricule);
	}

	@PostMapping
	public Collegue reqBody(@RequestBody Corps corps) throws CollegueInvalideException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String matricule = UUID.randomUUID().toString();
		collegueService.insererCollegue(new Collegue(matricule, corps.getNom(), corps.getPrenoms(), "",
				LocalDate.parse(corps.getDateDeNaissance(), formatter), corps.getPhotoUrl()));
		return collegueService.afficherParMatricule(matricule);

		// tbjbjkl
	}

	@PostMapping(path = "/{matricule}")
	public Collegue reqBodyModifPhotoUrl(@RequestBody Corps corps, @PathVariable String matricule)
			throws CollegueInvalideException {

		if (corps.getPhotoUrl() != null) {
			collegueService.modifierPhotoUrl(matricule, corps.getPhotoUrl());
		}
		if (corps.getEmail() != null) {
			collegueService.modifierEmail(matricule, corps.getEmail());
		}
		return collegueService.afficherParMatricule(matricule);

		// tbjbjkl
	}

	@ExceptionHandler({ CollegueNonTrouve.class })
	public ResponseEntity<String> errorCollegueNonTrouve(CollegueNonTrouve ex) {
		return ResponseEntity.status(400).body("Erreur de saisi du matricule : " + ex.getMessage());
	}

	@ExceptionHandler({ CollegueInvalideException.class })
	public ResponseEntity<String> errorCollegueInvalideException(CollegueInvalideException ex) {
		return ResponseEntity.status(400).body("Erreur de validation : " + ex.getMessage());
	}

	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<String> error(Throwable ex) {

		logger.info("erreur : ", ex);
		return ResponseEntity.status(404).body("erreur serveur, oops");
	}

}
