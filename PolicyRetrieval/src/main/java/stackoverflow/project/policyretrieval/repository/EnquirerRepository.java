package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.view.CollectionView;
import stackoverflow.project.policyretrieval.view.HistoryView;

import java.util.List;

public interface EnquirerRepository extends JpaRepository<EnquirerEntity, Integer> {
    EnquirerEntity findEnquirerEntityByUsername(String username);
    @Query()
    List<PolicyEntity> findHistoryByUsername(String username);
    @Query()
    List<PolicyEntity> findCollectionByUsername(String username);
}
