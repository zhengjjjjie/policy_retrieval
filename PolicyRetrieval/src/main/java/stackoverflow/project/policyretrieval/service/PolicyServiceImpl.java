package stackoverflow.project.policyretrieval.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.repository.ESPolicyRepository;
import stackoverflow.project.policyretrieval.repository.PolicyRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@Slf4j
@Service
public class PolicyServiceImpl implements PolicyService{
    private final ESPolicyRepository esPolicyRepository;
    private final PolicyRepository policyRepository;
    private final TransactionTemplate transactionTemplate;

    public PolicyServiceImpl(PolicyRepository policyRepository,
                             ESPolicyRepository esPolicyRepository,
                             TransactionTemplate transactionTemplate) {
        this.policyRepository = policyRepository;
        this.esPolicyRepository = esPolicyRepository;
        this.transactionTemplate = transactionTemplate;
    }
    @Override
    public ResponseUtil<String> addPolicy(PolicyEntity policy) {

        // 实现事物控制, 无状态且线性安全
        final PolicyEntity savePolicy = transactionTemplate.execute(status ->
                policyRepository.save(policy)
        );
        final ESPolicyEntity esPolicy = new ESPolicyEntity();
        assert savePolicy != null;
        BeanUtils.copyProperties(savePolicy,esPolicy);
        esPolicy.setId(savePolicy.getId());
        // try to save in es
        try {
            esPolicyRepository.save(esPolicy);
        } catch (Exception e) {
            log.error(String.format("ERROR: can not to save ESPolicy %s",e.getMessage()));
        }
        return ResponseUtil.successMessage("添加成功！");
    }
    @Override
    public List<ESPolicyEntity> searchTitle(String keyword){
        return esPolicyRepository.findByPolicyTitle(keyword);
    }
    @Override
    public ESPolicyEntity searchByPolicyId(String id){
        return esPolicyRepository.findByPolicyId(id);
    }
}
