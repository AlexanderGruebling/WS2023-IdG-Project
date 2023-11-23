package isg.meditrack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Effect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int intensity;

    @Column(nullable = false)
    private Boolean desired;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(
        nullable = false,
        name = "med_id"
    )
    private Medication medication;
}
