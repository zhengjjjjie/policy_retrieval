package stackoverflow.project.policyretrieval.service;

import stackoverflow.project.policyretrieval.entity.AdministratorEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

public interface AdministratorService {
    ResponseUtil<String> add(AdministratorEntity administratorEntity);

    ResponseUtil<String> delete(int id);

    ResponseUtil<String> update(AdministratorEntity administratorEntity);

    ResponseUtil<List<AdministratorEntity>> getAll();
}
