package stackoverflow.project.policyretrieval.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.project.policyretrieval.entity.PreferenceEntity;

public interface PreferRepository extends JpaRepository<PreferenceEntity,String> {
}
