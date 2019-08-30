package fr.diginamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.diginamic.services.CollegueService;
import fr.diginamic.utils.CreationDeColleguesUtils;

@SpringBootApplication
public class ColleguesApiApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ColleguesApiApplication.class, args);
		context.getBean(CollegueService.class).insererListeDeCollegues(CreationDeColleguesUtils.creerDesCollegues());
		context.getBean(CollegueService.class).afficherCollegues();

	}

}
