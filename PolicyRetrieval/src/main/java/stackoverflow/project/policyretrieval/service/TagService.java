package stackoverflow.project.policyretrieval.service;

import stackoverflow.project.policyretrieval.entity.TagEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

public interface TagService{
    ResponseUtil<String> add(TagEntity tag);

    ResponseUtil<String> delete(int id);

    ResponseUtil<String> update(TagEntity tagEntity);

    ResponseUtil<List<TagEntity>> getAll();
    ResponseUtil<List<TagEntity>> getByName(String tagName);
}
