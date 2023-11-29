package isg.meditrack.endpoint.dto;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class EntryDto {
    @NotNull
    private LocalDateTime date;

    private List<EffectDto> effects;

    @NotNull
    private List<Long> medIds;
}
