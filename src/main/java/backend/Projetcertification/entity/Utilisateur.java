package backend.Projetcertification.entity;

import backend.Projetcertification.dto.UtilisateurPutDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="pseudo", nullable = false, length = 100)
    private String pseudo;

    public Utilisateur() {
    }

    public Utilisateur(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNotNull(UtilisateurPutDTO newUtilisateurPutDto) {
        if (newUtilisateurPutDto.getPseudo() != null) {
            this.pseudo = newUtilisateurPutDto.getPseudo();
        }
    }
}
