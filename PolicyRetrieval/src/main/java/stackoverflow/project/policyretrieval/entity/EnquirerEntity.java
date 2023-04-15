package stackoverflow.project.policyretrieval.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
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
//    @ManyToMany(targetEntity = PolicyEntity.class,fetch = FetchType.EAGER)
//    @JsonIgnore
//    @JoinTable(name = "collection",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "POLICYID", referencedColumnName = "POLICYID")})
//    private List<PolicyEntity> collection;

//    @ManyToMany(targetEntity = TagEntity.class,fetch = FetchType.EAGER)
//    @JsonIgnore
//    @Fetch(value = FetchMode.SUBSELECT)
//    @JoinTable(name = "user_tags",
//            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")})
//    private List<PolicyEntity> tags;
//    private String tags;

}
