package stackoverflow.project.policyretrieval.view;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
