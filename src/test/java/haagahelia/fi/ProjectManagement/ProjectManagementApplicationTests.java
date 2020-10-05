package haagahelia.fi.ProjectManagement;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import haagahelia.fi.ProjectManagement.contoller.ProjectController;

// Testing Controllers
@RunWith(SpringRunner.class)
@SpringBootTest
class ProjectManagementApplicationTests {
	
	@Autowired
	private ProjectController pController;
	
	@Test
	void projectControllerLoads() {
		assertThat(pController).isNotNull();
	}

}
