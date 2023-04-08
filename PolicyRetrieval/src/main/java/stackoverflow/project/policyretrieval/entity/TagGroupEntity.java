package stackoverflow.project.policyretrieval.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tag_group")
public class TagGroupEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tag_group_id", nullable = false)
    private int tagGroupId;
    @Basic
    @Column(name = "tag_group_name", nullable = true, length = 50)
    private String tagGroupName;
}

