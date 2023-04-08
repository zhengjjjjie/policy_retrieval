package stackoverflow.project.policyretrieval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.service.PolicyService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyInfoView;
import stackoverflow.project.policyretrieval.view.PolicyResultView;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("/add")
    public ResponseUtil<String> addPolicy(@RequestBody PolicyEntity policy){
        return policyService.addPolicy(policy);
    }
    @GetMapping("/search/{title}")
    public ResponseUtil<List<ESPolicyEntity>> searchByTitle(@PathVariable("title") String title) {
        return policyService.searchTitle(title);
    }
    @GetMapping("/search/info/{id}")
    public ResponseUtil<PolicyInfoView> searchByPolicyId(@PathVariable("id") String id) {
        return policyService.searchByPolicyId(id);
    }
//    @GetMapping("/search/all")
//    public ResponseUtil<Page<ESPolicyEntity>> searchAll(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable) {
//        return policyService.searchAll(pageable);
//    }
    @PostMapping("/update/title/{id}")
    public ResponseUtil<String> updateTitle(@PathVariable("id") String id,
                                            @RequestBody String title){
        return policyService.updateTitle(id, title);
    }
    @GetMapping("/searchbytitlekeyword/{titlekeyword}")
    public ResponseUtil<List<PolicyResultView>> searchByTitleKeyword(@PathVariable("titlekeyword") String titleKeyword){
        return policyService.searchByTitleKeyword(titleKeyword);
    }
    // TODO: 2023/4/8 根据多条件查找
    // TODO: 2023/4/8 查找不同政策占比
    // TODO: 2023/4/8 热点推荐
}
