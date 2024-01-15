package isg.meditrack.endpoint.dto;


import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class EffectDto {

    @NotNull
    private String name;
    private String description;
    @NotNull
    private int intensity;
    @NotNull
    private boolean desired;
    @NotNull
    private long medId;
    private LocalDateTime date;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public boolean isDesired() {
        return desired;
    }

    public void setDesired(boolean desired) {
        this.desired = desired;
    }

    public long getMedId() {
        return medId;
    }

    public void setMedId(long medId) {
        this.medId = medId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
