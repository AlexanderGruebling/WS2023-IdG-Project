package isg.meditrack.endpoint.mapper;

import isg.meditrack.entity.Medication;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.repository.MedicationRepository;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MappingUtils {
    private final MedicationRepository medicationRepository;

    public MappingUtils(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    @Named("getMedicationSetByIdList")
    public Set<Medication> getMedicationSetByIdList(List<Long> medIds) {
        return medIds.stream().map(medId -> {
            Medication med = null;
            try {
                med = medicationRepository.findById(medId).orElse(null);
            } catch (NotFoundException e) {

            }
            return med;
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    @Named("getMedicationForId")
    public Medication getMedForId(Long medId) {
        Optional<Medication> med = this.medicationRepository.findById(medId);
        if(med.isEmpty()) {
            throw new NotFoundException("The Medication you are trying to find does not exist!");
        }
        return med.get();
    }
}
