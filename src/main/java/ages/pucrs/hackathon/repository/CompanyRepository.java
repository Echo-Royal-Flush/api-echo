package ages.pucrs.hackathon.repository;

import ages.pucrs.hackathon.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
