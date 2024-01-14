package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.EffectDto;
import isg.meditrack.entity.Effect;
import isg.meditrack.exception.NotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MappingUtils.class})
public interface EffectMapper {
    @Mapping(target = "medication", source = "effectDto.medId", qualifiedByName = "getMedicationForId")
    Effect effectDtoToEffect(EffectDto effectDto) throws NotFoundException;

    EffectDto effectToEffectDto(Effect effect);

    List<EffectDto> effectListToEffectDtoList(List<Effect> effects);
}
