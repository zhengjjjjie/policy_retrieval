package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;

import java.util.List;

public interface ESPolicyRepository extends ElasticsearchRepository<ESPolicyEntity, String> {
    List<ESPolicyEntity> findByPolicyTitle(String policy_title);
    ESPolicyEntity findByPolicyId(String id);

    Page<ESPolicyEntity> findByPolicyTitleLike(String keyword, Pageable pageable);
    Page<ESPolicyEntity> findAll(Pageable pageable);


}
