package stackoverflow.project.policyretrieval.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyResultView;
import java.io.IOException;
import java.util.List;

public interface CounterService {

    //发出请求时, 会自动记录时间
    public ResponseUtil<String> addRecord(String uid, String pid);

    //时间聚合查询, 查询一个月内热度最高的政策
    public ResponseUtil<List<PolicyResultView>> getHotPolicies(Integer Size) throws IOException;

    //聚合查询, 某记录点击量
    //TODO
    public ResponseUtil<Integer> getClicks(String policy_id);

    public ResponseUtil<Page<PolicyResultView>> getHistory(String uid, Pageable page);

    //构建用户画像
    public ResponseUtil<String> resetPrefer();

}
