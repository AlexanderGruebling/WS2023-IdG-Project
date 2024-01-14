package isg.meditrack.endpoint.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class EntryDto {

    private Long entryId;

    @NotNull
    private LocalDateTime date;

    private List<EffectDto> effects;

    private List<Long> medIds;

    public List<EffectDto> getEffects() {
        return effects;
    }

    public void setEffects(List<EffectDto> effects) {
        this.effects = effects;
    }

    public List<Long> getMedIds() {
        return medIds;
    }

    public void setMedIds(List<Long> medIds) {
        this.medIds = medIds;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
