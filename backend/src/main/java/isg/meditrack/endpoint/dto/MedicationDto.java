package isg.meditrack.endpoint.dto;

import javax.validation.constraints.NotNull;

public class MedicationDto {

    private long medId;
    @NotNull
    private String name;
    @NotNull
    private int dosage;
    @NotNull
    private double frequency;


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

    public long getMedId() {
        return medId;
    }

    public void setMedId(long medId) {
        this.medId = medId;
    }
}
