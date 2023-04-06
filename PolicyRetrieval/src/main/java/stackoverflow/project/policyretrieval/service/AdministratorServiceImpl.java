package stackoverflow.project.policyretrieval.service;

import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.AdministratorEntity;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService{
    @Override
    public ResponseUtil<String> login(AdministratorEntity administratorEntity) {
        return null;
    }

    @Override
    public ResponseUtil<String> add(AdministratorEntity administratorEntity) {
        return null;
    }

    @Override
    public ResponseUtil<String> delete(int id) {
        return null;
    }

    @Override
    public ResponseUtil<String> update(AdministratorEntity administratorEntity) {
        return null;
    }

    @Override
    public ResponseUtil<List<AdministratorEntity>> getAll() {
        return null;
    }
}
