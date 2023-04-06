package stackoverflow.project.policyretrieval.service;

import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@Service
public class EnquirerServiceImpl implements EnquirerService{

    @Override
    public ResponseUtil<String> login(EnquirerEntity enquirerEntity) {
        return null;
    }

    @Override
    public ResponseUtil<String> add(EnquirerEntity enquirerEntity) {
        return null;
    }

    @Override
    public ResponseUtil<String> delete(int id) {
        return null;
    }

    @Override
    public ResponseUtil<String> update(EnquirerEntity enquirerEntity) {
        return null;
    }

    @Override
    public ResponseUtil<List<EnquirerEntity>> getAll() {
        return null;
    }
}
