package stackoverflow.project.policyretrieval.service;


/*
    description: 用户历史记录相关操作
    查询某一用户全部记录
    查询
 */

import stackoverflow.project.policyretrieval.entity.HistoryEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

public interface HistoryService {
    // 以列表的形式返回数据, 其中只包含用户的搜索记录policyId
    // 排列方式以时间排序, 但是并不包含在返回值中
    public ResponseUtil<List<String>> searchHistoryByUid(String uid);

    //当前端用户点击详情页时, 可以使用该接口生成一条历史记录
    //
    public ResponseUtil<String> createHistory(String uid, String policy_id);
}
