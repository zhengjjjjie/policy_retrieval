package stackoverflow.project.policyretrieval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.service.CounterService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyInfoView;
import stackoverflow.project.policyretrieval.view.PolicyResultView;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/counter")
public class CounterController {

    @Autowired
    private CounterService counterService;


    //测试添加记录
    @PostMapping("/add/{uid}/{pid}")
    public ResponseUtil<String> addRecord(@PathVariable("uid") String uid,
                                          @PathVariable("pid") String pid) {
        return counterService.addRecord(uid,pid);
    }
    @GetMapping("/get/hotpolicy")
    public ResponseUtil<Page<PolicyResultView>> getHotPolicies() {
        return counterService.getHotPolicies();

    }

}
