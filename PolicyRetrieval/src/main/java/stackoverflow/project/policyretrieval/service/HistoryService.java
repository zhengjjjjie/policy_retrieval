package stackoverflow.project.policyretrieval.service;


/*
    description: 用户历史记录相关操作
    查询某一用户全部记录
    查询
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stackoverflow.project.policyretrieval.entity.HistoryEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.HistoryView;

import java.util.List;

public interface HistoryService {
    // 返回视图参考HistoryView
    // 排列方式以时间排序
    public ResponseUtil<List<HistoryView>> searchHistoryByUid(String username, Pageable pageable);
    //根据用户返回历史记录(无分页)
    public List<HistoryEntity> searchHistoryByUid(String username);
    public List<String> getAllUserName();

}
