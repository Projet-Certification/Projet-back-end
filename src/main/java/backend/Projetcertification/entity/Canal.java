package backend.Projetcertification.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "canals")
public class Canal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "est_general", nullable = false)
    private boolean estGeneral;
    @Column(name = "est_actif", nullable = false)
    private boolean estActif;

    @Column(name = "nom_canal", nullable = false, length = 100)
    private String nomCanal;

    @OneToMany(mappedBy = "canal", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    public Canal() {
        this.estActif = true;
        this.estGeneral = false;
    }

    public Canal(String nomCanal) {
        this();
        this.nomCanal = nomCanal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isEstGeneral() {
        return estGeneral;
    }

    public void setEstGeneral(boolean estGeneral) {
        this.estGeneral = estGeneral;
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public void addMessage(Message message) {
        messages.add(message);
        message.setCanal(this);
    }
}
