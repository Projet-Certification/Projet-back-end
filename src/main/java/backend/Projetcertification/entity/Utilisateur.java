package backend.Projetcertification.entity;

import backend.Projetcertification.dto.UtilisateurPutDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="pseudo", nullable = false, length = 100)
    private String pseudo;

    @Column(name="actif", nullable = false)
    private boolean actif;

    public Utilisateur() {
        this.actif = true;
    }

    public Utilisateur(String pseudo) {
        this();
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

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setNotNull(UtilisateurPutDTO newUtilisateurPutDto) {
        if (newUtilisateurPutDto.getPseudo() != null) {
            this.pseudo = newUtilisateurPutDto.getPseudo();
        }
    }
}
