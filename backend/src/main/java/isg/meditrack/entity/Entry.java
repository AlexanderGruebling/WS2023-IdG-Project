package isg.meditrack.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(
        nullable = false,
        name = "user_id"
    )
    private ApplicationUser user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinTable(
        name = "medication_used",
        joinColumns = @JoinColumn(name = "entry_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "med_id", referencedColumnName = "id")
    )
    private Set<Medication> usedMedication;

    public Entry() {
    }

    public Entry(Long id, LocalDateTime date, ApplicationUser user) {
        this.id = id;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public Set<Medication> getUsedMedication() {
        return usedMedication;
    }

    public void setUsedMedication(Set<Medication> usedMedication) {
        this.usedMedication = usedMedication;
    }
}
