package isg.meditrack.endpoint.dto;

import javax.validation.constraints.NotNull;

public class MedicationDto {

    @NotNull
    private String name;
    @NotNull
    private double dosage;
    @NotNull
    private double frequency;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
}
