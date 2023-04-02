package com.example.spring_es.service;


import com.example.spring_es.DAO.ESPolicyRepository;
import com.example.spring_es.DAO.PolicyRepository;
import com.example.spring_es.model.Policy;
import com.example.spring_es.model.PolicyEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Slf4j
@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final ESPolicyRepository esPolicyRepository;
    private final TransactionTemplate transactionTemplate;

    public PolicyService(PolicyRepository policyRepository,
                         ESPolicyRepository esPolicyRepository,
                         TransactionTemplate transactionTemplate) {
        this.policyRepository = policyRepository;
        this.esPolicyRepository = esPolicyRepository;
        this.transactionTemplate = transactionTemplate;
    }


    public List<Policy> searchTitle(String keyword) {
        return  esPolicyRepository.findByPOLICYTITLE(keyword);
    }
    public List<Policy> searchAngency(String keyword) {
        return esPolicyRepository.findByPUBAGENCY(keyword);
    }
}
