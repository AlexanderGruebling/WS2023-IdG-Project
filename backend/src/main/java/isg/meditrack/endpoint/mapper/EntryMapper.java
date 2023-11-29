package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.EntryDto;
import isg.meditrack.entity.Entry;
import org.mapstruct.Mapper;

@Mapper
public interface EntryMapper {
    Entry entryDtoToMedication(EntryDto entryDto);
    EntryDto entryToEntryDto(Entry entry);
}
