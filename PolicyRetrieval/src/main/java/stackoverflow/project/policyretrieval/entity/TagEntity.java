package stackoverflow.project.policyretrieval.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tag")
public class TagEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tag_id", nullable = false)
    private int tagId;
    @Basic
    @Column(name = "tag_name", nullable = true, length = 50)
    private String tagName;
    @Basic
    @Column(name = "group_belong", nullable = true)
    private int groupBelong;
}
