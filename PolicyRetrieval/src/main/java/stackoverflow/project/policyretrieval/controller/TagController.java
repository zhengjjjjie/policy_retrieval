package stackoverflow.project.policyretrieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.TagEntity;
import stackoverflow.project.policyretrieval.repository.TagRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private  TagRepository repository;

    @PostMapping("/add/{tagName}/{groupBelong}")
    public ResponseUtil addTag(@PathVariable("tagName") String tagName,
                               @PathVariable("groupBelong") int groupBelong){
        // if not exist then return exception
        // else
        TagEntity tag = new TagEntity();
        tag.setTagName(tagName);
        tag.setGroupBelong(groupBelong);
        repository.save(tag);
        return ResponseUtil.successMessage("添加成功！");
    }

    @PostMapping("/delete/{tagId}")
    public ResponseUtil deleteTag(@PathVariable("tagId") int tagId){
        // if not exist then return exception
        // else
        repository.deleteById(tagId);
        return ResponseUtil.successMessage("删除成功！");
    }

    @PostMapping("/update/{tagId}/{tagName}/{groupBelong}")
    public ResponseUtil updateTag(@PathVariable("tagId") int tagId,
                                  @PathVariable("tagName") String tagName,
                                  @PathVariable("groupBelong") int groupBelong){
        // if not exist then return exception
        // else
        TagEntity tag = repository.getReferenceById(tagId);
        tag.setTagName(tagName);
        tag.setGroupBelong(groupBelong);
        repository.save(tag);
        return ResponseUtil.successMessage("更新成功！");
    }

    @GetMapping("/getall")
    public ResponseUtil<List<TagEntity>> getAllTags(){

        return ResponseUtil.success(repository.findAll());
    }

    @GetMapping("/getbyname/{tagName}")
    public ResponseUtil<List<TagEntity>> getTagByName(@PathVariable("tagName") String tagName){
        // if Tag.getProfile(tagName) is not None Then
        return ResponseUtil.success(repository.findTagByTagName(tagName));
    }
}