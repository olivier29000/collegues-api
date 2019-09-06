package fr.diginamic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import fr.diginamic.datajpa.CollegueRepository;
import fr.diginamic.datajpa.UtilisateurRepository;
import fr.diginamic.entites.Utilisateur;
import fr.diginamic.services.CollegueService;
import fr.diginamic.utils.CreationDeColleguesUtils;

@SpringBootApplication
public class ColleguesApiApplication {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private CollegueRepository collegueRepository;

	@Autowired
	private CollegueService collegueService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		collegueService.insererListeDeCollegues(CreationDeColleguesUtils.creerDesCollegues());
		collegueService.afficherCollegues();
		// TODO
		// Chiffrage des mots de passe avant insertion en base de données
		utilisateurRepository.save(new Utilisateur("u1", passwordEncoder.encode("pass1"),
				Arrays.asList("ROLE_ADMIN", "ROLE_USER"), collegueRepository.findAll().get(0)));
		utilisateurRepository.save(new Utilisateur("u2", passwordEncoder.encode("pass2"), Arrays.asList("ROLE_USER"),
				collegueRepository.findAll().get(1)));

	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ColleguesApiApplication.class, args);

	}

}
