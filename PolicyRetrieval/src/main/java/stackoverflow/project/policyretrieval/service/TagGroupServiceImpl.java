package stackoverflow.project.policyretrieval.service;

import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.TagEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@Service
public class TagGroupServiceImpl implements TagService{
    @Override
    public ResponseUtil<String> add(TagEntity tag) {
        return null;
    }

    @Override
    public ResponseUtil<String> delete(int id) {
        return null;
    }

    @Override
    public ResponseUtil<String> update(TagEntity tag) {
        return null;
    }

    @Override
    public ResponseUtil<List<TagEntity>> getAll() {
        return null;
    }

    @Override
    public ResponseUtil<List<TagEntity>> getByName(String tagName) {
        return null;
    }
}
