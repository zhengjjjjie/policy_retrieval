package stackoverflow.project.policyretrieval.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyInfoView;
import stackoverflow.project.policyretrieval.view.QueryView;


import java.util.List;
import java.util.Map;

public interface PolicyService {
    public ResponseUtil<String> addPolicy(PolicyEntity policy);
    public ResponseUtil<List<ESPolicyEntity>> searchTitle(String keyword);
    public ResponseUtil<PolicyInfoView> searchByPolicyId(String id);
    public ResponseUtil<String> updateTitle(String id, String title);

    ResponseUtil<Page<PolicyInfoView>> searchByTitleKeyword(Pageable pageable, String titleKeyword);

    ResponseUtil<Page<ESPolicyEntity>> searchAll(Pageable pageable);

    ResponseUtil<Map<String, Integer>> searchProportionByType();

    ResponseUtil<Page<ESPolicyEntity>> searchQuery(QueryView query, Pageable pageable);
}
