package junit.test;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruanko.service.UserService;



public class TestService {
	private static UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
			userService = (UserService)act.getBean("userService");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void saveDelet(){
		this.userService.savDelet();	
	}
	
	
}
