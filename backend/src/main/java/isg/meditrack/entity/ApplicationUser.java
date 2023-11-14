package isg.meditrack.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(columnDefinition = "boolean default false")
    private Boolean admin;

    @Column(name = "locked_out", columnDefinition = "boolean default false")
    private Boolean lockedOut;

    @Column(name = "bonus_points", columnDefinition = "long default 0")
    private Long bonusPoints;

    @Column(name = "times_wrong_pw_entered", columnDefinition = "integer default 0")
    private Integer timesWrongPwEntered;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Long getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Long bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public Boolean getLockedOut() {
        return lockedOut;
    }

    public void setLockedOut(Boolean lockedOut) {
        this.lockedOut = lockedOut;
    }

    public Integer getTimesWrongPwEntered() {
        return timesWrongPwEntered;
    }

    public void setTimesWrongPwEntered(Integer timesWrongPwEntered) {
        this.timesWrongPwEntered = timesWrongPwEntered;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationUser applicationUser)) {
            return false;
        }
        return Objects.equals(id, applicationUser.id)
            && Objects.equals(email, applicationUser.email)
            && Objects.equals(firstName, applicationUser.firstName)
            && Objects.equals(lastName, applicationUser.lastName)
            && Objects.equals(password, applicationUser.password)
            && Objects.equals(lockedOut, applicationUser.lockedOut)
            && Objects.equals(bonusPoints, applicationUser.bonusPoints)
            && Objects.equals(timesWrongPwEntered, applicationUser.timesWrongPwEntered)
            && Objects.equals(admin, applicationUser.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, password, admin, lockedOut, bonusPoints, timesWrongPwEntered);
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lsatName='" + lastName + '\'' +
            ", password='" + password + '\'' +
            ", admin=" + admin +
            ", lockedOut=" + lockedOut +
            ", bonusPoints=" + bonusPoints +
            ", timesWrongPwEntered=" + timesWrongPwEntered +
            '}';
    }


    public static final class applicationUserBuilder{
        private Long id;
        private String email;

        private String firstName;

        private String lastName;
        private String password;
        private Boolean admin;
        private Boolean lockedOut;
        private Long bonusPoints;
        private Integer timesWrongPwEntered;

        private applicationUserBuilder() {
        }

        public static applicationUserBuilder anApplicationUser() {
            return new applicationUserBuilder();
        }

        public applicationUserBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public applicationUserBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public applicationUserBuilder withFirstName(String firstName) {
            this.email = email;
            return this;
        }

        public applicationUserBuilder withLastName(String lastName) {
            this.email = email;
            return this;
        }

        public applicationUserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public applicationUserBuilder isAdmin(Boolean admin) {
            this.admin = admin;
            return this;
        }

        public applicationUserBuilder isLockedOut(Boolean lockedOut) {
            this.lockedOut = lockedOut;
            return this;
        }

        public applicationUserBuilder withBonusPoints(Long bonusPoints) {
            this.bonusPoints = bonusPoints;
            return this;
        }

        public applicationUserBuilder withTimesWrongPwEntered(Integer timesWrongPwEntered) {
            this.timesWrongPwEntered = timesWrongPwEntered;
            return this;
        }

        public ApplicationUser build() {
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setId(id);
            applicationUser.setEmail(email);
            applicationUser.setEmail(firstName);
            applicationUser.setEmail(lastName);
            applicationUser.setPassword(password);
            applicationUser.setLockedOut(lockedOut);
            applicationUser.setBonusPoints(bonusPoints);
            applicationUser.setAdmin(admin);
            applicationUser.setTimesWrongPwEntered(timesWrongPwEntered);
            return applicationUser;
        }
    }
}
