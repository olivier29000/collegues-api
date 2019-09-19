package fr.diginamic.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.datajpa.CollegueRepository;
import fr.diginamic.entites.Collegue;
import fr.diginamic.utils.CollegueValidatorUtils;
import fr.diginamic.utils.CreationDeColleguesUtils;

@Service
@Component
public class CollegueService {

	@Autowired
	CollegueValidatorUtils collegueValidatorUtils;
	@Autowired
	CreationDeColleguesUtils creationDeColleguesUtils;
	@Autowired
	private CollegueRepository collegueRepository;

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(CollegueService.class);

	public List<Collegue> afficherCollegues() {

		List<Collegue> v = collegueRepository.findAll();
		logger.info(v.toString());
		return v;

	}

	public List<String> obtenirLaListeDesMatricules(String nom_collegue) {
		// TODO Auto-generated method stub

		List<Collegue> v = collegueRepository.findAll();
		List<String> listeDeMatricules = new ArrayList<String>();
		for (Collegue collegue : v) {
			if (collegue.getNom().equals(nom_collegue)) {
				listeDeMatricules.add(collegue.getMatricule());
			}

		}
		return listeDeMatricules;
	}

	@Transactional
	public void insererListeDeCollegues(List<Collegue> listeDeCollegues) {

		for (Collegue collegue : listeDeCollegues) {
			insererCollegue(collegue);
		}

	}

	@Transactional
	public void insererCollegue(Collegue collegue) {

		collegueRepository.save(collegue);

	}

	public List<Collegue> afficherParNom(String nom) {
		// logger.info(v.toString());
		return collegueRepository.findByNom(nom);
	}

	public Collegue afficherParMatricule(String matricule) {
		// logger.info(v.toString());
		return collegueRepository.findByMatricule(matricule);
	}

	public CollegueService() {
		super();

	}

	public void modifierPhotoUrl(String matricule, String photoUrl) {

		Collegue collegue = collegueRepository.findByMatricule(matricule);
		collegue.setPhotoUrl(photoUrl);
		collegueRepository.save(collegue);
	}

	public void modifierEmail(String matricule, String email) {
		Collegue collegue = collegueRepository.findByMatricule(matricule);
		collegue.setEmail(email);
		collegueRepository.save(collegue);
	}

}
