package stackoverflow.project.policyretrieval.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyInfoView;
import stackoverflow.project.policyretrieval.view.PolicyResultView;
import stackoverflow.project.policyretrieval.view.PolicyUploadView;
import stackoverflow.project.policyretrieval.view.QueryView;

import java.util.List;
import java.util.Map;

public interface PolicyService {
    public ResponseUtil<String> addPolicy(PolicyUploadView policy);
    public ResponseUtil<Page<PolicyResultView>> searchTitle(Pageable pageable, List<String> keyword);
    public ResponseUtil<PolicyInfoView> searchByPolicyId(String id);
    public ResponseUtil<String> updateTitle(String id, String title);

    ResponseUtil<Page<PolicyResultView>> searchByTitleKeyword(Pageable page, String keyword);

    ResponseUtil<Page<ESPolicyEntity>> searchAll(Pageable pageable);

    ResponseUtil<Map<String, Integer>> searchProportionByType();

    ResponseUtil<Page<PolicyResultView>> searchQuery(QueryView query,String address, Pageable pageable);

    boolean existsByPolicyId(String policyId);
    
    ResponseUtil<Page<PolicyResultView>> searchByBodyKeyword(Pageable page, String keyword);

}
