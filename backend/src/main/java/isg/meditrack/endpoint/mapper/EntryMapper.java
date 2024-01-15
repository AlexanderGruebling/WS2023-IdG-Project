package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.EntryDto;
import isg.meditrack.entity.Entry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MappingUtils.class})
public interface EntryMapper {

    @Mapping(target = "usedMedication", source = "entryDto.medIds", qualifiedByName = "getMedicationSetByIdList")
    @Mapping(target = "date", source = "entryDto.date")
    Entry entryDtoToEntry(EntryDto entryDto);

    @Mapping(target = "entryId", source = "id")
    @Mapping(target = "medIds", source = "usedMedication", qualifiedByName = "getMedIds")
    EntryDto entryToEntryDto(Entry entry);

    @Mapping(target = "medIds", source = "used_in")
    List<EntryDto> entryListToEntryDtoList(List<Entry> entries);
}
