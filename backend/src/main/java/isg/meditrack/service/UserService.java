package isg.meditrack.service;

import isg.meditrack.endpoint.dto.UserLoginDto;
import isg.meditrack.entity.ApplicationUser;
import isg.meditrack.exception.ValidationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    /**
     * Find a user in the context of Spring Security based on the email address
     * <br>
     * For more information have a look at this tutorial:
     * https://www.baeldung.com/spring-security-authentication-with-a-database
     *
     * @param email the email address
     * @return a Spring Security user
     * @throws UsernameNotFoundException is thrown if the specified user does not exists
     */
    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    /**
     * Find an application user based on the email address.
     *
     * @param email the email address
     * @return an application user
     */
    ApplicationUser findApplicationUserByEmail(String email);

    /**
     * Log in a user.
     *
     * @param userLoginDto login credentials
     * @return the JWT, if successful
     * @throws BadCredentialsException if credentials are bad
     */
    String login(UserLoginDto userLoginDto) throws BadCredentialsException;

    /**
     * Register a new user.
     *
     * @param applicationUser register credentials
     * @return the JWT, if successful
     * @throws ValidationException if credentials are bad
     */
    String register(ApplicationUser applicationUser) throws ValidationException;
}
