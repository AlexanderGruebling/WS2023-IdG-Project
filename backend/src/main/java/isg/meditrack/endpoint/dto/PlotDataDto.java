package isg.meditrack.endpoint.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PlotDataDto {

    @NotNull
    private LocalDateTime date;
    @NotNull
    private int intensity;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
