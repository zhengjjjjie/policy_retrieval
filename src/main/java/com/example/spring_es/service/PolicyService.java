package com.example.spring_es.service;


import com.example.spring_es.DAO.ESPolicyRepository;
import com.example.spring_es.DAO.PolicyRepository;
import com.example.spring_es.model.Policy;
import com.example.spring_es.model.PolicyEntity;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.swing.plaf.synth.SynthEditorPaneUI;
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

    public void addPolicy(PolicyEntity policy) {
        final PolicyEntity saveESPolicy =
                transactionTemplate.execute(status ->
                        policyRepository.save(policy));
        final Policy ESpolicy =new Policy();
        assert saveESPolicy != null;
        BeanUtils.copyProperties(saveESPolicy, ESpolicy);
        try {
            esPolicyRepository.save(ESpolicy);

        } catch (Exception e) {
            log.error(String.format("保存错误%s", e.getMessage()));
        }

    }

    public List<Policy> searchTitle(String keyword) {
        return  esPolicyRepository.findByPOLICYTITLE(keyword);
    }
    public List<Policy> searchAngency(String keyword) {
        return esPolicyRepository.findByPUBAGENCY(keyword);
    }
}
