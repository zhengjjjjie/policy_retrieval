package stackoverflow.project.policyretrieval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@CrossOrigin(origins = "*", maxAge = 7200)
@RequestMapping("/api/counter")
public class CounterController {

    @Autowired
    private CounterService counterService;
    private HistoryService historyService;

    private PolicyService policyService;

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

    //以下接口弃用! 请使用api/user/history/search/{pageNo}
    @GetMapping("/get/history/{uid}/{pageNo}")
    public ResponseUtil<Page<PolicyResultView>> getHistory(@PathVariable("uid") String uid,
                                                           @PathVariable("pageNo") int pageNo) {
        Pageable page = PageRequest.of(pageNo, SizeOfPage);
        return counterService.getHistory(uid, page);
    }
    @GetMapping("/resetprefer")
    public ResponseUtil<String> resetPrefer() {
        System.out.println("######执行######");

        return counterService.resetPrefer();
    }

}
