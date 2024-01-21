package isg.meditrack.endpoint;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import isg.meditrack.endpoint.dto.EffectDto;
import isg.meditrack.endpoint.dto.EntryDto;
import isg.meditrack.endpoint.mapper.EffectMapper;
import isg.meditrack.endpoint.mapper.EntryMapper;
import isg.meditrack.entity.Entry;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.service.EffectService;
import isg.meditrack.service.EntryService;
import isg.meditrack.service.MedicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/entry")
public class EntryEndpoint {

    private final EntryMapper entryMapper;
    private final EffectMapper effectMapper;
    private final EntryService entryService;
    private final EffectService effectService;
    static final String BASE_PATH = "/api/v1/entry";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public EntryEndpoint(EntryService entryService, EffectService effectService, EntryMapper entryMapper, EffectMapper effectMapper, MedicationService medicationService) {
        this.entryMapper = entryMapper;
        this.effectMapper = effectMapper;
        this.entryService = entryService;
        this.effectService = effectService;
    }



    @PostMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto create(@RequestBody EntryDto entryDto) {
        LOGGER.info("POST " + BASE_PATH);

        Entry newEntry = entryService.create(entryMapper.entryDtoToEntry(entryDto));
        for (EffectDto i : entryDto.getEffects()) {
            try {
                effectService.create(effectMapper.effectDtoToEffect(i), newEntry);
            } catch (NotFoundException e) {
                HttpStatus status = HttpStatus.NOT_FOUND;
                throw new ResponseStatusException(status, e.getMessage(), e);
            }
        }

        return entryMapper.entryToEntryDto(newEntry);
    }

    @PutMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EntryDto update(@RequestBody EntryDto entryDto) {
        LOGGER.info("PUT " + BASE_PATH);

        Entry entryToUpdate = entryService.update(entryMapper.entryDtoToEntry(entryDto));
        for (EffectDto i : entryDto.getEffects()) {
            try {
                effectService.update(effectMapper.effectDtoToEffect(i), entryToUpdate);
            } catch (NotFoundException e) {
                HttpStatus status = HttpStatus.NOT_FOUND;
                throw new ResponseStatusException(status, e.getMessage(), e);
            }
        }

        return entryMapper.entryToEntryDto(entryToUpdate);
    }

    @DeleteMapping("/{entryId}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_USER")
    public void delete(@PathVariable  Long entryId) {
        LOGGER.info("DELETE " + BASE_PATH);
        LOGGER.debug("delete()");

        effectService.deleteForEntry(entryId);

        try {
            entryService.delete(entryId);
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }

    @GetMapping()
    @Secured("ROLE_USER")
    public List<EntryDto> getForUser() {
        LOGGER.info("GET " + BASE_PATH);

        try {
            return entryMapper.entryListToEntryDtoList(entryService.getByUser());
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }

    @GetMapping("/last")
    @Secured("ROLE_USER")
    public EntryDto getLastForUser() {
        LOGGER.info("GET " + BASE_PATH);

        try {
            return entryMapper.entryToEntryDto(entryService.getLastByUser());
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }

    @GetMapping("/medId/{medId}")
    @Secured("ROLE_USER")
    public List<EntryDto> getEntriesByMedId(@PathVariable  Long medId) {
        LOGGER.info("GET " + BASE_PATH + "/medId/{}", medId);

        try {
            return entryMapper.entryListToEntryDtoList(entryService.getByUser());
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }
}
