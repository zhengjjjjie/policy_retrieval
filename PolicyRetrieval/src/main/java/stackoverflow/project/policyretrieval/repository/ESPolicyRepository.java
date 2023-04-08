package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.query.Param;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;

import java.util.List;

public interface ESPolicyRepository extends ElasticsearchRepository<ESPolicyEntity, String> {
    List<ESPolicyEntity> findByPolicyTitle(String policy_title);
    List<ESPolicyEntity> findByPubAgency(String pub_agency);
    ESPolicyEntity findByPolicyId(String id);

//    @Query("""
//        {
//            "query": {
//                "function_score": {
//                    "query": {
//                        "match": {
//                            "POLICYTITLE":"?0"
//                        }
//                    },
//                    "script_score": {
//                        "script": {
//                            "source": "?2 / 100.0"
//                        }
//                    },
//                    "boost_mode": "sum",
//                    "functions": [
//                        {
//                            "filter": { "match": { "PROVINCE": "?3" } },
//                            "weight": 2
//                        }
//                    ]
//                }
//            },
//            "sort": [
//                { "_score": "desc" }
//            ]
//        }
//    """)
//    List<ESPolicyEntity> searchByTitle(String key,String userRegion, @Param("click_count") int click_count);
    @Highlight(fields = {
            @HighlightField(name = "POLICYTITLE")
    })
    @Query("{\"match\":{\"POLICYTITLE\":?0\" }}")
    List<ESPolicyEntity> searchByTitle(String key);
}
