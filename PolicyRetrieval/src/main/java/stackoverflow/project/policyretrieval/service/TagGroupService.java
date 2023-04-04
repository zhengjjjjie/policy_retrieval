package stackoverflow.project.policyretrieval.service;

import stackoverflow.project.policyretrieval.entity.TagGroupEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

public interface TagGroupService {
    ResponseUtil<String> add(TagGroupEntity tagGroupEntity);

    ResponseUtil<String> delete(int id);

    ResponseUtil<String> update(TagGroupEntity tagGroupEntity);

    ResponseUtil<List<TagGroupEntity>> getAll();
}
