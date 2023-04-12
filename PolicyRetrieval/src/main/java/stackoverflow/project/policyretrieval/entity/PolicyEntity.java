package stackoverflow.project.policyretrieval.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "POLICY")
public class PolicyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer Id;

    @Basic
    @Column(name = "POLICYID",columnDefinition = "text")
    private String policyId;

    @Basic
    @Column(name = "POLICYTITLE",columnDefinition = "text")
    private String policyTitle;

    @Basic
    @Column(name = "POLICYGRADE",columnDefinition = "text")
    private String policyGrade;

    @Basic
    @Column(name = "PUBAGENCYID",columnDefinition = "text")
    private String pubAgencyId;

    @Basic
    @Column(name = "PUBAGENCY",columnDefinition = "text")
    private String PubAgency;

    @Basic
    @Column(name = "PUBAGENCYFULLNAME",columnDefinition = "text")
    private String pubAgencyFullName;

    @Basic
    @Column(name = "PUBNUMBER",columnDefinition = "text")
    private String pubNumber;

    @Basic
    @Column(name = "PUBTIME",columnDefinition = "datetime")
    private Date pubTime;

    @Basic
    @Column(name = "POLICYTYPE",columnDefinition = "text")
    private String policyType;

    @Basic
    @Column(name = "POLICYBODY",columnDefinition = "longtext")
    private String policyBody;

    @Basic
    @Column(name = "PROVINCE",columnDefinition = "text")
    private String province;

    @Basic
    @Column(name = "CITY",columnDefinition = "text")
    private String city;

    @Basic
    @Column(name = "POLICYSOURCE",columnDefinition = "text")
    private String policySource;

    @Basic
    @Column(name = "UPDATEDATE",columnDefinition = "datetime")
    private Date updateDate;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public String getPolicyGrade() {
        return policyGrade;
    }

    public void setPolicyGrade(String policyGrade) {
        this.policyGrade = policyGrade;
    }

    public String getPubAgencyId() {
        return pubAgencyId;
    }

    public void setPubAgencyId(String pubAgencyId) {
        this.pubAgencyId = pubAgencyId;
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

    public String getPubNumber() {
        return pubNumber;
    }

    public void setPubNumber(String pubNumber) {
        this.pubNumber = pubNumber;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
