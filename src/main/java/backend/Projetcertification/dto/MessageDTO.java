package backend.Projetcertification.dto;

import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.entity.Utilisateur;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public class MessageDTO {

    private String contenuMessage;
    private LocalDateTime dateMessage;
    private Integer idUtilisateur;
    private Integer idCanal;

    public String getContenuMessage() {
        return contenuMessage;
    }

    public void setContenuMessage(String contenuMessage) {
        this.contenuMessage = contenuMessage;
    }

    public LocalDateTime getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(LocalDateTime dateMessage) {
        this.dateMessage = dateMessage;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(Integer idCanal) {
        this.idCanal = idCanal;
    }
}
