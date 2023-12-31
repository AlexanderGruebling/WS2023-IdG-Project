package isg.meditrack.service.validation;


import isg.meditrack.entity.ApplicationUser;
import isg.meditrack.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@Service
public class CredentialValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public void validateForRegistration(ApplicationUser applicationUser) throws ValidationException {
        LOGGER.debug("Validate credentials for Registration {}", applicationUser);
        List<String> validationErrors = new ArrayList<>();

        if (applicationUser.getEmail() != null) {
            if (applicationUser.getEmail().contains(" ")) {
                validationErrors.add("Invalid Email, check for blank spaces");
            }
            if (applicationUser.getEmail().isBlank()) {
                validationErrors.add("Invalid Email, may not be blank");
            }
            if (!applicationUser.getEmail().contains("@")) {
                validationErrors.add("Invalid Email");
            } else if (!((applicationUser.getEmail().substring(applicationUser.getEmail().indexOf('@') + 1)).contains("."))) {
                validationErrors.add("Invalid Email");
            }
        }
        else validationErrors.add("Invalid Email, can not be null");

        if (applicationUser.getFirstName() == null) {
            validationErrors.add("No first name given");
        } else if (applicationUser.getFirstName() .isBlank()) {
            validationErrors.add("First name is given but blank");
        } else if (applicationUser.getFirstName() .length() > 255) {
            validationErrors.add("First name too long: longer than 255 characters");
        }

        if (applicationUser.getLastName() == null) {
            validationErrors.add("No last name given");
        } else if (applicationUser.getLastName().isBlank()) {
            validationErrors.add("Last name is given but blank");
        } else if (applicationUser.getLastName().length() > 255) {
            validationErrors.add("Last name too long: longer than 255 characters");
        }
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors.get(0), validationErrors);
        }
    }
}
