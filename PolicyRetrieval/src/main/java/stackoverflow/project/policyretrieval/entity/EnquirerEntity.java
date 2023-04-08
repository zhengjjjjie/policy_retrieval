package stackoverflow.project.policyretrieval.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "enquirer")
public class EnquirerEntity extends UserEntity{

    @Basic
    @Column(name = "gender")
    private String gender;

    @Basic
    @Column(name = "age")
    private int age;

    @Basic
    @Column(name = "politics_status")
    private String politicsStatus;
    @ManyToMany(targetEntity = PolicyEntity.class)
    @JoinTable(name = "collection",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "POLICYID", referencedColumnName = "POLICYID")})
    private List<PolicyEntity> collection;

    @ManyToMany(targetEntity = PolicyEntity.class)
    @JoinTable(name = "history",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "POLICYID", referencedColumnName = "POLICYID")})
    private List<PolicyEntity> history;

    @ManyToMany(targetEntity = TagEntity.class)
    @JoinTable(name = "user_tags",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
    private List<PolicyEntity> tags;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public List<PolicyEntity> getCollection() {
        return collection;
    }

    public void setCollection(List<PolicyEntity> collection) {
        this.collection = collection;
    }

    public List<PolicyEntity> getHistory() {
        return history;
    }

    public void setHistory(List<PolicyEntity> history) {
        this.history = history;
    }

    public List<PolicyEntity> getTags() {
        return tags;
    }

    public void setTags(List<PolicyEntity> tags) {
        this.tags = tags;
    }
}
