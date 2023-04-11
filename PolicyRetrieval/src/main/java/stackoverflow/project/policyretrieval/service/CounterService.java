package stackoverflow.project.policyretrieval.service;


import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyResultView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CounterService {

    //发出请求时, 会自动记录时间
    public ResponseUtil<String> addRecord(String uid, String pid);

    //时间聚合查询, 查询一个月内热度最高的政策
    public ResponseUtil<List<PolicyResultView>> getHotPolicies(Integer Size) throws IOException;

    //聚合查询, 某记录点击量
    public ResponseUtil<Integer> getClicks(String policy_id);

}
