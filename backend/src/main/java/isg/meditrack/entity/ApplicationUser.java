package isg.meditrack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String username;


    @Column(nullable = false, length = 100)
    private String password;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
            && Objects.equals(username, applicationUser.username)
            && Objects.equals(password, applicationUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password);
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", firstName='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }


    public static final class applicationUserBuilder{
        private Long id;
        private String email;
        private String username;
        private String password;

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

        public applicationUserBuilder withUsername(String firstName) {
            this.username = username;
            return this;
        }


        public applicationUserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }


        public ApplicationUser build() {
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setId(id);
            applicationUser.setEmail(email);
            applicationUser.setUsername(username);
            applicationUser.setPassword(password);
            return applicationUser;
        }
    }
}
