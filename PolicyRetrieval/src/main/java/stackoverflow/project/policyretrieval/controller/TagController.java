package stackoverflow.project.policyretrieval.controller;

import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.TagEntity;
import stackoverflow.project.policyretrieval.repository.TagRepository;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagRepository repository;

    public TagController(TagRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/add/{tagName}/{groupBelong}")
    public String addTag(@PathVariable("tagName") String tagName,
                         @PathVariable("groupBelong") int groupBelong){
        // if not exist then return exception
        // else
        TagEntity tag = new TagEntity();
        tag.setTagName(tagName);
        tag.setGroupBelong(groupBelong);
        repository.save(tag);
        return "添加成功！";
    }

    @PostMapping("/delete/{tagId}")
    public String deleteTag(@PathVariable("tagId") int tagId){
        // if not exist then return exception
        // else
        repository.deleteById(tagId);
        return "删除成功！";
    }

    @PostMapping("/update/{tagId}/{tagName}/{groupBelong}")
    public String updateTag(@PathVariable("tagId") int tagId,
                            @PathVariable("tagName") String tagName,
                            @PathVariable("groupBelong") int groupBelong){
        // if not exist then return exception
        // else
        TagEntity tag = repository.getReferenceById(tagId);
        tag.setTagName(tagName);
        tag.setGroupBelong(groupBelong);
        repository.save(tag);
        return "更新成功！";
    }

    @GetMapping("/getall")
    public List<TagEntity> getAllTags(){

        return repository.findAll();
    }

    @GetMapping("/getbyname/{tagName}")
    public List<TagEntity> getTagByName(@PathVariable("tagName") String tagName){
        // if Tag.getProfile(tagName) is not None Then
        return repository.findTagByTagName(tagName);
    }
}