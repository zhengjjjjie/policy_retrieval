package stackoverflow.project.policyretrieval;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
		System.out.println(esPolicyRepository.findByPolicyTitle("山东"));
	}

}
