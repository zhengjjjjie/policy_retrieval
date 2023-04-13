package stackoverflow.project.policyretrieval.view;

import lombok.Data;

import java.util.Date;


/*
     description: 搜索简略结果
 */
@Data
public class PolicyResultView{
     private String policyId;
     private String policyTitle;
     private Date pubTime;

     public PolicyResultView(){}
     public PolicyResultView(String policyId, String policyTitle, Date pubTime) {
          this.policyTitle = policyTitle;
          this.policyId = policyId;
          this.pubTime = pubTime;
     }
}
