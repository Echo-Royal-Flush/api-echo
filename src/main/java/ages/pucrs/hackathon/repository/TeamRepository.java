package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamEntity, UUID> {
}
