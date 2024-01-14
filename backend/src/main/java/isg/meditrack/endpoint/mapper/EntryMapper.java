package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.EntryDto;
import isg.meditrack.entity.Entry;
import isg.meditrack.entity.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MappingUtils.class})
public interface EntryMapper {

    @Mapping(target = "usedMedication", source = "entryDto.medIds", qualifiedByName = "getMedicationSetByIdList")
    @Mapping(target = "date", source = "entryDto.date")
    Entry entryDtoToEntry(EntryDto entryDto);

    EntryDto entryToEntryDto(Entry entry);

    List<EntryDto> entryListToEntryDtoList(List<Entry> entries);
}
