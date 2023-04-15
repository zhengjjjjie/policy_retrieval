package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;

import java.util.List;

public interface EnquirerRepository extends JpaRepository<EnquirerEntity, Integer> {
    EnquirerEntity findEnquirerEntityByUsername(String username);
//    Page<PolicyEntity> findHistoryByUsername(String username,  Pageable page);
    Page<EnquirerEntity> findAll(Pageable pageable);
    boolean existsByUsername(String username);
}
