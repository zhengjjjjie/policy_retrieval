package stackoverflow.project.policyretrieval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.HistoryEntity;
import stackoverflow.project.policyretrieval.entity.PreferenceEntity;
import stackoverflow.project.policyretrieval.repository.ESPolicyRepository;
import stackoverflow.project.policyretrieval.service.CounterService;
import stackoverflow.project.policyretrieval.service.HistoryService;
import stackoverflow.project.policyretrieval.service.PolicyService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyResultView;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/counter")
public class CounterController {

    @Autowired
    private CounterService counterService;

    private final int SizeOfPage = 15;

    //添加记录
    @PostMapping("/add/{uid}/{pid}")
    public ResponseUtil<String> addRecord(@PathVariable("uid") String uid,
                                          @PathVariable("pid") String pid) {
        return counterService.addRecord(uid,pid);
    }
    @GetMapping("/get/hotpolicies/{size}")
    public ResponseUtil<List<PolicyResultView>> getHotPolicies(@PathVariable("size") Integer Size) throws IOException {
        // 最大查询条数限定
        Integer Max_item = 10;
        if (Size >= Max_item) {
            Size = Max_item;
        }
        return counterService.getHotPolicies(Size);
    }
    // 更新用户偏好数据
    @GetMapping("/resetprefer")
    public ResponseUtil<String> resetPrefer() {
        return counterService.resetPrefer();
    }
}
