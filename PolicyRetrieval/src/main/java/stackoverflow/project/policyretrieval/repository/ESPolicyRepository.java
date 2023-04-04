package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;

import java.util.List;

public interface ESPolicyRepository extends ElasticsearchRepository<ESPolicyEntity, String> {
    List<ESPolicyEntity> findByPolicyTitle(String policy_title);
    List<ESPolicyEntity> findByPubAgency(String pub_agency);
    ESPolicyEntity findByPolicyId(String id);
}
