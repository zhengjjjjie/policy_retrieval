package stackoverflow.project.policyretrieval.service;

import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

public interface EnquirerService {
    ResponseUtil<String> login(EnquirerEntity enquirerEntity);
    ResponseUtil<String> add(EnquirerEntity enquirerEntity);

    ResponseUtil<String> delete(int id);

    ResponseUtil<String> update(EnquirerEntity enquirerEntity);

    ResponseUtil<List<EnquirerEntity>> getAll();
}
