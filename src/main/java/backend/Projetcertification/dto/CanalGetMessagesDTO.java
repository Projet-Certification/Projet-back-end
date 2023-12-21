package backend.Projetcertification.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CanalGetMessagesDTO {
    private Integer id;
    private String pseudoUtilisateur;
    private String dateMessage;
    private String heureMessage;
    private String contenuMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudoUtilisateur() {
        return pseudoUtilisateur;
    }

    public void setPseudoUtilisateur(String pseudoUtilisateur) {
        this.pseudoUtilisateur = pseudoUtilisateur;
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

    public void setHeureMessage(LocalDateTime dateMessage) {
        if (dateMessage != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            this.heureMessage = dateMessage.format(formatter);
        }
    }
}
