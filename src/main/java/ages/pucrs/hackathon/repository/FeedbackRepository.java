package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.entity.FeedbackEntity;
import ages.pucrs.hackathon.projection.FeedbackCountByType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.UUID;
import java.util.List;
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, UUID> {


    @Query("SELECT f.card.type AS type, COUNT(f) AS count " +
            "FROM FeedbackEntity f " +
            "WHERE f.idEvaluated = :userId " +
            "AND f.date >= :startDate " +
            "GROUP BY f.card.type")
    List<FeedbackCountByType> countFeedbacksByTypeForUserInLastMonth(
            @Param("userId") UUID userId,
            @Param("startDate") Date startDate
    );
    
    Page<FeedbackEntity> findAllByIdEvaluated(UUID idEvaluated, Pageable pageable);

    long countByDateBetweenAndEvaluatorId(Date start, Date end, UUID evaluatorId);

}
