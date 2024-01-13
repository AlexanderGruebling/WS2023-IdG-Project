package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.MedicationDto;
import isg.meditrack.entity.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface MedicationMapper {

    Medication medicationDtoToMedication(MedicationDto medicationDto);

    @Mapping(target = "medId", source = "id")
    MedicationDto medicationToMedicationDto(Medication medication);

    List<MedicationDto> medicationListToMedicationDtoList(List<Medication> byUser);
}
