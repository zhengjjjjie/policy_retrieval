package stackoverflow.project.policyretrieval;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import stackoverflow.project.policyretrieval.entity.ESPolicyEntity;
import stackoverflow.project.policyretrieval.repository.ESPolicyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class PolicyRetrievalApplicationTests {
	@Autowired
	private ESPolicyRepository esPolicyRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void searchTest(){
		Pageable page = PageRequest.of(1,15);
		System.out.println(esPolicyRepository.findByPolicyTitle(page,"山东"));
	}


}
