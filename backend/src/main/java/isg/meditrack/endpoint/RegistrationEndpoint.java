package isg.meditrack.endpoint;

import isg.meditrack.endpoint.dto.RegistrationDto;
import isg.meditrack.endpoint.mapper.UserMapper;
import isg.meditrack.exception.ValidationException;
import isg.meditrack.service.MedicationService;
import isg.meditrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.PermitAll;
import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value = "/api/v1/registration")
public class RegistrationEndpoint {
    private final UserService userService;
    private final MedicationService medicationService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final String BASE_PATH = "/api/v1/registration";

    public RegistrationEndpoint(UserService userService, MedicationService medicationService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.medicationService = medicationService;
    }

    @PermitAll
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody RegistrationDto registrationDto) {
        LOGGER.info("POST " + BASE_PATH);
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
        registrationDto.setPassword(encodedPassword);
        try {
            String jwtToken = userService.register(userMapper.registrationDtoToApplicationUser(registrationDto));
            medicationService.createOnRegister(registrationDto.getEmail());
            return jwtToken;
        } catch (ValidationException e) {
            HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }
}
