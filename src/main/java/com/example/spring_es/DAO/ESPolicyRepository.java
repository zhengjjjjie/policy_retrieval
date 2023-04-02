package com.example.spring_es.DAO;


import com.example.spring_es.model.Policy;
import org.elasticsearch.search.SearchHit;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository("esPolicyRepository")
public interface ESPolicyRepository extends ElasticsearchRepository<Policy,String> {

    List<Policy> findByPOLICYTITLE(String policy_title);
    List<Policy> findByPUBAGENCY(String pub_angency);

    @Highlight(fields = {
            @HighlightField(name = "POLICYTITLE")
    })
    @Query("{\"match\":{\"POLICYTITLE\":\"?0\"}}")
    SearchHit find(String keyword);


}
