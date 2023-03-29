package stackoverflow.project.policyretrieval.entity;

import javax.persistence.*;

@Entity
@Table(name = "administrator")
public class AdministratorEntity extends UserEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "administrator_id", nullable = false)
    private int id;
}
