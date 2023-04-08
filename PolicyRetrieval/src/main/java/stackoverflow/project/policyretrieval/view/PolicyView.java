package stackoverflow.project.policyretrieval.view;

import com.fasterxml.jackson.annotation.JsonView;

public class PolicyView {

    @JsonView(PolicyView.Hot.class)
    private String policyTitle;

    @JsonView({PolicyView.Content.class})
    private String PubAgency;

    @JsonView({PolicyView.Content.class})
    private String pubAgencyFullName;

    @JsonView({PolicyView.Result.class})
    private String pubTime;
    @JsonView({PolicyView.Content.class})
    private String policyBody;

    @JsonView({PolicyView.Content.class})
    private String province;

    @JsonView({PolicyView.Content.class})
    private String city;

    @JsonView({PolicyView.Content.class})
    private String policySource;

    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public String getPubAgency() {
        return PubAgency;
    }

    public void setPubAgency(String pubAgency) {
        PubAgency = pubAgency;
    }

    public String getPubAgencyFullName() {
        return pubAgencyFullName;
    }

    public void setPubAgencyFullName(String pubAgencyFullName) {
        this.pubAgencyFullName = pubAgencyFullName;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getPolicyBody() {
        return policyBody;
    }

    public void setPolicyBody(String policyBody) {
        this.policyBody = policyBody;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPolicySource() {
        return policySource;
    }

    public void setPolicySource(String policySource) {
        this.policySource = policySource;
    }

    public static class Hot{}
    public static class Result extends Hot{}
    public static class Content extends Result{}
}
