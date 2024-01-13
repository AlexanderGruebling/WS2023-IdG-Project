package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.EntryDto;
import isg.meditrack.entity.Entry;
import isg.meditrack.entity.Medication;
import isg.meditrack.service.MedicationService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface EntryMapper {
    @Mapping(target = "usedMedication", source = "medIds", qualifiedByName = "medIdListToMedicationSet")
    Entry entryDtoToEntry(@Context MedicationService medService, EntryDto entryDto);

    EntryDto entryToEntryDto(Entry entry);

    List<EntryDto> entryListToEntryDtoList(List<Entry> entries);


    @Named("medIdListToMedicationSet")
    static Set<Medication> medIdListToMedicationSet(@Context MedicationService medService, List<Long> medIds) {
        return medIds.stream().map(medService::getById).collect(Collectors.toSet());
    }
}
