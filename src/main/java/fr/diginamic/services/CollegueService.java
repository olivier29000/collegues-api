package fr.diginamic.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.entites.Collegue;
import fr.diginamic.exceptions.CollegueInvalideException;
import fr.diginamic.exceptions.CollegueNonTrouve;
import fr.diginamic.utils.CollegueValidatorUtils;
import fr.diginamic.utils.CreationDeColleguesUtils;

@Service
public class CollegueService {

	@Autowired
	CollegueValidatorUtils collegueValidatorUtils;
	@Autowired
	CreationDeColleguesUtils creationDeColleguesUtils;

	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {
		super();

	}

	@PostConstruct
	public void init() {
		this.data = creationDeColleguesUtils.creerDesCollegues();
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

	public Collegue ajouterUnCollegue(Collegue collegueAAjouter) {

		collegueValidatorUtils.validerAttributsCollegue(collegueAAjouter);

		collegueAAjouter.setMatricule(UUID.randomUUID().toString());
		this.data.put(collegueAAjouter.getMatricule(), collegueAAjouter);

		return collegueAAjouter;

	}

	public Collegue modifierEmail(String matricule, String email) {

		Collegue collegue = this.rechercherParMatricule(matricule);

		collegueValidatorUtils.validerEmail(email);

		collegue.setEmail(email);

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
