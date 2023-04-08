package stackoverflow.project.policyretrieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.AdministratorEntity;
import stackoverflow.project.policyretrieval.repository.AdministratorRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.LoginView;

import java.util.List;
import java.util.Objects;

@Service
public class AdministratorServiceImpl implements AdministratorService{
    @Autowired
    private AdministratorRepository administratorRepository;
    @Override
    public ResponseUtil<String> login(LoginView loginView) {
        AdministratorEntity administrator = administratorRepository.findAdministratorEntitiesByUsername(loginView.getUsername());
        if (!Objects.equals(administrator.getPassword(), loginView.getPassword())) {
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
        administrator.setIdentity(administratorEntity.getIdentity());
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
