package stackoverflow.project.policyretrieval.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.EnquirerRequestView;
import stackoverflow.project.policyretrieval.view.LoginRequestView;
import stackoverflow.project.policyretrieval.view.PolicyResultView;

import java.util.List;

public interface EnquirerService {
    ResponseUtil<String> login(LoginRequestView loginRequestView);
    ResponseUtil<String> add(EnquirerEntity enquirerEntity);

    ResponseUtil<String> delete(int id);

    ResponseUtil<String> update(EnquirerEntity enquirerEntity);

    ResponseUtil<Page<EnquirerEntity>> getAll(Pageable pageable);

    ResponseUtil<EnquirerRequestView> getByUsername(String username);

    ResponseUtil<String> addHistory(int enquirerId, int recordId);

//    ResponseUtil<String> addCollection(int enquirerId, int policyId);

//    ResponseUtil<List<PolicyResultView>> getCollection(String username);

    ResponseUtil<?> getInfo(String username);

}
