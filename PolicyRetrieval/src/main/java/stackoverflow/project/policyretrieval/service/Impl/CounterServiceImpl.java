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
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.CounterEntity;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.repository.ESCounterRepository;
import stackoverflow.project.policyretrieval.repository.ESPolicyRepository;
import stackoverflow.project.policyretrieval.service.CounterService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.PolicyResultView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    private final ESCounterRepository esCounterRepository;
    private final ESPolicyRepository esPolicyRepository;
    @Autowired
    private RestHighLevelClient client;
    public CounterServiceImpl(ESCounterRepository esCounterRepository, ESPolicyRepository esPolicyRepository) {
        this.esCounterRepository = esCounterRepository;
        this.esPolicyRepository = esPolicyRepository;
    }

    @Override
    public ResponseUtil<String> addRecord(String uid, String pid) {
        // 创建记录
        final CounterEntity counterEntity = new CounterEntity();
        counterEntity.setPolicyId(pid);
        counterEntity.setUserId(uid);
        // 获取当前时间
        Date date = new Date();
        counterEntity.setClickTime(date);
        //保存
        esCounterRepository.save(counterEntity);
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
//        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders
//                .terms("")
//                .field("policyid")
//                .size(Size)
//                .subAggregation(AggregationBuilders.dateHistogram("")
//                        .field(field_outer)
//                        .calendarInterval(DateHistogramInterval.WEEK));
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
//        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(policy));
//        JSONArray buckets = jsonObject.getJSONArray("buckets");
//        System.out.println("########################");
//        for (Object bucks : buckets) {
//            JSONObject buck = (JSONObject)bucks;
//            String keyAsString = buck.getString("keyAsString");
//            String docCount = buck.getString("docCount");
//            System.out.println(keyAsString + "\t-->\t"+docCount);
//        }
//        Terms terms = policy.get("date_histogram");
//        for (Terms.Bucket bucket : terms.getBuckets()) {
//            String bucketKey = bucket.getKeyAsString();
//            System.out.println("termsKey=" + bucketKey);
//            Sum sum = bucket.getAggregations().get("group_by_id");
//            String key = sum.getName();
//            double sumVal = sum.getValue();
//            System.out.println("key=" + key + ",count=" + sumVal);
//        }

        // 用@Query的方式行不通
//
////        SearchRequest searchRequest = new SearchRequest("datehistogram");
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        String aggName = "popular_policy";
//        //时间聚合
////        DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
////                AggregationBuilders.dateHistogram(aggName)
////                        .field("clicktime")
////                        .calendarInterval(DateHistogramInterval.WEEK);
//        //桶聚合
//        DateHistogramAggregationBuilder dateHistogramAggregationBuilder =
//                AggregationBuilders.dateHistogram(aggName)
//                        .field("clicktime")
//                        .calendarInterval(DateHistogramInterval.WEEK)
//                        .subAggregation(AggregationBuilders
//                                .terms("group_by_policy")
//                                .field("policy")
//                                .size(Size));
//        TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders
//                .terms("group_by_policy")
//                .field("policyid")
//                .size(Size)
//                .subAggregation(AggregationBuilders.dateHistogram(aggName)
//                        .field("clicktime")
//                        .calendarInterval(DateHistogramInterval.WEEK));
////        termsAggregationBuilder.subAggregation(termsAggregationBuilder);
//        //添加聚合
//        searchSourceBuilder.aggregation(dateHistogramAggregationBuilder);
//        //设置查询请求
//        searchRequest.source(searchSourceBuilder);
//        // 执行查询
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        //获取搜索的结果集
//        SearchHits searchHits = searchResponse.getHits();
//        Aggregations aggregations = searchResponse.getAggregations();
//        ParsedDateHistogram terms = aggregations.get(aggName);
//        List<? extends Histogram.Bucket> buckets = terms.getBuckets();
//        HashMap<String,Long> resultMap = new HashMap<>();
//        buckets.forEach(bucket -> {
//            resultMap.put(bucket.getKeyAsString(),bucket.getDocCount());
//        });
//        System.out.println("#####################"+buckets.size());
//
////        Terms terms1 = aggregations.get(aggName);
////        for (Terms.Bucket bucket : terms1.getBuckets()) {
////            String bucketKey = bucket.getKeyAsString();
////            System.out.println("termsKey="+bucketKey);
////        }
//        System.out.println(resultMap);
//        for (SearchHit searchHit : searchHits) {
//            String index = searchHit.getIndex();//获取索引名称
//            String id = searchHit.getId(); //文档id
//            Float score = searchHit.getScore();
//            String source = searchHit.getSourceAsString()
//                    .replace("{\"_class\":\"stackoverflow.project.policyretrieval.entity.CounterEntity\",","")
//                    .replace("}","");//文档内容
//
//            System.out.println(index);
//
//        }
//        List<String> test = esCounterRepository.getHotPolicies(0, Size);
//        System.out.println(test);
//        List<CounterEntity> counterEntities = null;
//        List<PolicyResultView> policyResultViews = new ArrayList<>();
//        for (CounterEntity ce : counterEntities) {
//            ESPolicyEntity es = esPolicyRepository.findByPolicyId(ce.getPolicyId());
//            PolicyResultView prv = new PolicyResultView();
//            prv.setPolicyId(es.getPolicyId());
//            prv.setPolicyTitle(es.getPolicyTitle());
//            prv.setPubTime(es.getPubTime());
//            policyResultViews.add(prv);
//        }
        return ResponseUtil.success(policyResultViews);
    }

    @Override
    public ResponseUtil<Integer> getClicks(String policy_id) {
        return null;
    }
}
