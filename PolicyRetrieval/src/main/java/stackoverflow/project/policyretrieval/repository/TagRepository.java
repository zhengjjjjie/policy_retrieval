package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.project.policyretrieval.entity.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {
}
