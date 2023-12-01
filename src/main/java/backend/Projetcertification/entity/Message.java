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

    
}
