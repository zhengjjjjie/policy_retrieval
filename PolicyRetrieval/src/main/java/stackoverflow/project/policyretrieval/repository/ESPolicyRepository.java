package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;

import java.util.List;

public interface ESPolicyRepository extends ElasticsearchRepository<ESPolicyEntity, String> {
    Page<ESPolicyEntity> findByPolicyTitle(Pageable pageable,String policy_title);
    ESPolicyEntity findByPolicyId(String id);

    Page<ESPolicyEntity> findByPolicyTitleLike(String keyword, Pageable pageable);

    Page<ESPolicyEntity> findAll(Pageable pageable);

    //
    @Query(
            "{"+
            "    \"bool\": {"+
            "      \"should\": ["+
            "        {\"match\":"+
            "          {"+
            "            \"POLICYTITLE\": \"?0\""+
            "          }"+
            "        },"+
            "        {\"match\":"+
            "          {"+
            "            \"POLICYTYPE\": \"?2\""+
            "          }"+
            "        },"+
            "        {\"match\":"+
            "          {"+
            "          \"POLICYBODY\": \"?4\""+
            "          }"+
            "        },"+
            "        {\"match\":"+
            "          {"+
            "          \"POLICYTITLE\": {"+
            "            \"query\": \"?6\","+
            "            \"boost\": ?7"+
            "            }"+
            "          }"+
            "        }"+
            "      ],"+
            "      \"must_not\": ["+
            "        {\"match\":"+
            "          {"+
            "            \"POLICYTITLE\": \"?1\""+
            "          }"+
            "        },"+
            "        { \"match\":"+
            "          {"+
            "            \"POLICYTYPE\": \"?3\""+
            "          }"+
            "        },"+
            "        { \"match\":"+
            "          {"+
            "          \"POLICYBODY\": \"?5\""+
            "          }"+
            "        }"+
            "      ]"+
            "    }"+
            "  }"
    )
    Page<ESPolicyEntity> searchByQuery(String titles, String notTitles,
                                       String policy_type,String noTpolicy_type,
                                       String bodies, String notBodies,
                                       String address,double boost,
                                       Pageable pageable);
    
    boolean existsByPolicyId(String id);

    Page<ESPolicyEntity> findByPolicyBodyLike(String keyword, Pageable page);
}
