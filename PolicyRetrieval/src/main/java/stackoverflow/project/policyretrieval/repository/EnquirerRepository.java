package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;

public interface EnquirerRepository extends JpaRepository<EnquirerEntity, Integer> {
}
