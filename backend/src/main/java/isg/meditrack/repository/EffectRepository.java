package isg.meditrack.repository;

import isg.meditrack.entity.Effect;
import isg.meditrack.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface EffectRepository extends JpaRepository<Effect, Long> {
    @Query("SELECT e FROM Effect e WHERE e.medication.id = ?1")
    List<Effect> findAllByMedId(Long medId);

    @Query("SELECT e FROM Effect e WHERE e.entry.id = ?1")
    List<Effect> findAllByEntryId(Long entryId);

    @Query("SELECT e FROM Effect e WHERE e.name = ?1")
    List<Effect> findAllByName(String name);
}
