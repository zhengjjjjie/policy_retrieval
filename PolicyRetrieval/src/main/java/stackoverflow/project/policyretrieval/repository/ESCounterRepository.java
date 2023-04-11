package stackoverflow.project.policyretrieval.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import stackoverflow.project.policyretrieval.entity.CounterEntity;

public interface ESCounterRepository extends ElasticsearchRepository<CounterEntity, String> {
}
