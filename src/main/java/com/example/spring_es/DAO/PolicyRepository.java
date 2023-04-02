package com.example.spring_es.DAO;


import com.example.spring_es.model.Policy;
import com.example.spring_es.model.PolicyEntity;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("policyRepository")
public interface PolicyRepository extends JpaRepository<PolicyEntity,String> {

    List<PolicyEntity> findByPOLICYTITLE(String policy_title);

}
