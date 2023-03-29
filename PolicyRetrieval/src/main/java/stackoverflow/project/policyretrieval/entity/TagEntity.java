package stackoverflow.project.policyretrieval.entity;

import javax.persistence.*;

@Entity
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

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getGroupBelong() {
        return groupBelong;
    }

    public void setGroupBelong(Integer groupBelong) {
        this.groupBelong = groupBelong;
    }
}
