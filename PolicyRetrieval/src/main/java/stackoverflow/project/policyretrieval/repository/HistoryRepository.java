package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stackoverflow.project.policyretrieval.entity.HistoryEntity;

import java.util.List;


public interface HistoryRepository extends JpaRepository<HistoryEntity,Integer> {
//     @Query("SELECT * WHERE user_id =?0")
     List<HistoryEntity> findByUserName(String username, Pageable pageable);
}
