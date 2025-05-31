package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.entity.UserTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserTeamRepository extends JpaRepository<UserTeamEntity, UUID> {
     List<UserTeamEntity> findByTeam_Id(UUID teamId);
}
