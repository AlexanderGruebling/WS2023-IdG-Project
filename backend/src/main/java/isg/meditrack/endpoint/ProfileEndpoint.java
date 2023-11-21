package isg.meditrack.endpoint;

import isg.meditrack.endpoint.dto.RegistrationDto;
import isg.meditrack.endpoint.mapper.UserMapper;
import isg.meditrack.entity.ApplicationUser;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.lang.invoke.MethodHandles;


@RestController
@RequestMapping(value = "/api/v1/profile")
public class ProfileEndpoint {

    private final UserService userService;
    private final UserMapper userMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    static final String BASE_PATH = "/api/v1/profile";

    public ProfileEndpoint(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }



    @Transactional
    @GetMapping()
    @Secured("ROLE_USER")
    @Operation(summary = "SUMMARY HERE", security = @SecurityRequirement(name = "apiKey"))
    public RegistrationDto get() {
        LOGGER.info("GET " + BASE_PATH);
        LOGGER.debug("get()");
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicationUser currentUser = userService.findApplicationUserByEmail(email);
        try {
            return userMapper.applicationUserToRegistrationDto(userService.getById(currentUser.getId()));
        } catch (NotFoundException e) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new ResponseStatusException(status, e.getMessage(), e);
        }
    }
}
