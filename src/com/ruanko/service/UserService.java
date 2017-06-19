package com.ruanko.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ruanko.dao.BaseDao;
import com.ruanko.entity.User;
import com.ruanko.uitl.PageModel;

@Service ("userService")
@Transactional //配置事务
public class UserService {

	@Resource 
	private BaseDao baseDao; //给UserService注入BaseDao
	/**
	 * 用户登录
	 * @param name
	 * @param pass
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List userLogin(String name,String pass){
		String hql="from User as u where u.userName=? and u.passWord=?";
		Object[] values= new Object[]{name,pass};
		List list = this.baseDao.findObjectByHql(hql, values);
		return list;
	}
	/**
	 * 查找用户
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public PageModel findUser(int offset,int line){
		PageModel model = new PageModel();
		String hql="from User";
		String countHql="select count(*) from User";
		int allLine = this.getAllLine(countHql);
		List<?> userList = this.baseDao.findObjByFenye(hql, offset, line);
		model.setEntityList(userList);
		model.setNextLine(offset+line);
		model.setOnLine(offset-line);
		model.setAllLine(allLine);
		return model;
	}
	
	/**
	 * 查询所有行数
	 * @param hql
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public int getAllLine(String hql){
		return Integer.parseInt(this.baseDao.singerResult(hql).toString());
		
	}
	/**
	 * 保存用户数据
	 * @param user
	 */
	public void addUser(User user){
		this.baseDao.saveObject(user);
	}
	/**
	 * 根据ID查询数据
	 * @param id
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public User getUserByID(int id){
		return (User)this.baseDao.getObjectByID(User.class, id);
	}
	/**
	 * 根据ID删除用户
	 * @param id
	 */
	public void deleteUser(int id ){
		try {
			this.baseDao.deleteObject(User.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新数据
	 * @param user
	 */
	public void updateUser(User user){
		this.baseDao.updateObject(user);
	}
	
	
	
	/**
	 * 测试事务
	 * 注意：Spring事务只有在发生未捕获的运行时异常的时候才会回滚
	 * 解决方法：在catch里面加入TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	 */
	public void savDelet(){
		User user = new User();
		user.setUserName("王二狗");
		user.setPassWord("111");
		this.baseDao.saveObject(user);
		try {
			this.baseDao.deleteObject(User.class, "lisi");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
