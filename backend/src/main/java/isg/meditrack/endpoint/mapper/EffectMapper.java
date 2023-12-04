package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.EffectDto;
import isg.meditrack.entity.Effect;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EffectMapper {
    Effect effectDtoToEffect(EffectDto effectDto);
    EffectDto effectToEffectDto(Effect effect);
    List<EffectDto> effectListToEffectDtoList(List<Effect> effects);
}
