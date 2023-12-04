package backend.Projetcertification.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CanalDTO {


    private boolean estLeGeneral;
    private boolean estActif;
    private String nomCanal;
    private List<String> listContenuMessage;
    private String pseudoUtilisateur;
    private String heureMessage;

    public List<String> getListContenuMessage() {
        return listContenuMessage;
    }

    public void setListContenuMessage(List<String> listContenuMessage) {
        this.listContenuMessage = listContenuMessage;
    }

    public boolean isEstLeGeneral() {
        return estLeGeneral;
    }

    public void setEstLeGeneral(boolean estLeGeneral) {
        this.estLeGeneral = estLeGeneral;
    }

    public boolean isEstActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }

    public String getNomCanal() {
        return nomCanal;
    }

    public void setNomCanal(String nomCanal) {
        this.nomCanal = nomCanal;
    }

    public String getPseudoUtilisateur() {
        return pseudoUtilisateur;
    }

    public void setPseudoUtilisateur(String pseudoUtilisateur) {
        this.pseudoUtilisateur = pseudoUtilisateur;
    }

    public String getHeureMessage() {
        return heureMessage;
    }

    public void setHeureMessage(LocalDateTime heureMessage) {
        if (heureMessage != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM", Locale.FRENCH);
            this.heureMessage = heureMessage.format(formatter);
        }
    }
}
