package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
}
