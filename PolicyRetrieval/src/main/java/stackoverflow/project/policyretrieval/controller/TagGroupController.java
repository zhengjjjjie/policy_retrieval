package stackoverflow.project.policyretrieval.controller;

import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.TagGroupEntity;
import stackoverflow.project.policyretrieval.repository.TagGroupRepository;

import java.util.List;

@RestController
@RequestMapping("/api/taggroup")
public class TagGroupController {
    private final TagGroupRepository repository;

    public TagGroupController(TagGroupRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add/{tagGroupName}")
    public String addTagGroup(@PathVariable("tagGroupName") String tagGroupName){
        // if not exist then return exception
        //else
        TagGroupEntity tagGroup = new TagGroupEntity();
        tagGroup.setTagGroupName(tagGroupName);
        repository.save(tagGroup);
        return "添加成功！";
    }

    @PostMapping("/delete/{tagGroupId}")
    public String deleteTagGroup(@PathVariable("tagGroupId") int tagGroupId){
        // if not exist then return exception
        // else
        repository.deleteById(tagGroupId);
        return "删除成功！";
    }

    @PostMapping("/update/{tagGroupId}/{newTagGroupName}")
    public String updateTagGroup(@PathVariable("tagGroupId") int tagId,
                                 @PathVariable("newTagGroupName") String newTagGroupName){
        // if not exist then return exception
        // else
        TagGroupEntity tagGroup = repository.getReferenceById(tagId);
        tagGroup.setTagGroupName(newTagGroupName);
        repository.save(tagGroup);
        return "更新成功！";
    }

    @GetMapping("/getall")
    public List<TagGroupEntity> getAllTagGroups(){
        return repository.findAll();
    }

    @GetMapping("/getbyname/{tagGroupName}")
    public List<TagGroupEntity> getTagGroupByName(@PathVariable("tagGroupName") String tagGroupName){
        // if not exist then return exception
        // else
        return repository.findTagGroupByTagGroupName(tagGroupName);
    }
}
