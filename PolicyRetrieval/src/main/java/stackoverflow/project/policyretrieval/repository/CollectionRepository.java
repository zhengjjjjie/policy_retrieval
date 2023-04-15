package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.project.policyretrieval.entity.CollectionEntity;
import org.springframework.data.domain.Pageable;

public interface CollectionRepository extends JpaRepository<CollectionEntity,String> {
    Page<CollectionEntity> findByUserName(String username, Pageable pageable);
}
