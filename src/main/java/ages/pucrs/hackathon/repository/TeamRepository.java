package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.entity.TeamEntity;
import ages.pucrs.hackathon.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamEntity, UUID> {
    @Query("SELECT ut.user FROM UserTeamEntity ut WHERE ut.team.id = :teamId")
    List<UserEntity> findAllUsersByTeamId(@Param("teamId") UUID teamId);
}
