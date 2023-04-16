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
    private List<String> Bodies;
    private List<String> NotBodies;

    public String getTitles_str(){
        return Titles.toString().replace("[", "").replace("]", "").replace(",","");
    }
    public String getNoTitles_str() {
        if (this.Titles == null) {
            return "";
        }else {
            return NotTitles.toString().replace("[", "").replace("]", "").replace(",","");
        }
    }
    public String getPolicyType_str() {
        if (this.PolicyType == null) {
            return "";
        }
        return PolicyType.toString().replace("[", "").replace("]", "").replace(",","");
    }
    public String getNotPolicyType_str() {
        if (this.NotPolicyType == null) {
            return "";
        }
        return NotPolicyType.toString().replace("[", "").replace("]", "").replace(",","");
    }

    public String getPolicyBodies_str() {
        if (this.Bodies  == null) {
            return "";
        }
        return Bodies.toString().replace("[", "").replace("]", "").replace(",","");
    }
    public String getNotPolicyBodies_str() {
        if (this.NotBodies == null) {
            return "";
        }
        return NotBodies.toString().replace("[", "").replace("]", "").replace(",","");
    }
    //通用匹配符
}
