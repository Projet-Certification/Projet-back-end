package backend.Projetcertification.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CanalDTO {


    private Integer id;
    private boolean estLeGeneral;
    private boolean estActif;
    private String nomCanal;
    private List<CanalGetMessagesDTO> listContenuMessage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CanalGetMessagesDTO> getListContenuMessage() {
        return listContenuMessage;
    }

    public void setListContenuMessage(List<CanalGetMessagesDTO> listContenuMessage) {
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


}
