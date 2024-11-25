package tpioc2.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NamedQuery(
        name = "findBetweenDate",
        query = "FROM Machine m WHERE m.dateAchat BETWEEN :d1 AND :d2"
)
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ref;
    private LocalDateTime dateAchat;  // Pas besoin de @Temporal pour LocalDateTime

    @ManyToOne
    private Salle salle;

    // Constructeur avec paramètres
    public Machine(String ref, LocalDateTime dateAchat, Salle salle) {
        this.ref = ref;
        this.dateAchat = dateAchat;
        this.salle = salle;
    }

    // Constructeur par défaut
    public Machine() {
    }

    // Getters et setters
    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
