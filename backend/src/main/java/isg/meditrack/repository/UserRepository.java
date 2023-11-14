package isg.meditrack.repository;

import isg.meditrack.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    /**
     * Find all user entries ordered by email.
     *
     * @return ordered list of all user entries
     */
    List<ApplicationUser> findAllByEmail(String email);

    /**
     * Find user entries with the given email.
     *
     * @param email E-mail address of the user that is looked for
     * @return User with given email
     */
    ApplicationUser findApplicationUserByEmail(String email);
}
