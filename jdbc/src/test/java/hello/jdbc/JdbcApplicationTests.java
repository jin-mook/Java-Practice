package hello.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class JdbcApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void context() {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println("bean : " + beanDefinitionName);
		}
	}

	@Test
	void contextLoads() {
	}

}
