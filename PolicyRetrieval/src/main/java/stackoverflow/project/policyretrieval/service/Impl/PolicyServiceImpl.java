package stackoverflow.project.policyretrieval.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.repository.ESPolicyRepository;
import stackoverflow.project.policyretrieval.repository.PolicyRepository;
import stackoverflow.project.policyretrieval.service.PolicyService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyInfoView;
import stackoverflow.project.policyretrieval.view.Query;

import java.util.List;
import java.util.Map;

import static stackoverflow.project.policyretrieval.util.ConvertPageUtil.convertPage;

@Slf4j
@Service
public class PolicyServiceImpl implements PolicyService {
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
    public ResponseUtil<List<ESPolicyEntity>>searchTitle(String keyword){
        return ResponseUtil.success(esPolicyRepository.findByPolicyTitle(keyword));
    }
    @Override
    public ResponseUtil<PolicyInfoView> searchByPolicyId(String id){
        ESPolicyEntity esPolicyEntity = esPolicyRepository.findByPolicyId(id);
        PolicyInfoView policyInfoView = new PolicyInfoView();
        policyInfoView.setPolicyId(esPolicyEntity.getPolicyId());
        policyInfoView.setPolicyTitle(esPolicyEntity.getPolicyTitle());
        policyInfoView.setPolicyBody(esPolicyEntity.getPolicyBody());
        policyInfoView.setPolicySource(esPolicyEntity.getPolicySource());
        policyInfoView.setPolicyType(esPolicyEntity.getPolicyType());
        policyInfoView.setCity(esPolicyEntity.getCity());
        policyInfoView.setProvince(esPolicyEntity.getProvince());
        policyInfoView.setPubTime(esPolicyEntity.getPubTime());
        policyInfoView.setPubAgencyFullName(esPolicyEntity.getPubAgencyFullName());
        return ResponseUtil.success(policyInfoView);
    }

    @Override
    public ResponseUtil<String> updateTitle(String id, String title) {
        // 其不具有update功能, 本质是就是先删除后添加
        // 只需要保证两个document的Id相同, 就能实现更新操作
        try {
            ESPolicyEntity updatePolicy = esPolicyRepository.findByPolicyId(id);
            updatePolicy.setPolicyTitle(title);
            PolicyEntity savePolicy = new PolicyEntity();
            BeanUtils.copyProperties(updatePolicy, savePolicy);
            policyRepository.save(savePolicy);
        } catch (Exception e) {
            log.error(String.format("ERROR: can not to update Policy %s",e.getMessage()));
        }
        return ResponseUtil.successMessage("update success");
    }

    @Override
    public ResponseUtil<Page<PolicyInfoView>> searchByTitleKeyword(Pageable pageable, String titleKeyword) {
        Page<ESPolicyEntity> esPolicyEntities = esPolicyRepository.findByPolicyTitleLike(titleKeyword, pageable);
        Page<PolicyInfoView> policyInfoViews = convertPage(esPolicyEntities, PolicyInfoView.class);
        return ResponseUtil.success(policyInfoViews);
    }

    @Override
    public ResponseUtil<Page<ESPolicyEntity>> searchAll(Pageable pageable) {
        return ResponseUtil.success(esPolicyRepository.findAll(pageable));
    }

    @Override
    public ResponseUtil<Map<String, Integer>> searchProportionByType() {

        return null;
    }

    @Override
    public ResponseUtil<Page<ESPolicyEntity>> searchQuery(Query query, Pageable pageable) {
        // 设计工具类 将List<String>转换为String
        String titles = query.getTitles_str();
        String notitles = query.getNoTitles_str();
        String policyType = query.getPolicyType_str();
        String notPolicyType = query.getNotePolicyType_str();
        return ResponseUtil.success(esPolicyRepository.searchByQuery(titles,notitles, policyType, notPolicyType, pageable));
    }
}