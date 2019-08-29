package fr.diginamic.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import fr.diginamic.entites.Collegue;
import fr.diginamic.exceptions.CollegueInvalideException;
import fr.diginamic.exceptions.CollegueNonTrouve;

@Service
public class CollegueService {

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {
		// TODO alimenter data avec des données fictives
		// Pour générer un matricule : `UUID.randomUUID().toString()`
		Collegue collegue1 = new Collegue(UUID.randomUUID().toString(), "lasbleis", "olivier", "lasb.oliv@yahoo.fr",
				LocalDate.now(), "http://ibgnews.com/wp-content/uploads/2016/07/Dev.jpg");
		Collegue collegue2 = new Collegue(UUID.randomUUID().toString(), "lasbleis", "kevin", "seguineau.kevin@yahoo.fr",
				LocalDate.now(), "https://www.afjv.com/2018/01/180104-developpeur-web.jpg");
		Collegue collegue3 = new Collegue(UUID.randomUUID().toString(), "hartouf", "bilel", "lasb.bilel@yahoo.fr",
				LocalDate.now(), "https://www.capcampus.com/img/u/1/developpeur-web.jpg");
		Collegue collegue4 = new Collegue(UUID.randomUUID().toString(), "turpin", "eloi", "eloi.oliv@yahoo.fr",
				LocalDate.now(),
				"https://edito.regionsjob.com/xjob/wp-content/uploads/sites/3/2016/02/code_developpeur.jpg");
		Collegue collegue5 = new Collegue(UUID.randomUUID().toString(), "breihzou", "guillaume",
				"breihzou.guillaume@yahoo.fr", LocalDate.now(),
				"https://start.lesechos.fr/images/2016/12/09/6715_1481539030_developpeur-full-stack_970x545p.jpg");

		this.data.put(collegue1.getMatricule(), collegue1);
		this.data.put(collegue2.getMatricule(), collegue2);
		this.data.put(collegue3.getMatricule(), collegue3);
		this.data.put(collegue4.getMatricule(), collegue4);
		this.data.put(collegue5.getMatricule(), collegue5);
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {

		List<Collegue> listeDeCollegues = new ArrayList<Collegue>();
		// TODO retourner une liste de collègues dont le nom est fourni
		for (String Key : this.data.keySet()) {
			this.data.get(Key); // Pour acceder aux clés de ma hashmap

			if (this.data.get(Key).getNom().equals(nomRecherche)) {
				listeDeCollegues.add(this.data.get(Key));
			}
		}
		return listeDeCollegues;

	}

	public Collegue rechercherParMatricule(String matriculeRecherche) {

		// TODO retourner le collègue dont le matricule est fourni

		// TODO retourner une exception `CollegueNonTrouveException` (à créer)
		// si le matricule ne correspond à aucun collègue
		Collegue reponse = null;
		// TODO retourner une liste de collègues dont le nom est fourni
		for (String Key : this.data.keySet()) {
			this.data.get(Key); // Pour acceder aux clés de ma hashmap

			if (this.data.get(Key).getMatricule().equals(matriculeRecherche)) {
				reponse = this.data.get(Key);
			}
		}
		if (reponse == null) {
			throw new CollegueNonTrouve("Aucun collegue ne correspond à ce matricule");
		}
		return reponse;
	}

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) throws CollegueInvalideException {

		if (collegueAAjouter.getNom().length() < 3) {
			throw new CollegueInvalideException("le nom doit avoir au moins 2 caractères");
		}
		if (collegueAAjouter.getPrenoms().length() < 3) {
			throw new CollegueInvalideException("le prenom doit avoir au moins 2 caractères");
		}
		if (!collegueAAjouter.getPhotoUrl().startsWith("http")) {
			throw new CollegueInvalideException("la photoUrl doit commencer par http");
		}
		if (collegueAAjouter.getEmail().length() > 4 && collegueAAjouter.getEmail().indexOf("@") > 0) {
			throw new CollegueInvalideException("l'email doit avoir au moins 3 caractères et contenir @");
		}
		if (Period.between(collegueAAjouter.getDateDeNaissance(), LocalDate.now()).getYears() < 18) {
			throw new CollegueInvalideException("l'age doit etre au minimum 18 ans");
		}

		collegueAAjouter.setMatricule(UUID.randomUUID().toString());
		this.data.put(collegueAAjouter.getMatricule(), collegueAAjouter);

		return collegueAAjouter;

	}

	public Collegue modifierEmail(String matricule, String email) {

		Collegue collegue = this.rechercherParMatricule(matricule);
		if (email.length() < 3 && email.indexOf("@") < 1) {
			throw new CollegueInvalideException("l'email doit avoir au moins 3 caractères et contenir @");
		} else {
			collegue.setEmail(email);
		}

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

		// TODO Vérifier que l'email a au moins 3 caractères et contient `@`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		// TODO Modifier le collègue

		return collegue;
	}

	public Collegue modifierPhotoUrl(String matricule, String photoUrl) {

		if (!photoUrl.startsWith("http")) {
			throw new CollegueInvalideException("la photoUrl doit commencer par http");
		} else {
			Collegue collegue = this.rechercherParMatricule(matricule);
			collegue.setPhotoUrl(photoUrl);
			return collegue;
		}

		// TODO retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue

		// TODO Vérifier que la photoUrl commence bien par `http`
		// TODO Si la règle ci-dessus n'est pas valide, générer une exception :
		// `CollegueInvalideException`. avec un message approprié.

		// TODO Modifier le collègue

	}

	public List<Collegue> listeCollegue() {

		List<Collegue> reponse = new ArrayList<Collegue>();
		// TODO retourner une liste de collègues dont le nom est fourni
		for (String Key : this.data.keySet()) {
			this.data.get(Key); // Pour acceder aux clés de ma hashmap

			reponse.add(this.data.get(Key));

		}
		if (reponse == null) {
			throw new CollegueNonTrouve("Aucun collegue n'est présent");
		}
		return reponse;

	}

}
