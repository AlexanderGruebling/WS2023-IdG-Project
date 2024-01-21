package isg.meditrack.endpoint;


import isg.meditrack.endpoint.dto.EffectDto;
import isg.meditrack.endpoint.mapper.EffectMapper;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.service.EffectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/effect")
public class EffectEndpoint {

    private final EffectService effectService;
    private final EffectMapper effectMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final String BASE_PATH = "/api/v1/effect";

    public EffectEndpoint(EffectService effectService, EffectMapper effectMapper) {
        this.effectService = effectService;
        this.effectMapper = effectMapper;
    }

    @GetMapping("/{id}")
    @Secured("ROLE_USER")
    public EffectDto getById(@PathVariable Long id) {
        LOGGER.info("GET " + BASE_PATH + "/{}", id);

        try {
            return effectMapper.effectToEffectDto(effectService.getById(id));
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }

    @GetMapping("/med/{medId}")
    @Secured("ROLE_USER")
    public List<EffectDto> getForMedication(@PathVariable Long medId) {
        LOGGER.info("GET " + BASE_PATH + "/med/{}", medId);

        try {
            return effectMapper.effectListToEffectDtoList(effectService.getByMedication(medId));
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }

    @GetMapping()
    @Secured("ROLE_USER")
    public List<String> getAllEffectNames() {
        LOGGER.info("GET " + BASE_PATH);

        try {
            return effectService.getAllEffectNames();
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }

    @GetMapping("/entry/{entryId}")
    @Secured("ROLE_USER")
    public List<EffectDto> getForEntry(@PathVariable Long entryId) {
        LOGGER.info("GET " + BASE_PATH + "/entry/{}", entryId);

        try {
            return effectMapper.effectListToEffectDtoList(effectService.getByEntry(entryId));
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }
}
