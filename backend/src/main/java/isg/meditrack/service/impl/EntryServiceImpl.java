package isg.meditrack.service.impl;

import isg.meditrack.entity.Entry;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.repository.EntryRepository;
import isg.meditrack.service.EntryService;
import isg.meditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.invoke.MethodHandles;
import java.util.List;


@Service
public class EntryServiceImpl implements EntryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final EntryRepository entryRepository;
    private final UserService userService;

    public EntryServiceImpl(EntryRepository entryRepository,
                             UserService userService) {
        this.entryRepository = entryRepository;
        this.userService = userService;
    }


    @Override
    public Entry create(Entry newEntry) {
        LOGGER.debug("Create new entry {}", newEntry);

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newEntry.setUser(userService.findApplicationUserByEmail(email));

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

    @Override
    public Entry getLastByUser() {
        LOGGER.debug("Get all entries for user");

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userService.findApplicationUserByEmail(email).getId();
        return entryRepository.findLastByUserId(userId).get(0);
    }

    @Override
    public List<Entry> getByMedId(Long medId) {
        return null;
    }
}
