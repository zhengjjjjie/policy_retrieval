package stackoverflow.project.policyretrieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.service.EnquirerService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/enquirer")
public class EnquirerController {
    @Autowired
    private EnquirerService enquirerService;
    //TODO:注册
    //TODO:登录

    @PostMapping("/add")
    public ResponseUtil<String> addEnquirer(@RequestBody EnquirerEntity enquirerEntity){
        return enquirerService.add(enquirerEntity);
    }

    @PostMapping("/delete/{id}")
    public ResponseUtil<String> deleteEnquirer(@PathVariable int id){
        return enquirerService.delete(id);
    }

    @PostMapping("/update")
    public ResponseUtil<String> updateEnquirer(@RequestBody EnquirerEntity enquirerEntity){
        return enquirerService.update(enquirerEntity);
    }

    @GetMapping("/getall")
    public ResponseUtil<List<EnquirerEntity>> getAllEnquirers(){
        return enquirerService.getAll();
    }

    @GetMapping("/getbyusername{username}")
    public ResponseUtil<EnquirerEntity> getEnquirerByUsername(@PathVariable String username){
        return enquirerService.getByUsername(username);
    }
    @PostMapping("/addhistory/{enquirer_id}/{policy_id}")
    public ResponseUtil<String> addHistory(@PathVariable("enquirer_id") int enquirerId,
                                          @PathVariable("policy_id") int policyId){
        return enquirerService.addHistory(enquirerId, policyId);
    }
    @GetMapping("/gethistory/{username}")
    public ResponseUtil<List<PolicyEntity>> getHistory(@PathVariable("username") String username){
        return enquirerService.getHistory(username);
    }
    @PostMapping("/addcollection/{enquirer_id}/{policy_id}")
    public ResponseUtil<String> addCollection(@PathVariable("enquirer_id") int enquirerId,
                                           @PathVariable("policy_id") int policyId){
        return enquirerService.addCollection(enquirerId, policyId);
    }
    @GetMapping("/getcollection/{username}")
    public ResponseUtil<List<PolicyEntity>> getCollection(@PathVariable("username") String username){
        return enquirerService.getCollection(username);
    }

    //TODO:阅读记录各类占比
    //TODO:收藏量排行
}
