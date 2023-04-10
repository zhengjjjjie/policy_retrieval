package stackoverflow.project.policyretrieval.service.Impl;

import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.TagGroupEntity;
import stackoverflow.project.policyretrieval.service.TagGroupService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@Service
public class TagGroupServiceImpl implements TagGroupService {

    @Override
    public ResponseUtil<String> add(TagGroupEntity tagGroupEntity) {
        return null;
    }

    @Override
    public ResponseUtil<String> delete(int id) {
        return null;
    }

    @Override
    public ResponseUtil<String> update(TagGroupEntity tagGroupEntity) {
        return null;
    }

    @Override
    public ResponseUtil<List<TagGroupEntity>> getAll() {
        return null;
    }

    @Override
    public ResponseUtil<List<TagGroupEntity>> getByName(String tagGroupName) {
        return null;
    }
}
