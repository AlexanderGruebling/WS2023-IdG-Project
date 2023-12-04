package isg.meditrack.service.validation;


import isg.meditrack.entity.ApplicationUser;
import isg.meditrack.entity.Medication;
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
        } else {
            validationErrors.add("Invalid Email, can not be null");
        }

        if (applicationUser.getUsername() == null) {
            validationErrors.add("No username given");
        } else if (applicationUser.getUsername().isBlank()) {
            validationErrors.add("Username is given but blank");
        } else if (applicationUser.getUsername().length() > 255) {
            validationErrors.add("Username too long: longer than 255 characters");
        }


        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors.get(0), validationErrors);
        }
    }

    public void validateMedForCreation(Medication medication) throws ValidationException {
        LOGGER.debug("Validate credentials for Creation {}", medication);
        List<String> validationErrors = new ArrayList<>();

        if (medication.getName() == null) {
            validationErrors.add("Name must not be null");
        }
        if (medication.getName().length() > 100) {
            validationErrors.add("Name too long: longer than 100 characters");
        }

        if (medication.getDosage() < 0) {
            validationErrors.add("Dosis must not be negative");
        }

        if (medication.getFrequency() < 0.0) {
            validationErrors.add("Frequency must not be negative");
        }


        if (!validationErrors.isEmpty()) {
            throw new ValidationException(validationErrors.get(0), validationErrors);
        }
    }
}
