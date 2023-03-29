package stackoverflow.project.policyretrieval.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "policy")
public class PolicyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "policy_id", nullable = false)
    private int policyId;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "content")
    private String content;

    @Basic
    @Column(name = "link")
    private String link;

    @Basic
    @Column(name = "date")
    private Date date;

    @ManyToMany(targetEntity = TagEntity.class)
    @JoinTable(name = "policy_tags",
            joinColumns = {@JoinColumn(name = "policy_id", referencedColumnName = "policy_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private List<PolicyEntity> tags;

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<PolicyEntity> getTags() {
        return tags;
    }

    public void setTags(List<PolicyEntity> tags) {
        this.tags = tags;
    }
}
