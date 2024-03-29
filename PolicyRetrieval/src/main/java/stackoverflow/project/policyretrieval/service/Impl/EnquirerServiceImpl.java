package stackoverflow.project.policyretrieval.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.EnquirerEntity;
import stackoverflow.project.policyretrieval.entity.HistoryEntity;
import stackoverflow.project.policyretrieval.entity.PolicyEntity;
import stackoverflow.project.policyretrieval.repository.EnquirerRepository;
import stackoverflow.project.policyretrieval.repository.PolicyRepository;
import stackoverflow.project.policyretrieval.service.EnquirerService;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.EnquirerRequestView;
import stackoverflow.project.policyretrieval.view.LoginRequestView;
import stackoverflow.project.policyretrieval.view.PolicyResultView;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static stackoverflow.project.policyretrieval.util.ConvertPageUtil.convertPage;
@Service
public class EnquirerServiceImpl implements EnquirerService {
    @Autowired
    private EnquirerRepository enquirerRepository;
    @Autowired
    private PolicyRepository policyRepository;
    @Override
    public ResponseUtil<String> login(LoginRequestView loginRequestView) {
        EnquirerEntity enquirer = enquirerRepository.findEnquirerEntityByUsername(loginRequestView.getUsername());
        if (!Objects.equals(enquirer.getPassword(), loginRequestView.getPassword())) {
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
        enquirer.setRole(enquirerEntity.getRole());
//        enquirer.setCollection(enquirerEntity.getCollection());
        enquirer.setPoliticsStatus(enquirerEntity.getPoliticsStatus());
//        enquirer.setTags(enquirerEntity.getTags());
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
    public ResponseUtil<EnquirerRequestView> getByUsername(String username) {
        EnquirerEntity enquirerEntity = enquirerRepository.findEnquirerEntityByUsername(username);
        EnquirerRequestView enquirerView = new EnquirerRequestView();
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
    public ResponseUtil<?> getInfo(String username) {
        if(enquirerRepository.findEnquirerEntityByUsername(username) == null){
            return ResponseUtil.failMessage("不存在该用户！");
        }
        EnquirerEntity enquirerEntity = enquirerRepository.findEnquirerEntityByUsername(username);
        EnquirerRequestView enquirerRequestView = new EnquirerRequestView();
        enquirerRequestView.setNickname(enquirerEntity.getNickname());
        enquirerRequestView.setUsername(enquirerEntity.getUsername());
        enquirerRequestView.setAge(enquirerEntity.getAge());
        enquirerRequestView.setGender(enquirerEntity.getGender());
        enquirerRequestView.setPoliticsStatus(enquirerEntity.getPoliticsStatus());
        return ResponseUtil.success(enquirerRequestView);
    }
}
