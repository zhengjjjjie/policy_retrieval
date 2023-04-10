package stackoverflow.project.policyretrieval.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.repository.EnquirerRepository;
import stackoverflow.project.policyretrieval.repository.PolicyRepository;
import stackoverflow.project.policyretrieval.service.EnquirerService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.EnquirerView;
import stackoverflow.project.policyretrieval.view.LoginView;

import java.util.List;
import java.util.Objects;

@Service
public class EnquirerServiceImpl implements EnquirerService {
    @Autowired
    private EnquirerRepository enquirerRepository;
    @Autowired
    private PolicyRepository policyRepository;
    @Override
    public ResponseUtil<String> login(LoginView loginView) {
        EnquirerEntity enquirer = enquirerRepository.findEnquirerEntityByUsername(loginView.getUsername());
        if (!Objects.equals(enquirer.getPassword(), loginView.getPassword())) {
            return ResponseUtil.failMessage("登录失败！");
        }
        return ResponseUtil.successMessage("登录成功！");
    }

    @Override
    public ResponseUtil<String> add(EnquirerEntity enquirerEntity) {
        enquirerRepository.save(enquirerEntity);
        return ResponseUtil.successMessage("添加成功!");
    }

    @Override
    public ResponseUtil<String> delete(int id) {
        enquirerRepository.deleteById(id);
        return ResponseUtil.successMessage("删除成功!");
    }

    @Override
    public ResponseUtil<String> update(EnquirerEntity enquirerEntity) {
        EnquirerEntity enquirer = enquirerRepository.getReferenceById(enquirerEntity.getId());
        enquirer.setAge(enquirerEntity.getAge());
        enquirer.setGender(enquirerEntity.getGender());
        enquirer.setUsername(enquirerEntity.getUsername());
        enquirer.setNickname(enquirerEntity.getNickname());
        enquirer.setIdentity(enquirerEntity.getIdentity());
        enquirer.setCollection(enquirerEntity.getCollection());
        enquirer.setPoliticsStatus(enquirerEntity.getPoliticsStatus());
        enquirer.setTags(enquirerEntity.getTags());
//        enquirer.setHistory(enquirerEntity.getHistory());
        enquirer.setPassword(enquirerEntity.getPassword());
        enquirerRepository.save(enquirer);
        return ResponseUtil.successMessage("更新成功!");
    }

    @Override
    public ResponseUtil<Page<EnquirerEntity>> getAll(Pageable pageable) {
        return ResponseUtil.success(enquirerRepository.findAll(pageable));
    }

    @Override
    public ResponseUtil<EnquirerView> getByUsername(String username) {
        EnquirerEntity enquirerEntity = enquirerRepository.findEnquirerEntityByUsername(username);
        EnquirerView enquirerView = new EnquirerView();
        enquirerView.setUsername(enquirerEntity.getUsername());
        enquirerView.setPassword(enquirerEntity.getPassword());
        enquirerView.setNickname(enquirerEntity.getPassword());
        enquirerView.setAge(enquirerEntity.getAge());
        enquirerView.setGender(enquirerEntity.getGender());
        enquirerView.setPoliticsStatus(enquirerEntity.getPoliticsStatus());
        return ResponseUtil.success(enquirerView);
    }

    @Override
    public ResponseUtil<String> addHistory(int enquirerId, int policyId) {
        EnquirerEntity enquirer = enquirerRepository.getReferenceById(enquirerId);
//        List<PolicyEntity> history = enquirer.getHistory();
        PolicyEntity policy = policyRepository.getReferenceById(policyId);
//        history.add(policy);
//        enquirer.setHistory(history);
        enquirerRepository.save(enquirer);
        return ResponseUtil.successMessage("添加成功!");
    }

    @Override
    public ResponseUtil<String> addCollection(int enquirerId, int policyId) {
        EnquirerEntity enquirer = enquirerRepository.getReferenceById(enquirerId);
        List<PolicyEntity> collection = enquirer.getCollection();
        PolicyEntity policy = policyRepository.getReferenceById(policyId);
        collection.add(policy);
//        enquirer.setHistory(collection);
        enquirerRepository.save(enquirer);
        return ResponseUtil.successMessage("添加成功!");
    }

    @Override
    public ResponseUtil<List<PolicyEntity>> getHistory(String username) {
        return ResponseUtil.success(enquirerRepository.findHistoryByUsername(username));
    }

    @Override
    public ResponseUtil<List<PolicyEntity>> getCollection(String username) {
        return ResponseUtil.success(enquirerRepository.findCollectionByUsername(username));
    }
}
