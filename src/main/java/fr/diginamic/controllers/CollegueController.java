package fr.diginamic.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entites.Collegue;
import fr.diginamic.services.CollegueService;

@RestController
@RequestMapping(path = "/collegues")
public class CollegueController {
	CollegueService collegueService = new CollegueService();

	@RequestMapping(method = RequestMethod.GET)
	public List<String> reqParam(@RequestParam String nom) {

		List<String> reponse = new ArrayList<String>();

		for (Collegue collegue : collegueService.rechercherParNom(nom)) {
			reponse.add(collegue.getMatricule());
		}

		return reponse;
	}

	@RequestMapping(path = "/{matricule}", method = RequestMethod.GET)
	public Collegue collegueEnFonctionMatricule(@PathVariable String matricule) {

		return collegueService.rechercherParMatricule(matricule);
	}

	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<String> error() {
		return ResponseEntity.status(404).body("erreur serveur, Collegue non trouvé");
	}

}
