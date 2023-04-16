package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Repository
public interface PolicyRepository extends JpaRepository<PolicyEntity, Integer> {
    List<PolicyEntity> findByPolicyTitle(String policy_title);

    Integer deleteByPolicyId(String policyId);
}
