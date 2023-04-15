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
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private HistoryService historyService;

    //根据用户返回历史记录

}
