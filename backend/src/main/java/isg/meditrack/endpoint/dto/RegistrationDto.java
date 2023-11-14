package isg.meditrack.endpoint.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class RegistrationDto {
    @NotNull(message = "Email must not be null")
    @Email
    private String email;
    @NotNull(message = "Password must not be null")
    private String password;

    @NotNull(message = "First name must not be null")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

}

