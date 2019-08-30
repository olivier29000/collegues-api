package fr.diginamic.datajpa;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.diginamic.entites.Collegue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CollegueRepositoryIntegrationTest {

	@Autowired
	private CollegueRepository collegueRepository;

	@Test
	public void testFindByNom() {
		String nom = UUID.randomUUID().toString();

		collegueRepository.save(new Collegue("jkbjlbnl", nom, "prenom", "email", LocalDate.now(), "photoUrl"));
		List<Collegue> listeCollegues = collegueRepository.findByNom(nom);
		Assert.assertEquals(1, listeCollegues.size());
	}

	@Test
	public void testFindByNomNomTrouve() {
		String nom = UUID.randomUUID().toString();

		List<Collegue> listeCollegues = collegueRepository.findByNom(nom);
		Assert.assertEquals(0, listeCollegues.size());
	}

}
