package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardRepository extends JpaRepository<CardEntity, UUID> {
}
