package stackoverflow.project.policyretrieval.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.service.CollectionService;
import stackoverflow.project.policyretrieval.service.EnquirerService;
import stackoverflow.project.policyretrieval.service.HistoryService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.CollectionView;
import stackoverflow.project.policyretrieval.view.EnquirerRequestView;
import stackoverflow.project.policyretrieval.view.HistoryView;

import java.util.List;


@RestController
@RequestMapping("/api/enquirer")
public class EnquirerController {
    @Autowired
    private EnquirerService enquirerService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private CollectionService collectionService;

    private final int SizeOfPage = 15;
    @GetMapping("/get/info/{username}")
    public ResponseUtil<?> getInfo(@PathVariable String username){
        return enquirerService.getInfo(username);
    }
    @PostMapping("/opr/add")
    public ResponseUtil<String> addEnquirer(@RequestBody EnquirerEntity enquirerEntity){
        return enquirerService.add(enquirerEntity);
    }

    @PostMapping("/opr/delete/{id}")
    public ResponseUtil<String> deleteEnquirer(@PathVariable int id){
        return enquirerService.delete(id);
    }

    @PostMapping("/update")
    public ResponseUtil<String> updateEnquirer(@RequestBody EnquirerEntity enquirerEntity){
        return enquirerService.update(enquirerEntity);
    }

    @GetMapping("/opr/all/{page}")
    public ResponseUtil<Page<EnquirerEntity>> getAllEnquirers(@PathVariable("page") int page) {
        Pageable pageable = PageRequest.of(page,SizeOfPage);
        return enquirerService.getAll(pageable);
    }

    @GetMapping("/opr/get/username/{username}")
    public ResponseUtil<EnquirerRequestView> getEnquirerByUsername(@PathVariable String username){
        return enquirerService.getByUsername(username);
    }
    @GetMapping ("/get/history/{uid}/{pageNo}")
    public ResponseUtil<List<HistoryView>> getHistoryByUsername(@PathVariable("pageNo") Integer pageNo,
                                                                @PathVariable("uid") String username) {
        //每页返回30条记录
        Pageable page = PageRequest.of(pageNo, SizeOfPage);
        return historyService.searchHistoryByUid(username, page);
    }
    @GetMapping("/get/collection/{uid}/{pageNo}")
    public ResponseUtil<Page<CollectionView>> getCollectionByUsername(Pageable pageable,
                                                                      @PathVariable("pageNo") Integer pageNo,
                                                                      @PathVariable("uid") String username) {
        //每页返回30条记录
        Pageable page = PageRequest.of(pageNo, SizeOfPage);
        return collectionService.searchCollectionByUsername(username, page);
    }

    @PostMapping("/add/collection/{uid}/{policyid}")
    public ResponseUtil<?> addCollectionByUsername(
            @PathVariable("policyid") String policyId,
            @PathVariable("uid") String username) {
        return collectionService.addCollection(username,policyId);
    }
    @PostMapping("/del/collection/{uid}/{policyid}")
    public ResponseUtil<?> delCollectionByUsername(@PathVariable("policyid") String policyId,
                                                   @PathVariable("uid") String username) {
        return collectionService.delCollection(username,policyId);
    }

}
