package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.project.policyretrieval.entity.TagGroupEntity;

import java.util.List;

public interface TagGroupRepository extends JpaRepository<TagGroupEntity, Integer> {
    List<TagGroupEntity> findTagGroupByTagGroupName(String tagGroupName);
}
