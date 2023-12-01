package backend.Projetcertification.repository;

import backend.Projetcertification.entity.Canal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CanalRepository extends JpaRepository<Canal, Integer> {

}

