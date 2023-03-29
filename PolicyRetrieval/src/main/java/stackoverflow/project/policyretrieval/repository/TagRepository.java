package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.project.policyretrieval.entity.TagEntity;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {
    List<TagEntity> findTagByTagName(String tagName);
}
