package stackoverflow.project.policyretrieval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stackoverflow.project.policyretrieval.entity.AdministratorEntity;

public interface AdministratorRepository extends JpaRepository<AdministratorEntity, Integer> {
    AdministratorEntity findAdministratorEntitiesByUsername(String username);
}
