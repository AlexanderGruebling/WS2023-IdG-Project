package isg.meditrack.service.impl;

import isg.meditrack.entity.Medication;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.exception.ValidationException;
import isg.meditrack.repository.MedicationRepository;
import isg.meditrack.service.MedicationService;
import isg.meditrack.service.validation.CredentialValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class MedicationServiceImpl implements MedicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final MedicationRepository medicationRepository;
    private final CredentialValidator validator;

    public MedicationServiceImpl(MedicationRepository medicationRepository, CredentialValidator credentialValidator){
        this.medicationRepository = medicationRepository;
        this.validator = credentialValidator;
    }


    @Override
    public Medication create(Medication newMed) throws ValidationException {
        LOGGER.debug("Create new medication {}", newMed);

        validator.validateMedForCreation(newMed);
        newMed = medicationRepository.save(newMed);

        return newMed;
    }

    @Override
    public Medication getById(Long id) throws NotFoundException {
        LOGGER.debug("Get medication {}", id);
        if (!medicationRepository.existsById(id)) {
            throw new NotFoundException("User with given Id doesnt exist");
        } else {
            return medicationRepository.getReferenceById(id);
        }
    }
}