package stackoverflow.project.policyretrieval.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;


/*
     description: 搜索简略结果
 */
@Data
public class PolicyResultView{
     private String policyId;
     private String policyTitle;
     @JsonFormat(pattern = "yyyy-MM-dd")
     private Date pubTime;

     public PolicyResultView(){}
     public PolicyResultView(String policyId, String policyTitle, Date pubTime) {
          this.policyTitle = policyTitle;
          this.policyId = policyId;
          this.pubTime = pubTime;
     }
}
