package isg.meditrack.endpoint;


import isg.meditrack.endpoint.dto.MedicationDto;
import isg.meditrack.endpoint.mapper.MedicationMapper;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.exception.ValidationException;
import isg.meditrack.service.MedicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value = "/api/v1/medication")
public class MedicationEndpoint {

    private final MedicationMapper medicationMapper;
    private final MedicationService medicationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final String BASE_PATH = "/api/v1/medication";

    public MedicationEndpoint(MedicationService medicationService, MedicationMapper medicationMapper) {
        this.medicationMapper = medicationMapper;
        this.medicationService = medicationService;
    }


    @PostMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicationDto create(@RequestBody MedicationDto medicationDto) {
        LOGGER.info("POST " + BASE_PATH);

        try {
            return medicationMapper.medicationToMedicationDto(
                medicationService.create(medicationMapper.medicationDtoToMedication(medicationDto))
            );
        } catch (ValidationException e) {
            HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    @Secured("ROLE_USER")
    public MedicationDto getById(@PathVariable long id) {
        LOGGER.info("GET " + BASE_PATH + "/{}", id);

        try {
            return medicationMapper.medicationToMedicationDto(medicationService.getById(id));
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }

    @GetMapping()
    @Secured("ROLE_USER")
    public List<MedicationDto> getForUser() {
        LOGGER.info("GET " + BASE_PATH);

        try {
            return medicationMapper.medicationListToMedicationDtoList(medicationService.getByUser());
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }
}
