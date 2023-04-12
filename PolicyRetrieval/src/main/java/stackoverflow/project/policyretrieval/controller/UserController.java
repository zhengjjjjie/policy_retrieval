package stackoverflow.project.policyretrieval.controller;


import com.zaxxer.hikari.util.SuspendResumeLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    @GetMapping ("/history/search")
    public ResponseUtil<List<HistoryView>> loadSearch(Pageable pageable,@RequestBody String username) {
//        Pageable page = PageRequest.of(0,10);
        // 根据uid查询用户的所有记录q
        return historyService.searchHistoryByUid(username, pageable);
    }
    @GetMapping("/test")
    public ResponseUtil<Integer> test() {

        return ResponseUtil.success(20);
    }
}
