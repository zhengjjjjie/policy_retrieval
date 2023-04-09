package stackoverflow.project.policyretrieval.view;


/*
    查询方法
 */
import lombok.Data;

import java.util.List;

@Data
public class Query {
    // 查询类, 可以设置需要包含的字段和不包含的关键词
    private List<String> Titles;
    private List<String> NotTitles;
    private List<String> PolicyType;
    private List<String> NotPolicyType;
}
