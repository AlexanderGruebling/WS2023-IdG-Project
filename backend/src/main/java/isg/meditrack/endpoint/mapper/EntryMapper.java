package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.EntryDto;
import isg.meditrack.entity.Entry;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EntryMapper {
    Entry entryDtoToEntry(EntryDto entryDto);
    EntryDto entryToEntryDto(Entry entry);
    List<EntryDto> entryListToEntryDtoList(List<Entry> entries);
}
