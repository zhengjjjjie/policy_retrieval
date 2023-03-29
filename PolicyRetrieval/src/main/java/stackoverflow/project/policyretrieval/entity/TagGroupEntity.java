package stackoverflow.project.policyretrieval.entity;

import javax.persistence.*;

@Entity
@Table(name = "tag_group")
public class TagGroupEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tag_group_id", nullable = false)
    private int tagGroupId;
    @Basic
    @Column(name = "tag_group_name", nullable = true, length = 50)
    private String tagGroupName;

    public int getTagGroupId() {
        return tagGroupId;
    }

    public void setTagGroupId(int tagGroupId) {
        this.tagGroupId = tagGroupId;
    }

    public String getTagGroupName() {
        return tagGroupName;
    }

    public void setTagGroupName(String tagGroupName) {
        this.tagGroupName = tagGroupName;
    }
}

