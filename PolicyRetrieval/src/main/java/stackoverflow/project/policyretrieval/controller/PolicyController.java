package stackoverflow.project.policyretrieval.controller;


import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.service.PolicyService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import java.sql.ClientInfoStatus;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping("/add")
    public ResponseUtil<String> addPolicy(@RequestBody PolicyEntity policy){
        policyService.addPolicy(policy);
        return  ResponseUtil.successMessage("添加成功！");
    }
    @GetMapping("/search/{title}")
    public ResponseUtil<List<ESPolicyEntity>> searchByTitle(@PathVariable("title") String keyword) {
        List<ESPolicyEntity> esPolicyEntityPage = policyService.searchTitle(keyword);
        return ResponseUtil.success(esPolicyEntityPage);
    }
    @GetMapping("/search/id/{id}")
    public ResponseUtil<ESPolicyEntity> searchByPolicyId(@PathVariable("id") String keyword) {
        ESPolicyEntity esPolicy = policyService.searchByPolicyId(keyword);
        return ResponseUtil.success(esPolicy);
    }
    // 其不具有update功能, 本质是就是先删除后添加
    // 只需要保证两个document的Id相同, 就能实现更新操作
    @PostMapping("/update/title/{id}")
    public ResponseUtil<String> updateTitle(
            @PathVariable("id") String id,
            @RequestBody String title){
        try {
            ESPolicyEntity updatePolicy = policyService.searchByPolicyId(id);
            updatePolicy.setPolicyTitle(title);
            PolicyEntity savePolicy = new PolicyEntity();
            BeanUtils.copyProperties(updatePolicy, savePolicy);
            policyService.addPolicy(savePolicy);
        } catch (Exception e) {
            log.error(String.format("ERROR: can not to update Policy %s",e.getMessage()));

        }
        return ResponseUtil.successMessage("update success");
    }
    //TODO:添加标签
    //TODO:查找不同政策占比
    //TODO:热点推荐
//    @PostMapping("/update")
//    public ResponseUtil<String> updatePolicy(@RequestBody PolicyEntity policy){
//        if(!repository.findById(policy.getPolicyId()).isPresent()){
//            return ResponseUtil.failMessage("政策不存在！");
//        }
//        PolicyEntity policyEntity = repository.getReferenceById(policy.getPolicyId());
//        policyEntity.setPolicyId(policy.getPolicyId());
//        policyEntity.setTitle(policy.getTitle());
//        policyEntity.setContent(policy.getContent());
//        policyEntity.setDate(policy.getDate());
//        policyEntity.setLink(policy.getLink());
//        policyEntity.setTags(policy.getTags());
//        repository.save(policyEntity);
//        return ResponseUtil.successMessage("修改成功");
//    }

}
