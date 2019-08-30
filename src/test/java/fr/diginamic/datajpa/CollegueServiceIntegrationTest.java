package fr.diginamic.datajpa;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.diginamic.entites.Collegue;
import fr.diginamic.services.CollegueService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollegueServiceIntegrationTest {

	@Autowired
	CollegueService collegueService;
	@Autowired
	CollegueRepository collegueRepository;

	@Test
	public void afficherParNomTestOk() {
		String nom = UUID.randomUUID().toString();
		collegueRepository.save(new Collegue("matricule", nom, "prenoms", "email", LocalDate.now(), "photoUrl"));

		Assert.assertEquals(1, collegueService.afficherParNom(nom).size());
	}

	@Test
	public void afficherParNomTestOk2() {
		String nom = UUID.randomUUID().toString();
		collegueRepository.save(new Collegue("matricule", nom, "prenoms", "email", LocalDate.now(), "photoUrl"));
		collegueRepository.save(new Collegue("matricule2", nom, "prenoms", "email", LocalDate.now(), "photoUrl"));

		Assert.assertEquals(2, collegueService.afficherParNom(nom).size());
	}

	@Test
	public void afficherParNomTestOk3() {
		String nom = UUID.randomUUID().toString();
		collegueRepository.save(new Collegue("matricule", nom, "prenoms", "email", LocalDate.now(), "photoUrl"));
		collegueRepository.save(new Collegue("matricule2", nom, "prenoms", "email", LocalDate.now(), "photoUrl"));
		collegueRepository.save(new Collegue("matricule2", nom, "prenoms", "email", LocalDate.now(), "photoUrl"));

		Assert.assertEquals(3, collegueService.afficherParNom(nom).size());
	}

}
