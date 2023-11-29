package isg.meditrack.endpoint;


import isg.meditrack.endpoint.dto.EntryDto;
import isg.meditrack.endpoint.mapper.EntryMapper;
import isg.meditrack.service.EntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value = "/api/v1/entry")
public class EntryEndpoint {

    private final EntryMapper entryMapper;
    private final EntryService entryService;
    static final String BASE_PATH = "/api/v1/entry";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public EntryEndpoint(EntryService entryService, EntryMapper entryMapper) {
        this.entryMapper = entryMapper;
        this.entryService = entryService;
    }



    @PostMapping
    @Secured("ROLE_USER")
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDto create(@RequestBody EntryDto entryDto) {
        LOGGER.info("POST " + BASE_PATH);

        return null;

    }
}
