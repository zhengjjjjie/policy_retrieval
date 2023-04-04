package stackoverflow.project.policyretrieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.TagEntity;
import stackoverflow.project.policyretrieval.repository.TagRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;
    @Override
    public ResponseUtil<String> add(TagEntity tag) {
        tagRepository.save(tag);
        return ResponseUtil.successMessage("添加成功！");
    }

    @Override
    public ResponseUtil<String> delete(int id) {
        tagRepository.deleteById(id);
        return ResponseUtil.successMessage("添加成功！");
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
