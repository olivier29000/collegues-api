package fr.diginamic.datajpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.entites.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, String> {

	Collegue findByMatricule(String matricule);

	List<Collegue> findByNom(String nom);

	Optional<Collegue> findByEmail(String email);
}
