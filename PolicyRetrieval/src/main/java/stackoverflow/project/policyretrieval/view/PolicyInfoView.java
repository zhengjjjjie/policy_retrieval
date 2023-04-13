package stackoverflow.project.policyretrieval.view;

/*
    description: 详情页
 */
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
public class PolicyInfoView {
    private String policyId;
    private String policyTitle;
    @Field(name = "UPDATEDATE",type = FieldType.Date,format = {},
            pattern = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd'T'HH:mm:ss'+08:00' || strict_date_optional_time || epoch_millis")
    private Date pubTime;
    private String pubAgencyFullName;
    private String policyType;
    private String policyBody;
    private String province;
    private String city;
    private String policySource;
}
