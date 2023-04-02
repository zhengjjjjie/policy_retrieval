package stackoverflow.project.policyretrieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.entity.TagEntity;
import stackoverflow.project.policyretrieval.repository.PolicyRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {
    @Autowired
    private PolicyRepository repository;

    @PostMapping("/add")
    public ResponseUtil addPolicy(@RequestBody PolicyEntity policy) {
        if (repository.findById(policy.getPolicyId()).isPresent()) {
            return ResponseUtil.failMessage("政策已存在！");
        }
        repository.save(policy);
        return ResponseUtil.successMessage("添加成功！");
    }

    @PostMapping("/delete/{policyId}")
    public ResponseUtil deletePolicy(@PathVariable int policyId) {
        if (!repository.findById(policyId).isPresent()) {
            return ResponseUtil.failMessage("政策不存在！");
        }
        repository.deleteById(policyId);
        return ResponseUtil.successMessage("删除成功！");
    }

    @PostMapping("/update")
    public ResponseUtil updatePolicy(@RequestBody PolicyEntity policy) {
        if (!repository.findById(policy.getPolicyId()).isPresent()) {
            return ResponseUtil.failMessage("政策不存在！");
        }
        PolicyEntity policyEntity = repository.getReferenceById(policy.getPolicyId());
        policyEntity.setPolicyId(policy.getPolicyId());
        policyEntity.setTitle(policy.getTitle());
        policyEntity.setContent(policy.getContent());
        policyEntity.setDate(policy.getDate());
        policyEntity.setLink(policy.getLink());
        policyEntity.setTags(policy.getTags());
        repository.save(policyEntity);
        return ResponseUtil.successMessage("修改成功");
    }

    @GetMapping("/getall")
    public ResponseUtil<List<PolicyEntity>> getAllPolicies(){
        return ResponseUtil.success(repository.findAll());
    }

    @GetMapping("/getbyname/{policyName}")
    public ResponseUtil<List<PolicyEntity>> getByName(@PathVariable String policyName){
        return ResponseUtil.success();
    }

    @GetMapping("/getbytags")
    public ResponseUtil<List<PolicyEntity>> getByTags(@RequestBody List<String> tags){
        return ResponseUtil.success();
    }
}
