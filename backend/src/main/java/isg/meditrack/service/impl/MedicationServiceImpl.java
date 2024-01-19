package isg.meditrack.service.impl;

import isg.meditrack.entity.Medication;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.exception.ValidationException;
import isg.meditrack.repository.MedicationRepository;
import isg.meditrack.service.MedicationService;
import isg.meditrack.service.UserService;
import isg.meditrack.service.validation.CredentialValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final MedicationRepository medicationRepository;
    private final UserService userService;
    private final CredentialValidator validator;

    public MedicationServiceImpl(MedicationRepository medicationRepository,
                                 CredentialValidator credentialValidator,
                                 UserService userService) {
        this.medicationRepository = medicationRepository;
        this.validator = credentialValidator;
        this.userService = userService;
    }


    @Override
    public Medication create(Medication newMed) throws ValidationException {
        LOGGER.debug("Create new medication {}", newMed);

        validator.validateMedForCreation(newMed);
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newMed.setUser(userService.findApplicationUserByEmail(email));
        newMed = medicationRepository.save(newMed);

        return newMed;
    }

    @Override
    public Medication getById(Long id) throws NotFoundException {
        LOGGER.debug("Get medication {}", id);
        if (!medicationRepository.existsById(id)) {
            throw new NotFoundException("Medication with given Id doesnt exist");
        } else {
            return medicationRepository.getReferenceById(id);
        }
    }

    @Override
    public List<Medication> getByUser() {
        LOGGER.debug("Get medication by User");
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userService.findApplicationUserByEmail(email).getId();

        return medicationRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public void createOnRegister(String email) {
        Medication med1 = new Medication();
        med1.setName("Aspirin + C");
        med1.setDosage(500);
        med1.setUser(userService.findApplicationUserByEmail(email));
        medicationRepository.save(med1);

        Medication med2 = new Medication();
        med2.setName("Ibuprofen");
        med2.setDosage(400);
        med2.setUser(userService.findApplicationUserByEmail(email));
        medicationRepository.save(med2);

        Medication med3 = new Medication();
        med3.setName("Mexalen");
        med3.setDosage(500);
        med3.setUser(userService.findApplicationUserByEmail(email));
        medicationRepository.save(med3);

        Medication med4 = new Medication();
        med4.setName("Thomapyrin");
        med4.setDosage(200);
        med4.setUser(userService.findApplicationUserByEmail(email));
        medicationRepository.save(med4);

        Medication med5 = new Medication();
        med5.setName("BoxaGrippal");
        med5.setDosage(200);
        med5.setUser(userService.findApplicationUserByEmail(email));
        medicationRepository.save(med5);

        Medication med6 = new Medication();
        med6.setName("None");
        med6.setUser(userService.findApplicationUserByEmail(email));
        medicationRepository.save(med6);
    }
}
