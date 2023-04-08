package stackoverflow.project.policyretrieval.service;


import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

public interface PolicyService {
    public ResponseUtil<String> addPolicy(PolicyEntity policy);
    public List<ESPolicyEntity> searchTitle(String keyword);
    public ESPolicyEntity searchByPolicyId(String id);
    public List<ESPolicyEntity> find(String keyword);
}
