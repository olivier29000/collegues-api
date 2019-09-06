package fr.diginamic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.datajpa.UtilisateurRepository;
import fr.diginamic.entites.Collegue;
import fr.diginamic.entites.Utilisateur;

@RestController
@Secured({ "ROLE_USER", "ROLE_ADMIN" })
@RequestMapping(path = "/auth/user")
public class AuthController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Utilisateur collegueEnFonctionMatricule() {
		String nomUtilisateur = SecurityContextHolder.getContext().getAuthentication().getName();
		Utilisateur utilisateur = utilisateurRepository.findByNomUtilisateur(nomUtilisateur).get();
		Collegue collegue = utilisateur.getCollegue();
		return utilisateur;
	}

}
