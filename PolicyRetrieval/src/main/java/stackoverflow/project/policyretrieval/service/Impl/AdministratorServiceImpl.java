package stackoverflow.project.policyretrieval.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.AdministratorEntity;
import stackoverflow.project.policyretrieval.repository.AdministratorRepository;
import stackoverflow.project.policyretrieval.service.AdministratorService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.LoginRequestView;

import java.util.List;
import java.util.Objects;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;
    @Override
    public ResponseUtil<String> login(LoginRequestView loginRequestView) {
        AdministratorEntity administrator = administratorRepository.findAdministratorEntitiesByUsername(loginRequestView.getUsername());
        if (!Objects.equals(administrator.getPassword(), loginRequestView.getPassword())) {
            return ResponseUtil.failMessage("登录失败！");
        }
        return ResponseUtil.successMessage("登录成功！");
    }
    @Override
    public ResponseUtil<String> add(AdministratorEntity administratorEntity) {
        administratorRepository.save(administratorEntity);
        return ResponseUtil.successMessage("添加成功!");
    }

    @Override
    public ResponseUtil<String> delete(int id) {
        administratorRepository.deleteById(id);
        return ResponseUtil.successMessage("删除成功！");
    }

    @Override
    public ResponseUtil<String> update(AdministratorEntity administratorEntity) {
        AdministratorEntity administrator = administratorRepository.getReferenceById(administratorEntity.getId());
        administrator.setNickname(administratorEntity.getNickname());
        administrator.setRole(administratorEntity.getRole());
        administrator.setUsername(administratorEntity.getUsername());
        administrator.setPassword(administratorEntity.getPassword());
        administratorRepository.save(administrator);
        return ResponseUtil.successMessage("更新成功");
    }

    @Override
    public ResponseUtil<List<AdministratorEntity>> getAll() {
        return ResponseUtil.success(administratorRepository.findAll());
    }
}
