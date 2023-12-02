package isg.meditrack.service.impl;

import isg.meditrack.entity.Effect;
import isg.meditrack.entity.Entry;
import isg.meditrack.entity.Medication;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.repository.EntryRepository;
import isg.meditrack.repository.MedicationRepository;
import isg.meditrack.service.EffectService;
import isg.meditrack.service.EntryService;
import isg.meditrack.service.MedicationService;
import isg.meditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;


@Service
public class EntryServiceImpl implements EntryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final EntryRepository entryRepository;
    private final EffectService effectService;
    private final MedicationService medicationService;
    private final UserService userService;

    public EntryServiceImpl (EntryRepository entryRepository,
                             EffectService effectService,
                             MedicationService medicationService,
                             UserService userService)
    {
        this.entryRepository = entryRepository;
        this.effectService = effectService;
        this.medicationService = medicationService;
        this.userService = userService;
    }


    @Override
    public Entry create(Entry newEntry, List<Long> medIds) {
        LOGGER.debug("Create new entry {}", newEntry);

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newEntry.setUser(userService.findApplicationUserByEmail(email));
        for (Long id: medIds) {
            newEntry.getUsedMedication().add(medicationService.getById(id));
        }

        newEntry = entryRepository.save(newEntry);

        return newEntry;
    }

    @Override
    public Entry getById(Long id) throws NotFoundException {
        LOGGER.debug("Get Entry {}", id);

        if (!entryRepository.existsById(id)) {
            throw new NotFoundException("Entry with given Id doesnt exist");
        } else {
            return entryRepository.getReferenceById(id);
        }
    }

    @Override
    public List<Entry> getByUser() {
        LOGGER.debug("Get all entries for user");

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userService.findApplicationUserByEmail(email).getId();
        return entryRepository.findAllByUserId(userId);
    }
}
