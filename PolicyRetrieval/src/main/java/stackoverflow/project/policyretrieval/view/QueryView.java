package stackoverflow.project.policyretrieval.view;


/*
    查询方法
 */
import lombok.Data;

import java.util.List;

@Data
public class QueryView {
    // 查询类, 可以设置需要包含的字段和不包含的关键词
    private List<String> Titles;
    private List<String> NotTitles;
    private List<String> PolicyType;
    private List<String> NotPolicyType;

    public String getTitles_str(){
        return Titles.toString().replace("[", "").replace("]", "").replace(",","");
    }
    public String getNoTitles_str() {
        return NotTitles.toString().replace("[", "").replace("]", "").replace(",","");
    }
    public String getPolicyType_str() {
        return PolicyType.toString().replace("[", "").replace("]", "").replace(",","");
    }
    public String getNotePolicyType_str() {
        return NotPolicyType.toString().replace("[", "").replace("]", "").replace(",","");
    }

    //通用匹配符
}
