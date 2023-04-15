package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stackoverflow.project.policyretrieval.entity.HistoryEntity;

import java.util.List;


public interface HistoryRepository extends JpaRepository<HistoryEntity,Integer> {
     List<HistoryEntity> findByUserName(String username, Pageable pageable);

     List<HistoryEntity> findByUserName(String username);
     @Query("SELECT DISTINCT(h.userName) FROM HistoryEntity h")
     List<String> searchAllUser();
}
