package stackoverflow.project.policyretrieval.view;

import lombok.Data;

@Data
public class PolicyResultView{
     private String policyId;
     private String policyTitle;
     private String pubTime;

     public PolicyResultView(String policyId, String policyTitle, String pubTime) {
          this.policyTitle = policyTitle;
          this.policyId = policyId;
          this.pubTime = pubTime;
     }
}
