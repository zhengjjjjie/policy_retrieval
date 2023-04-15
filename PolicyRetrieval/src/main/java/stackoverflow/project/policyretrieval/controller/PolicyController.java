package stackoverflow.project.policyretrieval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.service.PolicyService;
import stackoverflow.project.policyretrieval.util.AmapService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyInfoView;
import stackoverflow.project.policyretrieval.view.PolicyResultView;
import stackoverflow.project.policyretrieval.view.PolicyUploadView;
import stackoverflow.project.policyretrieval.view.QueryView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    private final int pageMax = 15;
    @PostMapping("/opr/add")
    public ResponseUtil<String> addPolicy(@RequestBody PolicyUploadView policy){
        return policyService.addPolicy(policy);
    }

    @PostMapping("/search/title/{pagNo}")
    public ResponseUtil<Page<PolicyResultView>> searchByTitle(@PathVariable("pagNo") Integer pageNo,
                                                              @RequestBody List<String> Titles) {
        //根据 标题查询, 返回简略页
        Pageable page = PageRequest.of(pageNo, pageMax);
        return policyService.searchTitle(page, Titles);
    }

    @GetMapping("/search/info/{id}")
    public ResponseUtil<PolicyInfoView> searchByPolicyId(@PathVariable("id") String id) {
        return policyService.searchByPolicyId(id);
    }
    @GetMapping("/search/all/{page}")
    public ResponseUtil<Page<ESPolicyEntity>> searchAll(@PathVariable("page") int page) {
        Pageable pageable = PageRequest.of(page,pageMax);
        return policyService.searchAll(pageable);
    }
    @PostMapping("/opr/update/title/{id}")
    public ResponseUtil<String> updateTitle(@PathVariable("id") String id,
                                            @RequestBody String title) {
        return policyService.updateTitle(id, title);
    }

    @GetMapping("/search/title/{keyword}/{page}")
    public ResponseUtil<Page<PolicyResultView>> searchByTitleKeyword(@PathVariable("page") Integer pageNo,
                                                                   @PathVariable("keyword") String keyword){
        Pageable page = PageRequest.of(pageNo,pageMax);
        return policyService.searchByTitleKeyword(page, keyword);
    }
    @GetMapping("/search/body/{keyword}/{page}")
    public ResponseUtil<Page<PolicyResultView>> searchByBodyKeyword(@PathVariable("page") Integer pageNo,
                                                                   @PathVariable("keyword") String keyword){
        Pageable page = PageRequest.of(pageNo,pageMax);
        return policyService.searchByBodyKeyword(page, keyword);
    }
    // TODO: 2023/4/8 根据多条件查找

    /*
    约定:
    多条件查询需要传递比较多的参数, 并且包含 AND 和 NOTs
    所以我们需要类来实现这些参数的传输
     */
    @PostMapping("/search/complex/{page}")
    public ResponseUtil<Page<PolicyResultView>> complexSearch(@PathVariable("page") Integer pageNo,
                                                            @RequestBody QueryView query) {
        Pageable page = PageRequest.of(pageNo, pageMax);
        //当用户访问详情页的时候, 记录其信息
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String ip = request.getRemoteAddr();
        String address = null;
        try {
            address = new AmapService().getAddressByIp(ip);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //推荐, 根据用户所在地区搜索t
        return policyService.searchQuery(query, address,page);
    }
    @PostMapping("/search/smart/{uid}/{page}")
    public ResponseUtil<Page<PolicyResultView>> smartSearch(@PathVariable("page") Integer pageNo,
                                                            @PathVariable("uid") String uid,
                                                            @RequestBody QueryView query) {
        Pageable page = PageRequest.of(pageNo, pageMax);
        //当用户访问详情页的时候, 记录其信息
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String ip = request.getRemoteAddr();
        String address = null;
        try {
            address = new AmapService().getAddressByIp(ip);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //个性化推荐
        return policyService.smartQuery(query, address,uid, page);
    }
}
