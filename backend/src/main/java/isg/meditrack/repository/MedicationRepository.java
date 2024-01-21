package isg.meditrack.repository;

import isg.meditrack.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query("SELECT m FROM Medication m WHERE m.user.id = ?1")
    List<Medication> findAllByUserId(Long userId);

    @Query("SELECT m FROM Medication m WHERE m.id = ?1")
   Medication findByMedId(Long userId);

    @Query("SELECT m FROM Medication m WHERE m.name = ?1")
    List<Medication> findAllByName(String name);
}
