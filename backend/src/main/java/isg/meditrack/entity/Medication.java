package isg.meditrack.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = true, length = 100)
    private int dosage;

    @Column(nullable = true, length = 100)
    private double frequency;

    @ManyToOne
    @JoinColumn(
        nullable = false,
        name = "user_id"
    )
    private ApplicationUser user;

    @ManyToMany(mappedBy = "usedMedication", fetch = FetchType.EAGER)
    private Set<Entry> usedIn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Set<Entry> getUsedIn() {
        return usedIn;
    }

    public void setUsedIn(Set<Entry> usedIn) {
        this.usedIn = usedIn;
    }
}
