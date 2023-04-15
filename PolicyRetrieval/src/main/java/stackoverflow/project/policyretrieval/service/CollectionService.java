package stackoverflow.project.policyretrieval.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stackoverflow.project.policyretrieval.entity.CollectionEntity;
import stackoverflow.project.policyretrieval.repository.CollectionRepository;
import stackoverflow.project.policyretrieval.repository.ESPolicyRepository;
import stackoverflow.project.policyretrieval.repository.EnquirerRepository;
import stackoverflow.project.policyretrieval.util.ResponseUtil;
import stackoverflow.project.policyretrieval.view.CollectionView;

import static stackoverflow.project.policyretrieval.util.ConvertPageUtil.convertPage;

@Service
public class CollectionService{
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private ESPolicyRepository esPolicyRepository;

    @Autowired
    private EnquirerRepository enquirerRepository;


    public ResponseUtil<Page<CollectionView>> searchCollectionByUsername(String username, Pageable page) {
        Page<CollectionEntity> collectionEntities = collectionRepository.findByUserName(username,page);
        Page<CollectionView> collectionViews = convertPage(collectionEntities,CollectionView.class);
        return ResponseUtil.success(collectionViews);
    }

    public ResponseUtil<?> addCollection(String username, String policyId) {
        CollectionEntity collection = new CollectionEntity();
        if (!esPolicyRepository.existsByPolicyId(policyId)){
            return ResponseUtil.failMessage("政策不存在");
        }
        if (!enquirerRepository.existsByUsername(username)) {
            return ResponseUtil.failMessage("用户不存在");
        }
        collection.setPolicyId(policyId);
        collection.setUserName(username);
        collectionRepository.save(collection);

        return ResponseUtil.success("收藏成功");
    }
}
