package backend.Projetcertification.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MessageDTO {

    private Integer id;
    private String contenuMessage;
    private String dateMessage;
    private String heureMessage;
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

    public String getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(LocalDateTime dateMessage) {
        if (dateMessage != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRENCH);
            this.dateMessage = dateMessage.format(formatter);
        }
    }

    public String getHeureMessage() {
        return heureMessage;
    }

    public void setHeureMessage(LocalDateTime heureMessage) {
        if (heureMessage != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            this.heureMessage = heureMessage.format(formatter);
        }
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
