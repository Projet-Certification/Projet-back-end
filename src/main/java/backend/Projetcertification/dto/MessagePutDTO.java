package backend.Projetcertification.dto;

import java.time.LocalDateTime;

public class MessagePutDTO {
    private Integer id;
    private String contenuMessage;
    private LocalDateTime dateMessage;
    private Integer idUtilisateur;
    private Integer idCanal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
