package isg.meditrack.endpoint.mapper;

import isg.meditrack.endpoint.dto.MedicationDto;
import isg.meditrack.entity.Medication;
import org.mapstruct.Mapper;

@Mapper
public interface MedicationMapper {

    Medication medicationDtoToMedication(MedicationDto medicationDto);
    MedicationDto medicationToMedicationDto(Medication medication);
}
