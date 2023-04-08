package stackoverflow.project.policyretrieval.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "POLICY")
public class PolicyEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
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
    @Column(name = "PUBTIME",columnDefinition = "text")
    private String pubTime;

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
    @Column(name = "UPDATEDATE",columnDefinition = "text")
    private String updateDate;

//    @Basic
//    @Column(name = "link")
//    private String link;
//
//    @Basic
//    @Column(name = "date")
//    private Date date;
//
//    @ManyToMany(targetEntity = TagEntity.class)
//    @JoinTable(name = "policy_tags",
//            joinColumns = {@JoinColumn(name = "policy_id", referencedColumnName = "policy_id")},
//            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
//    private List<PolicyEntity> tags;

//    public String getLink() {
//        return link;
//    }
//
//    public void setLink(String link) {
//        this.link = link;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//    public List<PolicyEntity> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<PolicyEntity> tags) {
//        this.tags = tags;
//    }

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

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
