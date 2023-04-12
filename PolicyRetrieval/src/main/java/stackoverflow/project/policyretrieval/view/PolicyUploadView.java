package stackoverflow.project.policyretrieval.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
public class PolicyUploadView {
    private String policyId;
    private String policyTitle;
    private String policyGrade;
    private String pubAgencyId;
    private String pubAgency;
    private String pubAgencyFullName;
    private String pubNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd", timezone ="GMT+8")
    private Date pubTime;
    private String policyType;
    private String policyBody;
    private String province;
    private String city;
    private String policySource;
}
