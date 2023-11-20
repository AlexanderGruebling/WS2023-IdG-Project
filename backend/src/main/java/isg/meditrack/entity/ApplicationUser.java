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
    private String userName;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String firstName) {
        this.userName = userName;
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
            && Objects.equals(userName, applicationUser.userName)
            && Objects.equals(password, applicationUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, userName, password);
    }

    @Override
    public String toString() {
        return "ApplicationUser{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", firstName='" + userName + '\'' +
            ", password='" + password + '\'' +
            '}';
    }


    public static final class applicationUserBuilder{
        private Long id;
        private String email;
        private String userName;
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

        public applicationUserBuilder withUserName(String firstName) {
            this.userName = userName;
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
            applicationUser.setEmail(userName);
            applicationUser.setPassword(password);
            return applicationUser;
        }
    }
}
