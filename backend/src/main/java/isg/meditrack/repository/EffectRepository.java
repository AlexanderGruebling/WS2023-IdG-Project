package isg.meditrack.repository;

import isg.meditrack.entity.Effect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface EffectRepository extends JpaRepository<Effect, Long> {
}
