package stackoverflow.project.policyretrieval.entity;

import javax.persistence.*;

@Entity
@Table(name = "administrator")
public class AdministratorEntity extends UserEntity{
    @Basic
    @Column(name = "nickname")
    private int nickname;
}
