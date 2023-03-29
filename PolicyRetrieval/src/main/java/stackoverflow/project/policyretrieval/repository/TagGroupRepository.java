package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.project.policyretrieval.entity.TagGroupEntity;

public interface TagGroupRepository extends JpaRepository<TagGroupEntity, Integer> {
}
