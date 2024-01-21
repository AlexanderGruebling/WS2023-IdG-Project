package isg.meditrack.endpoint.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DosagePlotDataDto {

    @NotNull
    private LocalDateTime date;
    @NotNull
    private int dosage;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }
}
