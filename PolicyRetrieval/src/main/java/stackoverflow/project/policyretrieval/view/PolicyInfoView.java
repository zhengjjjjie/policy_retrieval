package stackoverflow.project.policyretrieval.view;

import lombok.Data;

@Data
public class PolicyInfoView {
    private String policyId;
    private String policyTitle;
    private String pubTime;
    private String pubAgencyFullName;
    private String policyType;
    private String policyBody;
    private String province;
    private String city;
    public String policySource;
}
