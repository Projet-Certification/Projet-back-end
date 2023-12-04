package backend.Projetcertification.dto;

public class CanalPutDto {

    private Integer id;
    private boolean estLeGeneral;
    private boolean estActif;
    private String nomCanal;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
