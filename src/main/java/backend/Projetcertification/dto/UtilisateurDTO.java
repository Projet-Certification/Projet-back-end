package backend.Projetcertification.dto;

import jakarta.persistence.Column;

public class UtilisateurDTO {

    private String pseudo;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}