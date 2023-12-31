package isg.meditrack.repository;

import isg.meditrack.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    /**
     * Find all message entries ordered by published at date (descending).
     *
     * @return ordered list of all message entries
     */
    List<Message> findAllByOrderByPublishedAtDesc();

}
