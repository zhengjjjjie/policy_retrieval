package stackoverflow.project.policyretrieval.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "administrator")
public class AdministratorEntity extends UserEntity{

}
