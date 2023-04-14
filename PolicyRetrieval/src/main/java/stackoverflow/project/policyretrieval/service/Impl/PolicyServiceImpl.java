package stackoverflow.project.policyretrieval.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.entity.PreferenceEntity;
import stackoverflow.project.policyretrieval.repository.ESPolicyRepository;
import stackoverflow.project.policyretrieval.repository.PolicyRepository;
import stackoverflow.project.policyretrieval.repository.PreferRepository;
import stackoverflow.project.policyretrieval.service.PolicyService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyInfoView;
import stackoverflow.project.policyretrieval.view.PolicyResultView;
import stackoverflow.project.policyretrieval.view.PolicyUploadView;
import stackoverflow.project.policyretrieval.view.QueryView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static stackoverflow.project.policyretrieval.util.ConvertPageUtil.convertPage;

@Slf4j
@Service
public class PolicyServiceImpl implements PolicyService {
    private final ESPolicyRepository esPolicyRepository;
    private final PolicyRepository policyRepository;
    private final PreferRepository preferRepository;
    private final TransactionTemplate transactionTemplate;


    public PolicyServiceImpl(PolicyRepository policyRepository,
                             ESPolicyRepository esPolicyRepository,
                             PreferRepository preferRepository, TransactionTemplate transactionTemplate) {
        this.policyRepository = policyRepository;
        this.esPolicyRepository = esPolicyRepository;
        this.preferRepository = preferRepository;
        this.transactionTemplate = transactionTemplate;

    }
    @Override
    public ResponseUtil<String> addPolicy(PolicyUploadView policyUploadView) {
        // 规则性检查
        // PolicyId, policyTitle, pubTime不能为空
        if (policyUploadView.getPolicyId().equals("") || policyUploadView.getPolicyTitle().equals("") || (policyUploadView.getPubTime() == null)) {
            return ResponseUtil.failMessage("PolicyId, policyTitle, pubTime不能为空");
        }
        //查询是否存在相同POLICYID
        if (existsByPolicyId(policyUploadView.getPolicyId())) {
            return ResponseUtil.failMessage("已存在该政策");
        }
        PolicyEntity policy = new PolicyEntity();
        policy.setPolicyId(policyUploadView.getPolicyId());
        policy.setPolicyTitle(policyUploadView.getPolicyTitle());
        policy.setPolicyGrade(policyUploadView.getPolicyGrade());
        policy.setPubAgencyId(policyUploadView.getPubAgencyId());
        policy.setPubAgency(policyUploadView.getPubAgency());
        policy.setPubAgencyFullName(policyUploadView.getPubAgencyFullName());
        policy.setPubNumber(policyUploadView.getPubNumber());
        policy.setPubTime(policyUploadView.getPubTime());
        policy.setPolicyType(policyUploadView.getPolicyType());
        policy.setPolicyBody(policyUploadView.getPolicyBody());
        policy.setProvince(policyUploadView.getProvince());
        policy.setCity(policyUploadView.getCity());
        policy.setPolicySource(policyUploadView.getPolicySource());
//        Date date = new Date(new Date().getTime() + 28800000);
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        policy.setUpdateDate(date);
        BeanUtils.copyProperties(policy,policyUploadView);
        // 实现事物控制, 无状态且线性安全
        final PolicyEntity savePolicy = transactionTemplate.execute(status ->
                policyRepository.save(policy)
        );
        final ESPolicyEntity esPolicy = new ESPolicyEntity();
        assert savePolicy != null;
        BeanUtils.copyProperties(savePolicy,esPolicy);
        esPolicy.setId(savePolicy.getId());
        System.out.println(esPolicy.getPolicyId());
        System.out.println(esPolicy.getPolicyTitle());
        // try to save in es
        try {
            esPolicyRepository.save(esPolicy);
        } catch (Exception e) {
            log.error(String.format("ERROR: can not to save ESPolicy %s",e.getMessage()));
        }
        return ResponseUtil.successMessage("添加成功！");
    }
    @Override
    public ResponseUtil<Page<PolicyResultView>>searchTitle(Pageable pageable, List<String> titles){
        String keywords = titles.toString().toString().replace("[", "").replace("]", "").replace(",","");
        Page<ESPolicyEntity> esPolicyEntities = esPolicyRepository.findByPolicyTitle(pageable,keywords);
        Page<PolicyResultView> resultViews = convertPage(esPolicyEntities,PolicyResultView.class);
        return ResponseUtil.success(resultViews);
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
    public ESPolicyEntity getByPolicyId(String id) {
        return esPolicyRepository.findByPolicyId(id);
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
    public ResponseUtil<Page<PolicyResultView>> searchByTitleKeyword(Pageable page, String keyword) {
        Page<ESPolicyEntity> esPolicyEntities = esPolicyRepository.findByPolicyTitleLike(keyword, page);
        Page<PolicyResultView> policyInfoViews = convertPage(esPolicyEntities, PolicyResultView.class);
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
    public ResponseUtil<Page<PolicyResultView>> searchQuery(QueryView query,String address, Pageable pageable) {
        //将List<String>转换为String进行查询
        String titles = query.getTitles_str();
        String notitles = query.getNoTitles_str();
        String policyType = query.getPolicyType_str();
        String notPolicyType = query.getNotPolicyType_str();
        String bodies = query.getPolicyBodies_str();
        String notePolicyBodies = query.getNotPolicyBodies_str();
        double weight = (float) 0.5 / (query.getTitles().size() );
        if (weight <= 0.0001) {
            weight = 0.0001;
        }
        String boost = String.format("%.2f",weight);
        Page<ESPolicyEntity> esPolicyEntities = esPolicyRepository.searchByQuery(titles,notitles,
                policyType, notPolicyType,
                bodies,notePolicyBodies,
                address,weight,
                pageable);
        Page<PolicyResultView> policyInfoViews = convertPage(esPolicyEntities, PolicyResultView.class);
        return ResponseUtil.success(policyInfoViews);
    }
    public ResponseUtil<Page<PolicyResultView>> smartQuery(QueryView query,String address,String uid, Pageable pageable) {
        //将List<String>转换为String进行查询
        String titles = query.getTitles_str();
        String notitles = query.getNoTitles_str();
        String policyType = query.getPolicyType_str();
        String notPolicyType = query.getNotPolicyType_str();
        String bodies = query.getPolicyBodies_str();
        String notePolicyBodies = query.getNotPolicyBodies_str();
        double weight = (float) 0.5 / (query.getTitles().size() );
        if (weight <= 0.0001) {
            weight = 0.0001;
        }
        // 获取用户画像
        PreferenceEntity pe = preferRepository.findByUsername(uid);
        Page<ESPolicyEntity> esPolicyEntities = esPolicyRepository.smartsearchByQuery(titles,notitles,
                policyType, notPolicyType,
                bodies,notePolicyBodies,
                address,weight,
                pe.getProvince(), pe.getProvinceWeight(),
                pe.getType(),pe.getTypeWeight(),
                pageable);
        Page<PolicyResultView> policyInfoViews = convertPage(esPolicyEntities, PolicyResultView.class);
        return ResponseUtil.success(policyInfoViews);
    }

    @Override
    public boolean existsByPolicyId(String policyId) {
        return esPolicyRepository.existsByPolicyId(policyId);
    }
    @Override
    public ResponseUtil<Page<PolicyResultView>> searchByBodyKeyword(Pageable page, String keyword) {
        Page<ESPolicyEntity> esPolicyEntities = esPolicyRepository.findByPolicyBodyLike(keyword, page);
        Page<PolicyResultView> policyInfoViews = convertPage(esPolicyEntities, PolicyResultView.class);
        return ResponseUtil.success(policyInfoViews);
    }
}
