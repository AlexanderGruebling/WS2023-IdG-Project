package isg.meditrack.service.impl;

import isg.meditrack.endpoint.dto.UserLoginDto;
import isg.meditrack.entity.ApplicationUser;
import isg.meditrack.entity.Medication;
import isg.meditrack.exception.NotFoundException;
import isg.meditrack.exception.ValidationException;
import isg.meditrack.repository.UserRepository;
import isg.meditrack.security.JwtTokenizer;
import isg.meditrack.service.MedicationService;
import isg.meditrack.service.UserService;
import isg.meditrack.service.validation.CredentialValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;
    private final CredentialValidator validator;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository,
                                   PasswordEncoder passwordEncoder,
                                   JwtTokenizer jwtTokenizer,
                                   CredentialValidator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenizer = jwtTokenizer;
        this.validator = validator;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.debug("Load all user by email");
        try {
            ApplicationUser applicationUser = findApplicationUserByEmail(email);
            List<GrantedAuthority> grantedAuthorities;
            grantedAuthorities = AuthorityUtils.createAuthorityList("ROLE_USER");
            return new User(applicationUser.getEmail(), applicationUser.getPassword(), Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, grantedAuthorities);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public ApplicationUser findApplicationUserByEmail(String email) {
        LOGGER.debug("Find application user by email");
        ApplicationUser applicationUser = userRepository.findApplicationUserByEmail(email);
        if (applicationUser != null) {
            return applicationUser;
        }
        throw new NotFoundException(String.format("Could not find the user with the email address '%s'", email));
    }

    @Override
    public String login(UserLoginDto userLoginDto) {
        LOGGER.debug("Login User {}", userLoginDto);
        UserDetails userDetails = loadUserByUsername(userLoginDto.getEmail());
        if (userDetails != null) {
            ApplicationUser applicationUser = userRepository.findApplicationUserByEmail(userLoginDto.getEmail());
            if (passwordEncoder.matches(userLoginDto.getPassword(), userDetails.getPassword())) {
                userRepository.save(applicationUser);
                List<String> roles = userDetails.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();
                return jwtTokenizer.getAuthToken(userDetails.getUsername(), roles);
            } else {
                throw new BadCredentialsException("Password is incorrect");
            }
        }
        throw new BadCredentialsException("Account not found");
    }

    @Override
    public String register(ApplicationUser applicationUser) throws ValidationException {
        LOGGER.debug("Create new user {}", applicationUser);
        try {
            findApplicationUserByEmail(applicationUser.getEmail());
            List<String> validationErrors = new ArrayList<>();
            validationErrors.add("Email is already registered!");
            throw new ValidationException(validationErrors.get(0), validationErrors);
        } catch (NotFoundException e) {
            validator.validateForRegistration(applicationUser);
            userRepository.save(applicationUser);
            UserDetails userDetails = loadUserByUsername(applicationUser.getEmail());
            List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
            return jwtTokenizer.getAuthToken(userDetails.getUsername(), roles);
        }
    }

    @Override
    public ApplicationUser getById(Long userId) throws NotFoundException {
        LOGGER.debug("Get user {}", userId);
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with given Id doesnt exist");
        } else {
            return userRepository.getReferenceById(userId);
        }
    }


}
