package backend.Projetcertification.dto;

import jakarta.persistence.Column;

public class CanalDTO {


    private boolean estLeGeneral;
    private boolean estActif;
    private String nomCanal;

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
}
