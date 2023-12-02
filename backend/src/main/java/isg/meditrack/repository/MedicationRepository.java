package isg.meditrack.repository;

import isg.meditrack.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Query("SELECT m FROM Medication m WHERE m.user.id = :userId")
    List<Medication> findAllByUserId(Long userId);
}
