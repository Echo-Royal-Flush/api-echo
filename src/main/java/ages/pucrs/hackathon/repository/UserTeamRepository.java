package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.dto.UserTeamRequest;
import ages.pucrs.hackathon.entity.UserEntity;
import ages.pucrs.hackathon.entity.UserTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserTeamRepository extends JpaRepository<UserTeamEntity, UUID> {
     @Query(
    value = "SELECT u.* FROM users u " +
            "INNER JOIN users_team ut ON u.id = ut.id_users " +
            "WHERE ut.id_team = :teamId",
    nativeQuery = true
)
List<UserEntity> findUsersByTeamId(@Param("teamId") UUID teamId);

     List<UserTeamEntity> findAllByUserId(UUID userId);
}
