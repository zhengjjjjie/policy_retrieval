package stackoverflow.project.policyretrieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.AdministratorEntity;
import stackoverflow.project.policyretrieval.service.AdministratorService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.LoginRequestView;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/login")
    public ResponseUtil<String> login(@RequestBody LoginRequestView loginRequestView){
        return administratorService.login(loginRequestView);
    }

    @PostMapping("/add")
    public ResponseUtil<String> addAdministrator(@RequestBody AdministratorEntity administratorEntity){
        return administratorService.add(administratorEntity);
    }

    @PostMapping("/delete/{id}")
    public ResponseUtil<String> deleteAdministrator(@PathVariable int id){
        return administratorService.delete(id);
    }

    @PostMapping("/update")
    public ResponseUtil<String> updateAdministrator(@RequestBody AdministratorEntity administratorEntity){
        return administratorService.update(administratorEntity);
    }

    @GetMapping("/getall")
    public ResponseUtil<List<AdministratorEntity>> getAllAdministrators(){
        return administratorService.getAll();
    }

}
