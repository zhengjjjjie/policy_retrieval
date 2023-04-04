package stackoverflow.project.policyretrieval.service;


import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;

import java.util.List;

public interface PolicyService {
    public void addPolicy(PolicyEntity policy);
    public List<ESPolicyEntity> searchTitle(String keyword);
    public ESPolicyEntity searchByPolicyId(String id);
}
