package stackoverflow.project.policyretrieval.service.Impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.HistoryEntity;
import stackoverflow.project.policyretrieval.repository.ESPolicyRepository;
import stackoverflow.project.policyretrieval.repository.HistoryRepository;
import stackoverflow.project.policyretrieval.service.HistoryService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.HistoryView;

import java.util.ArrayList;
import java.util.List;

import static stackoverflow.project.policyretrieval.util.ConvertPageUtil.convertPage;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;
    private final ESPolicyRepository esPolicyRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository, ESPolicyRepository esPolicyRepository) {
        this.historyRepository = historyRepository;
        this.esPolicyRepository = esPolicyRepository;
    }

    @Override
    public ResponseUtil<List<HistoryView>> searchHistoryByUid(String uid,Pageable pageable) {
        //在Mysql中搜索所有与uid相匹配的字段
        //再在ES中查找对应的政策信息
        List<HistoryEntity> historyEntities = historyRepository.findByUserName(uid, pageable);
        List<HistoryView> historyViews = new ArrayList<>();

        for (HistoryEntity he : historyEntities) {
            ESPolicyEntity es = esPolicyRepository.findByPolicyId(he.getPolicyId());
            HistoryView hv = new HistoryView();
            if (es != null) {
                hv.setPolicyId(es.getPolicyId());
                hv.setPolicyTitle(es.getPolicyTitle());
                hv.setPubTime(es.getPubTime());
                hv.setHistoryTime(he.getClickTime());
            }
            historyViews.add(hv);
        }
        return ResponseUtil.success(historyViews);
    }

    @Override
    public List<HistoryEntity> searchHistoryByUid(String username) {
        return historyRepository.findByUserName(username);
    }
    @Override
    public List<String> getAllUserName() {
        return historyRepository.searchAllUser();
    }

}
