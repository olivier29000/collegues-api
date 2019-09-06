package fr.diginamic.datajpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.entites.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	Optional<Utilisateur> findByNomUtilisateur(String nomUtilisateur);

}
