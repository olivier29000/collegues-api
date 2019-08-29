package fr.diginamic.utils;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import fr.diginamic.entites.Collegue;
import fr.diginamic.exceptions.CollegueInvalideException;

@Component
public class CollegueValidatorUtils {

	public void validerAttributsCollegue(Collegue collegue) {

		validerNom(collegue.getNom());
		validerPrenom(collegue.getPrenoms());
		validerPhotoUrl(collegue.getPhotoUrl());
		validerEmail(collegue.getEmail());
		validerDateDeNaissance(collegue.getDateDeNaissance());

	}

	public void validerNom(String nom) {
		if (nom.length() < 3) {
			throw new CollegueInvalideException("le nom doit avoir au moins 2 caractères");
		}
	}

	public void validerPrenom(String prenom) {
		if (prenom.length() < 3) {
			throw new CollegueInvalideException("le prenom doit avoir au moins 2 caractères");
		}
	}

	public void validerPhotoUrl(String photoUrl) {
		if (!photoUrl.startsWith("http")) {
			throw new CollegueInvalideException("la photoUrl doit commencer par http");
		}
	}

	public void validerEmail(String email) {
		if (email.length() < 4 || email.indexOf("@") < 0) {
			throw new CollegueInvalideException("l'email doit avoir au moins 3 caractères et contenir @");
		}
	}

	public void validerDateDeNaissance(LocalDate localDate) {
		if (Period.between(localDate, LocalDate.now()).getYears() < 18) {
			throw new CollegueInvalideException("l'age doit etre au minimum 18 ans");
		}
	}

}
