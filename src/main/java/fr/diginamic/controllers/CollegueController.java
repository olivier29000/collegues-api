package fr.diginamic.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.entites.Collegue;
import fr.diginamic.services.CollegueService;

@RestController
public class CollegueController {

	@GetMapping("/collegues")
	public List<String> reqParam(@RequestParam String nom) {
		CollegueService collegueService = new CollegueService();
		List<String> reponse = new ArrayList<String>();

		for (Collegue collegue : collegueService.rechercherParNom(nom)) {
			reponse.add(collegue.getMatricule());
		}

		return reponse;

	}

}
