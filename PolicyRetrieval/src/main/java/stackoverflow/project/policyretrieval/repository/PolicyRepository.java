package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;

public interface PolicyRepository extends JpaRepository<PolicyEntity, Integer> {
}