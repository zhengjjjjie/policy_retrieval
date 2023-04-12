package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;

import java.util.List;
@Repository
public interface PolicyRepository extends JpaRepository<PolicyEntity, Integer> {
    List<PolicyEntity> findByPolicyTitle(String policy_title);

}
