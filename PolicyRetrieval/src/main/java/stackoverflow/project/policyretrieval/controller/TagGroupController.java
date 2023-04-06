package stackoverflow.project.policyretrieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.TagGroupEntity;
import stackoverflow.project.policyretrieval.repository.TagGroupRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/taggroup")
public class TagGroupController {
    @Autowired
    private TagGroupRepository repository;

    @PostMapping("/add/{tagGroupName}")
    public ResponseUtil addTagGroup(@PathVariable("tagGroupName") String tagGroupName){
        // if not exist then return exception
        //else
        TagGroupEntity tagGroup = new TagGroupEntity();
        tagGroup.setTagGroupName(tagGroupName);
        repository.save(tagGroup);
        return ResponseUtil.successMessage("添加成功！");
    }

    @PostMapping("/delete/{tagGroupId}")
    public ResponseUtil deleteTagGroup(@PathVariable("tagGroupId") int tagGroupId){
        // if not exist then return exception
        // else
        repository.deleteById(tagGroupId);
        return ResponseUtil.successMessage("删除成功！");
    }

    @PostMapping("/update/{tagGroupId}/{newTagGroupName}")
    public ResponseUtil updateTagGroup(@PathVariable("tagGroupId") int tagId,
                                       @PathVariable("newTagGroupName") String newTagGroupName){
        // if not exist then return exception
        // else
        TagGroupEntity tagGroup = repository.getReferenceById(tagId);
        tagGroup.setTagGroupName(newTagGroupName);
        repository.save(tagGroup);
        return ResponseUtil.successMessage("更新成功！");
    }

    @GetMapping("/getall")
    public ResponseUtil<List<TagGroupEntity>> getAllTagGroups(){
        return ResponseUtil.success(repository.findAll());
    }

    @GetMapping("/getbyname/{tagGroupName}")
    public ResponseUtil<List<TagGroupEntity>> getTagGroupByName(@PathVariable("tagGroupName") String tagGroupName){
        // if not exist then return exception
        // else
        return ResponseUtil.success(repository.findTagGroupByTagGroupName(tagGroupName));
    }
}