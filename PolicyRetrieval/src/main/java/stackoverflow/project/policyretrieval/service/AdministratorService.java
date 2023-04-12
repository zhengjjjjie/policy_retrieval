package stackoverflow.project.policyretrieval.service;

import stackoverflow.project.policyretrieval.entity.AdministratorEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.LoginRequestView;

import java.util.List;

public interface AdministratorService {
    ResponseUtil<String> login(LoginRequestView loginRequestView);
    ResponseUtil<String> add(AdministratorEntity administratorEntity);

    ResponseUtil<String> delete(int id);

    ResponseUtil<String> update(AdministratorEntity administratorEntity);

    ResponseUtil<List<AdministratorEntity>> getAll();
}
