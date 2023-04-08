package stackoverflow.project.policyretrieval.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyInfoView;
import stackoverflow.project.policyretrieval.view.PolicyResultView;

import java.util.List;

public interface PolicyService {
    public ResponseUtil<String> addPolicy(PolicyEntity policy);
    public ResponseUtil<List<ESPolicyEntity>> searchTitle(String keyword);
    public ResponseUtil<PolicyInfoView> searchByPolicyId(String id);
    public ResponseUtil<String> updateTitle(String id, String title);

    ResponseUtil<List<PolicyResultView>> searchByTitleKeyword(String titleKeyword);

    ResponseUtil<Page<ESPolicyEntity>> searchAll(Pageable pageable);
}
