package isg.meditrack.repository;

import isg.meditrack.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query("SELECT e FROM Entry e WHERE e.user.id = ?1")
    List<Entry> findAllByUserId(Long userId);

    @Query("SELECT e FROM Entry e WHERE e.user.id = ?1 ORDER BY e.date DESC")
    List<Entry> findLastByUserId(Long userId);
}
