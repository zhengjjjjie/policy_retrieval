package stackoverflow.project.policyretrieval.repository;


import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;

import java.util.List;
@Repository
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
//    @Highlight(fields = {
//            @HighlightField(name = "POLICYTITLE")
//    })
//    @Query("\"query\":{\"match\":{\"POLICYTITLE\":?0\" }}}")
    @Query("{\"match\":{\"POLICYTITLE\":\"?0\"}}")
    List<ESPolicyEntity> find(String keyword);

}
