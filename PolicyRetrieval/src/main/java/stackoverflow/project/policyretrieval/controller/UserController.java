package stackoverflow.project.policyretrieval.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import stackoverflow.project.policyretrieval.service.HistoryService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.HistoryView;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 7200)
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private HistoryService historyService;


    @GetMapping ("/history/search/{pageNo}")
    public ResponseUtil<List<HistoryView>> loadSearch(Pageable pageable,
                                                      @PathVariable("pageNo") Integer pageNo,
                                                      @RequestBody String username) {
        //每页返回30条记录
        Pageable page = PageRequest.of(pageNo,30);
        return historyService.searchHistoryByUid(username, page);
    }
    @GetMapping("/test")
    public ResponseUtil<Integer> test() {
        return ResponseUtil.success(20);
    }
}
