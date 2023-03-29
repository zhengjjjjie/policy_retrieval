package stackoverflow.project.policyretrieval.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.repository.PolicyRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {
    private PolicyRepository repository;
    @PostMapping("/add")
    public ResponseUtil addPolicy(@RequestBody PolicyEntity policy){
        if(repository.findById(policy.getPolicyId()).isPresent()){
            return ResponseUtil.failMessage("政策已存在！");
        }
        repository.save(policy);
        return  ResponseUtil.successMessage("添加成功！");
    }

    @PostMapping("/update")
    public ResponseUtil updatePolicy(@RequestBody PolicyEntity policy){
        if(!repository.findById(policy.getPolicyId()).isPresent()){
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
}
