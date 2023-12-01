package backend.Projetcertification.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "contenu_message", nullable = false, length = 255)
    private String contenuMessage;
    @Column(name = "date_message", nullable = false)
    private LocalDateTime dateMessage;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "canal_id")
    private Canal canal;

    public Message() {
        this.dateMessage = LocalDateTime.now();
    }

    public Message(String contenuMessage, LocalDateTime dateMessage, Utilisateur utilisateur, Canal canal) {
        this();
        this.contenuMessage = contenuMessage;
        this.dateMessage = dateMessage;
        this.utilisateur = utilisateur;
        this.canal = canal;
    }

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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Canal getCanal() {
        return canal;
    }

    public void setCanal(Canal canal) {
        this.canal = canal;
    }

    public void setNotNull(Message newMessage) {
        if (newMessage.getContenuMessage() != null) {
            this.contenuMessage = newMessage.getContenuMessage();
        }
        if (newMessage.getCanal() != null) {
            this.canal = newMessage.getCanal();
        }
        if (newMessage.getUtilisateur() != null) {
            this.utilisateur = newMessage.getUtilisateur();
        }
    }
}
