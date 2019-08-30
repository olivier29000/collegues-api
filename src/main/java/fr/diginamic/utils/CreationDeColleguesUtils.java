package fr.diginamic.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import fr.diginamic.entites.Collegue;

@Component
public class CreationDeColleguesUtils {

	public static List<Collegue> creerDesCollegues() {

		List<Collegue> data = new ArrayList<Collegue>();

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

		data.add(collegue1);
		data.add(collegue2);
		data.add(collegue3);
		data.add(collegue4);
		data.add(collegue5);

		return data;

	}
}
