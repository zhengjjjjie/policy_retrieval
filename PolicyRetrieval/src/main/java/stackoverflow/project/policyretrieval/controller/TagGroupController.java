package stackoverflow.project.policyretrieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.TagEntity;
import stackoverflow.project.policyretrieval.entity.TagGroupEntity;
import stackoverflow.project.policyretrieval.repository.TagGroupRepository;
import stackoverflow.project.policyretrieval.service.TagGroupService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/taggroup")
public class TagGroupController {
    @Autowired
    private TagGroupService tagGroupService;

    @PostMapping("/add")
    public ResponseUtil<String> addTagGroup(@RequestBody TagGroupEntity tagGroup){
        return tagGroupService.add(tagGroup);
    }

    @PostMapping("/delete/{id}")
    public ResponseUtil<String> deleteTagGroup(@PathVariable("id") int id){
        return tagGroupService.delete(id);
    }

    @PostMapping("/update")
    public ResponseUtil<String> updateTagGroup(@RequestBody TagGroupEntity tagGroup){
        return tagGroupService.update(tagGroup);
    }

    @GetMapping("/getall")
    public ResponseUtil<List<TagGroupEntity>> getAllTagGroups(){
        return tagGroupService.getAll();
    }

    @GetMapping("/getbyname/{tagGroupName}")
    public ResponseUtil<List<TagGroupEntity>> getTagGroupByName(@PathVariable("tagGroupName") String tagGroupName){
        return tagGroupService.getByName(tagGroupName);
    }
}