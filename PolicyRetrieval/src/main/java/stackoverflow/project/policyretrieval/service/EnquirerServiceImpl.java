package stackoverflow.project.policyretrieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.repository.EnquirerRepository;
import stackoverflow.project.policyretrieval.repository.PolicyRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;

import java.util.List;

@Service
public class EnquirerServiceImpl implements EnquirerService{
    @Autowired
    private EnquirerRepository enquirerRepository;
    @Autowired
    private PolicyRepository policyRepository;
    @Override
    public ResponseUtil<String> login(EnquirerEntity enquirerEntity) {
        return null;
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
        enquirer.setHistory(enquirerEntity.getHistory());
        enquirer.setPassword(enquirerEntity.getPassword());
        enquirerRepository.save(enquirer);
        return ResponseUtil.successMessage("更新成功!");
    }

    @Override
    public ResponseUtil<List<EnquirerEntity>> getAll() {
        return ResponseUtil.success(enquirerRepository.findAll());
    }

    @Override
    public ResponseUtil<EnquirerEntity> getByUsername(String username) {
        return ResponseUtil.success(enquirerRepository.findEnquirerEntityByUsername(username));
    }

    @Override
    public ResponseUtil<String> addHistory(int enquirerId, int policyId) {
        EnquirerEntity enquirer = enquirerRepository.getReferenceById(enquirerId);
        List<PolicyEntity> history = enquirer.getHistory();
        PolicyEntity policy = policyRepository.getReferenceById(policyId);
        history.add(policy);
        enquirer.setHistory(history);
        enquirerRepository.save(enquirer);
        return ResponseUtil.successMessage("添加成功!");
    }

    @Override
    public ResponseUtil<String> addCollection(int enquirerId, int policyId) {
        EnquirerEntity enquirer = enquirerRepository.getReferenceById(enquirerId);
        List<PolicyEntity> collection = enquirer.getCollection();
        PolicyEntity policy = policyRepository.getReferenceById(policyId);
        collection.add(policy);
        enquirer.setHistory(collection);
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
