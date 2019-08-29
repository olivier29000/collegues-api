package fr.diginamic;

import java.time.LocalDate;

import org.junit.Test;

import fr.diginamic.entites.Collegue;
import fr.diginamic.exceptions.CollegueInvalideException;
import fr.diginamic.services.CollegueService;

public class AjouterUnCollegueTest {

	@Test
	public void testAjoutCorrect() {
		CollegueService service = new CollegueService();
		Collegue c = new Collegue("fzfzeze", "yuuyvyugy", "egrtgrtg", "fekjberkjb@zrfzef.fr", LocalDate.of(1970, 2, 3),
				"http://www.nioutaik.fr/images/galerie/fail%2002.jpg");

		service.ajouterUnCollegue(c);

	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutExceptionNom() throws CollegueInvalideException {
		CollegueService service = new CollegueService();
		Collegue c = new Collegue("fzfzeze", "yuuyvyugy", "hrthrthr", "fekjberk@jbzrfzef.fr", LocalDate.of(1970, 2, 3),
				"http://www.nioutaik.fr/images/galerie/fail%2002.jpg");
		service.ajouterUnCollegue(c);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutExceptionPrenom() throws CollegueInvalideException {
		CollegueService service = new CollegueService();
		Collegue c = new Collegue("fzfzeze", "y", "rthrthrth", "fekjberkj@bzrfzef.fr", LocalDate.of(1970, 2, 3),
				"http://www.nioutaik.fr/images/galerie/fail%2002.jpg");
		service.ajouterUnCollegue(c);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutExceptionEmail() throws CollegueInvalideException {
		CollegueService service = new CollegueService();
		Collegue c = new Collegue("fzfzeze", "y", "rthrthrth", "fekjberkjbzrfzef.fr", LocalDate.of(1970, 2, 3),
				"http://www.nioutaik.fr/images/galerie/fail%2002.jpg");
		service.ajouterUnCollegue(c);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutExceptionEmail2() throws CollegueInvalideException {
		CollegueService service = new CollegueService();
		Collegue c = new Collegue("fzfzeze", "y", "rthrthrth", "fr", LocalDate.of(1970, 2, 3),
				"http://www.nioutaik.fr/images/galerie/fail%2002.jpg");
		service.ajouterUnCollegue(c);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutExceptionDate() throws CollegueInvalideException {
		CollegueService service = new CollegueService();
		Collegue c = new Collegue("fzfzeze", "y", "rthrthrth", "fekjberk@jbzrfzef.fr", LocalDate.of(2010, 2, 3),
				"http://www.nioutaik.fr/images/galerie/fail%2002.jpg");
		service.ajouterUnCollegue(c);
	}

	@Test(expected = CollegueInvalideException.class)
	public void testAjoutExceptionImage() throws CollegueInvalideException {
		CollegueService service = new CollegueService();
		Collegue c = new Collegue("fzfzeze", "y", "rthrthrth", "fekjberkj@bzrfzef.fr", LocalDate.of(1970, 2, 3),
				"p://www.nioutaik.fr/images/galerie/fail%2002.jpg");
		service.ajouterUnCollegue(c);
	}

}
