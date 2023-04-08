package stackoverflow.project.policyretrieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.TagEntity;
import stackoverflow.project.policyretrieval.service.TagService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/add")
    public ResponseUtil<String> addTag(@RequestBody TagEntity tagEntity){
        return tagService.add(tagEntity);
    }

    @PostMapping("/delete/{id}")
    public ResponseUtil<String> deleteTag(@PathVariable("id") int id){
        return tagService.delete(id);
    }

    @PostMapping("/update")
    public ResponseUtil<String> updateTag(@RequestBody TagEntity tag){
        return tagService.update(tag);
    }

    @GetMapping("/getall")
    public ResponseUtil<List<TagEntity>> getAllTags(){
        return tagService.getAll();
    }

    @GetMapping("/getbyname/{tagName}")
    public ResponseUtil<List<TagEntity>> getTagByName(@PathVariable("tagName") String tagName){
        return tagService.getByName(tagName);
    }
}