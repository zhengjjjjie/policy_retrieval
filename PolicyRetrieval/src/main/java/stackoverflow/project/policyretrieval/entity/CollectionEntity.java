package stackoverflow.project.policyretrieval.entity;


import lombok.Data;
import stackoverflow.project.policyretrieval.entity.combine.CollectionId;
import stackoverflow.project.policyretrieval.entity.combine.HistoryId;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@IdClass(CollectionId.class)
@Table(name = "collection")
public class CollectionEntity {
    @Id
    @JoinColumn(name = "username", referencedColumnName="username",nullable = false)
    private String userName;

    @Id
    @JoinColumn(name = "policyId",referencedColumnName="POLICYID", nullable = false)
    private String policyId;

    @Id
    @JoinColumn(name = "policytitle")
    private String policyTitle;

    public CollectionEntity(){}

}
