package isg.meditrack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @ManyToMany(mappedBy = "usedIn", fetch = FetchType.EAGER)
    private Set<Medication> usedMedication = new HashSet<>();

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
