package stackoverflow.project.policyretrieval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.service.CounterService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyResultView;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 7200)
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
    @GetMapping("/get/hotpolicies/{size}")
    public ResponseUtil<List<PolicyResultView>> getHotPolicies(@PathVariable("size") Integer Size) throws IOException {
        // 最大查询条数限定
        Integer Max_item = 10;
        if (Size >= Max_item) {
            Size = Max_item;
        }
        return counterService.getHotPolicies(Size);

    }

}
