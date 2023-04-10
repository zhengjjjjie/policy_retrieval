package stackoverflow.project.policyretrieval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.service.PolicyService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyInfoView;
import stackoverflow.project.policyretrieval.view.Query;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
    @GetMapping("/search/all")
    public ResponseUtil<Page<ESPolicyEntity>> searchAll(Pageable pageable) {
        return policyService.searchAll(pageable);
    }
    @PostMapping("/update/title/{id}")
    public ResponseUtil<String> updateTitle(@PathVariable("id") String id,
                                            @RequestBody String title){
        return policyService.updateTitle(id, title);
    }
    @GetMapping("/searchbytitlekeyword/{titlekeyword}")
    public ResponseUtil<Page<PolicyInfoView>> searchByTitleKeyword(Pageable pageable,
                                                                   @PathVariable("titlekeyword") String titleKeyword){
        return policyService.searchByTitleKeyword(pageable, titleKeyword);
    }
    // TODO: 2023/4/8 根据多条件查找

    /*
    约定:
    多条件查询需要传递比较多的参数, 并且包含 AND 和 NOTs
    所以我们需要类来实现这些参数的传输
     */
    @PostMapping("/complexsearch/{page}")
    public ResponseUtil<Page<ESPolicyEntity>> complexSearch(@PathVariable("page") Integer pageNo,
                                                            @RequestBody Query query){
        Pageable page = PageRequest.of(pageNo,15);
        return policyService.searchQuery(query, page);
    }


    // TODO: 2023/4/8 查找不同政策占比
//    @GetMapping("/search/typeproportion")
//    public ResponseUtil<Map<String, Integer>> searchProportionByType(){67
//        return policyService.searchProportionByType();
//    }
    // TODO: 2023/4/8 热点推荐

    @GetMapping("/test")
    public ResponseUtil<Query> test() {
        Query query = new Query();
        List<String> a = new ArrayList<>();
        a.add("123");
        List<String> b = new ArrayList<>();
        b.add("234");
        List<String> c = new ArrayList<>();
        c.add("345");
        List<String> d = new ArrayList<>();
        d.add("456");
        query.setTitles(a);
        query.setPolicyType(b);
        query.setNotPolicyType(c);
        query.setNotTitles(d);
        return ResponseUtil.success(query);
    }
}
