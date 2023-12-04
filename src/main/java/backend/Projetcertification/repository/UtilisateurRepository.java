package backend.Projetcertification.repository;

import backend.Projetcertification.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    public Utilisateur findByPseudo(String pseudo);
}
