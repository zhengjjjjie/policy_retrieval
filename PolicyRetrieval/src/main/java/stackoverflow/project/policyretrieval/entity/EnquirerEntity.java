package stackoverflow.project.policyretrieval.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "enquirer")
public class EnquirerEntity extends UserEntity{
    @Basic
    @Column(name = "nickname")
    private int nickname;

    @Basic
    @Column(name = "gender")
    private String gender;

    @Basic
    @Column(name = "age")
    private int age;

    @Basic
    @Column(name = "politics_status")
    private int politicsStatus;

    @ManyToMany(targetEntity = PolicyEntity.class)
    @JoinTable(name = "collection",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "policy_id", referencedColumnName = "policy_id")})
    private List<PolicyEntity> collection;

    @ManyToMany(targetEntity = PolicyEntity.class)
    @JoinTable(name = "history",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "policy_id", referencedColumnName = "policy_id")})
    private List<PolicyEntity> history;

    @ManyToMany(targetEntity = TagEntity.class)
    @JoinTable(name = "user_tags",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private List<PolicyEntity> tags;
}
