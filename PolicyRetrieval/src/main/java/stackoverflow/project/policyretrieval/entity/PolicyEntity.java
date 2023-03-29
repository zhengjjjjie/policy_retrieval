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
}
