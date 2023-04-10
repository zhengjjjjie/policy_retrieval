package stackoverflow.project.policyretrieval.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.CounterEntity;
import stackoverflow.project.policyretrieval.repository.ESCounterRepository;
import stackoverflow.project.policyretrieval.service.CounterService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyResultView;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CounterServiceImpl implements CounterService {

    private final ESCounterRepository esCounterRepository;

    public CounterServiceImpl(ESCounterRepository esCounterRepository) {
        this.esCounterRepository = esCounterRepository;
    }

    @Override
    public ResponseUtil<String> addRecord(String uid, String pid) {
        // 创建记录
        final CounterEntity counterEntity = new CounterEntity();
        counterEntity.setPolicyId(pid);
        counterEntity.setUserId(uid);
        // 获取当前时间
        Date date = new Date();
        counterEntity.setClickTime(date);
        //保存
        esCounterRepository.save(counterEntity);
        return ResponseUtil.successMessage("保存记录");
    }

    @Override
    public ResponseUtil<Page<PolicyResultView>> getHotPolicies() {
        return null;
    }

    @Override
    public ResponseUtil<Integer> getClicks(String policy_id) {
        return null;
    }
}
