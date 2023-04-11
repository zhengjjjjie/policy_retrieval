package stackoverflow.project.policyretrieval.view;

import lombok.Data;


/*
     description: 搜索简略结果
 */
@Data
public class PolicyResultView{
     private String policyId;
     private String policyTitle;
     private String pubTime;

     public PolicyResultView(){}
     public PolicyResultView(String policyId, String policyTitle, String pubTime) {
          this.policyTitle = policyTitle;
          this.policyId = policyId;
          this.pubTime = pubTime;
     }
}
