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
@Transactional //��������
public class UserService {

	@Resource 
	private BaseDao baseDao; //��UserServiceע��BaseDao
	/**
	 * �û���¼
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
	 * �����û�
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
	 * ��ѯ��������
	 * @param hql
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public int getAllLine(String hql){
		return Integer.parseInt(this.baseDao.singerResult(hql).toString());
		
	}
	/**
	 * �����û�����
	 * @param user
	 */
	public void addUser(User user){
		this.baseDao.saveObject(user);
	}
	/**
	 * ����ID��ѯ����
	 * @param id
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public User getUserByID(int id){
		return (User)this.baseDao.getObjectByID(User.class, id);
	}
	/**
	 * ����IDɾ���û�
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
	 * ��������
	 * @param user
	 */
	public void updateUser(User user){
		this.baseDao.updateObject(user);
	}
	
	
	
	/**
	 * ��������
	 * ע�⣺Spring����ֻ���ڷ���δ���������ʱ�쳣��ʱ��Ż�ع�
	 * �����������catch�������TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	 */
	public void savDelet(){
		User user = new User();
		user.setUserName("������");
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
