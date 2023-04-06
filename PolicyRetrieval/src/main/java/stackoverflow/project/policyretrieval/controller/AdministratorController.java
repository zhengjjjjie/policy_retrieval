package stackoverflow.project.policyretrieval.controller;

import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stackoverflow.project.policyretrieval.entity.AdministratorEntity;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.service.AdministratorService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

@RestController
@RequestMapping("/api/admin")
public class AdministratorController {
    @Autowired
    AdministratorService service;

    @PostMapping("/login")
    public ResponseUtil<String> login(@RequestBody AdministratorEntity administratorEntity){
        return service.login(administratorEntity);
    }
    //TODO:基本方法

}
