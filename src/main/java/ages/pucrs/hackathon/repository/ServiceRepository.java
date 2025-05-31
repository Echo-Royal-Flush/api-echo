package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Service, UUID> {
}
