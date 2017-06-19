package junit.test;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ruanko.dao.BaseDao;
import com.ruanko.entity.User;



public class TestDao {
	private static BaseDao baseDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext act = new ClassPathXmlApplicationContext("beans.xml");
			baseDao = (BaseDao)act.getBean("baseDao");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findUser(){
		List userList=this.baseDao.findObjectByHql("from User", null);
		for (Object user : userList) {
			System.out.println(((User)user).getUserName());
		}
		
	}
	
	@Test
	public void saveUser(){
		User user = new User();
		user.setUserName("lisi4");
		this.baseDao.saveObject(user);
		
	}
	
	@Test
	public void deleteUser(){
		try {
			this.baseDao.deleteObject(User.class,"zhangsan");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void FindUserByID(){
		User user= (User) this.baseDao.getObjectByID(User.class, "lisi");
		System.out.println(user.getUserName());
	}
	
	@Test
	public void updateUser(){
		User user= (User) this.baseDao.getObjectByID(User.class, "lisi");
		user.setPassWord("123");
		this.baseDao.updateObject(user);
	}
	
	@Test
	public void findUserByFenye(){
		String hql="from User";
		int offset=0;
		int line=3;
		List userList=this.baseDao.findObjByFenye(hql, offset, line);
		
		for (Object user : userList) {
			System.out.println(((User)user).getUserName());
		}
		
	}
	
}
