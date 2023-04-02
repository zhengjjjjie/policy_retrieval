package com.example.spring_es.controller;


import com.example.spring_es.model.Policy;
import com.example.spring_es.model.PolicyEntity;
import com.example.spring_es.service.PolicyService;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    //政策添加
    @PostMapping("/add")
    public String addPolicy(@RequestBody PolicyEntity policy) {
        policyService.addPolicy(policy);
        return "添加成功";
    }


    // 在ES中搜索
    @GetMapping("/search/title")
    public List<Policy> searchTitle(String key)
    {
        try {
            String keyword = URLDecoder.decode(key,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("无法解析");
            return null;
        }
        return policyService.searchTitle(key);
    }
    @GetMapping("/search/pubagency/{keyword}")
    public List<Policy> searchAgency(@PathVariable("keyword") String key) {
        System.out.println(key);
        List<Policy> list = policyService.searchAngency(key);
        /* 精细搜索 */

        return list;
    }


}
