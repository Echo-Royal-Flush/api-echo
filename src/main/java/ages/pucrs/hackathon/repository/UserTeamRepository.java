package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.entity.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserTeamRepository extends JpaRepository<UserTeam, UUID> {
}
