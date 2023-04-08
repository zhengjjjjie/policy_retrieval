package stackoverflow.project.policyretrieval.service;

import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.view.EnquirerView;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.LoginView;

import java.util.List;

public interface EnquirerService {
    ResponseUtil<String> login(LoginView loginView);
    ResponseUtil<String> add(EnquirerEntity enquirerEntity);

    ResponseUtil<String> delete(int id);

    ResponseUtil<String> update(EnquirerEntity enquirerEntity);

    ResponseUtil<List<EnquirerEntity>> getAll();

    ResponseUtil<EnquirerView> getByUsername(String username);

    ResponseUtil<String> addHistory(int enquirerId, int recordId);

    ResponseUtil<String> addCollection(int enquirerId, int policyId);

    ResponseUtil<List<PolicyEntity>> getHistory(String username);

    ResponseUtil<List<PolicyEntity>> getCollection(String username);
}
