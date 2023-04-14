package stackoverflow.project.policyretrieval.service.Impl;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.CounterEntity;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.entity.HistoryEntity;
import stackoverflow.project.policyretrieval.entity.PreferenceEntity;
import stackoverflow.project.policyretrieval.repository.*;
import stackoverflow.project.policyretrieval.service.CounterService;
import stackoverflow.project.policyretrieval.service.HistoryService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.HistoryView;
import stackoverflow.project.policyretrieval.view.PolicyResultView;
import java.io.IOException;
import java.util.*;

import org.springframework.data.domain.Pageable;

@Service
public class CounterServiceImpl implements CounterService {

    private final ESCounterRepository esCounterRepository;
    private final ESPolicyRepository esPolicyRepository;
    private final HistoryRepository historyRepository;
    private final PreferRepository preferRepository;

    @Autowired
    private RestHighLevelClient client;
    public CounterServiceImpl(ESCounterRepository esCounterRepository, ESPolicyRepository esPolicyRepository, HistoryService historyService, HistoryRepository historyRepository, PreferRepository preferRepository, PolicyRepository policyRepository) {
        this.esCounterRepository = esCounterRepository;
        this.esPolicyRepository = esPolicyRepository;
        this.historyRepository = historyRepository;
        this.preferRepository = preferRepository;
    }

    @Override
    public ResponseUtil<String> addRecord(String uid, String pid) {
        // 创建记录
        final CounterEntity counterEntity = new CounterEntity();
        HistoryEntity historyEntity = new HistoryEntity();

        counterEntity.setPolicyId(pid);
        counterEntity.setUserId(uid);

        historyEntity.setUserName(counterEntity.getUserId());
        historyEntity.setPolicyId(counterEntity.getPolicyId());
        // 获取当前时间
        Date date = new Date(new Date().getTime() + 28800000);
        counterEntity.setClickTime(date);
        historyEntity.setClickTime(date);

        //save
        esCounterRepository.save(counterEntity);
        historyRepository.save(historyEntity);

        return ResponseUtil.successMessage("保存记录");
    }

    @Override
    public ResponseUtil<List<PolicyResultView>> getHotPolicies(Integer Size) throws IOException {


        //重建
        List<PolicyResultView> policyResultViews = new ArrayList<>();

        String indices = "datehistogram";
        String terms_outer = "date_histogram";
        String field_outer = "clicktime";
        String terms_inner = "group_by_id";
        String field_inner = "policyid";
        DateHistogramInterval interval = DateHistogramInterval.WEEK;
        SearchRequest searchRequest = new SearchRequest(indices);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
                AggregationBuilders
                        .dateHistogram(terms_outer)
                        .field(field_outer)
                        .calendarInterval(interval)
                        .subAggregation(AggregationBuilders
                                .terms(terms_inner)
                                .field(field_inner)
                                .size(Size));
        searchSourceBuilder.aggregation(dateHistogramAggregationBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        Aggregations aggregations = searchResponse.getAggregations();
        Histogram histogram = aggregations.get(terms_outer);
        for (Histogram.Bucket bucket  : histogram.getBuckets()) {
            // 获取桶的Key值
            String key = bucket.getKeyAsString();
            Terms subBuckets = bucket.getAggregations().get(terms_inner);
            long count = bucket.getDocCount();
            // 获取文档总数
            for (Terms.Bucket subBucket : subBuckets.getBuckets()) {
                String subKey = subBucket.getKeyAsString();
                long subCount = subBucket.getDocCount();

                //获取到文档的PolicyId 和 次数之后, 将返回PolicyResultView
                ESPolicyEntity esPolicyEntity = esPolicyRepository.findByPolicyId(subKey);
                PolicyResultView pv = new PolicyResultView();
                pv.setPolicyId(subKey);
                pv.setPolicyTitle(esPolicyEntity.getPolicyTitle());
                pv.setPubTime(esPolicyEntity.getPubTime());
                policyResultViews.add(pv);
            }
        }
        return ResponseUtil.success(policyResultViews);
    }
    //TODO
    @Override
    public ResponseUtil<Integer> getClicks(String policy_id) {
        return null;
    }

    @Override
    public ResponseUtil<Page<PolicyResultView>> getHistory(String uid, Pageable page) {
        // 根据用户id查询历史记录
        List<HistoryView> historyViews = new ArrayList<>();


        return null;
    }

    @Override
    public ResponseUtil<String> resetPrefer() {

        // 这里有笨蛋, 写得好烂
        // 根据用户的行为习惯, 重建搜索优化查询表
        //首先得到所有用户的uid
        List<String> users = historyRepository.searchAllUser();
        // 遍历所有用户, 构建用户画像
        for( String s : users) {
            System.out.println(s);
            Map<String, Integer> provinces = new HashMap<>();
            int i_p = 0;
            Map<String, Integer> types = new HashMap<>();
            int i_t = 0;
            Map<String, Integer> sources = new HashMap<>();
            int i_s = 0;

            //得到所有政策的条目
            List<HistoryEntity> polies = historyRepository.findByUserName(s);
            for (HistoryEntity e : polies) {
                ESPolicyEntity policy = esPolicyRepository.findByPolicyId(e.getPolicyId());
                String province = policy.getProvince();
                String type = policy.getPolicyType();
                String source = policy.getPolicySource();
                if (!province.equals("")) {
                    i_p++;
                    if (provinces.containsKey(province)) {
                        provinces.put(province, provinces.get(province)+1);
                    }
                    else {
                        provinces.put(province,1);
                    }
                }
                if (!type.equals("")) {
                    i_t++;
                    if (types.containsKey(type)) {
                        types.put(type, types.get(type)+1);
                    }
                    else {
                        types.put(type,1);
                    }
                }
                if (!source.equals("")) {
                    i_s++;
                    if (sources.containsKey(source)) {
                        sources.put(province, sources.get(province)+1);
                    }
                    else {
                        sources.put(province,1);
                    }
                }
            }
            String maxPro = null;
            String maxType = null;
            String maxSorc = null;
            for (String key : provinces.keySet()) {
                if (maxPro == null || provinces.get(key) > provinces.get(maxPro)) {
                    maxPro = key;
                }
            }
            for (String key : types.keySet()) {
                if (maxType == null || types.get(key) > types.get(maxType)) {
                    maxType = key;
                }
            }
            for (String key : sources.keySet()) {
                if (maxSorc == null || sources.get(key) > sources.get(maxSorc)) {
                    maxSorc = key;
                }
            }
            PreferenceEntity preferenceEntity = new PreferenceEntity();
            preferenceEntity.setUsername(s);

            if (maxPro != null) {
                preferenceEntity.setProvince(maxPro);
                preferenceEntity.setProvinceWeight(Double.valueOf(provinces.get(maxPro)) / ((double) i_p));
            }
            if (maxType != null) {
                preferenceEntity.setType(maxType);
                preferenceEntity.setTypeWeight(Double.valueOf(types.get(maxType)) / ((double) i_t));
            }
            if (maxSorc != null) {
                preferenceEntity.setSource(maxSorc);
                preferenceEntity.setSourceWeight(Double.valueOf(sources.get(maxSorc)) / ((double) i_s));
            }
            if (preferRepository.existsByUsername(s)) {
                //如果已经存在, 那么更新覆盖
                preferenceEntity.setId(preferRepository.findByUsername(s).getId());
            }
            // to save user
            preferRepository.save(preferenceEntity);
        }//end of user loop
        return ResponseUtil.successMessage("执行完毕");
    }
}
