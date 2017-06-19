package com.ruanko.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;


/**
 * ���ݲ�����
 * 
 * @author lipeng
 * 
 */
@Repository("baseDao")
public class BaseDao{
	@Resource
	public HibernateTemplate hibernateTemplate;

	/**
	 * ����hql��������������Ҷ���
	 * 
	 * @param hql
	 *            ��ѯ���
	 * @param values
	 *            ��������
	 * @return List
	 */
	public List findObjectByHql(String hql, Object[] values) {
		return this.hibernateTemplate.find(hql, values);
	}
	
	/**
	 * ��������
	 * @param ectity
	 */
	public void saveObject(Object ectity){
		this.hibernateTemplate.save(ectity);
	}
	
	/**
	 * ����IDɾ������
	 * @param entityClass
	 * @param id
	 */
	public void deleteObject(Class entityClass,Serializable id)throws Exception{
		this.hibernateTemplate.delete(this.hibernateTemplate.load(entityClass, id));
	}
	/**
	 * ��������
	 * @param entity
	 */
	public void updateObject(Object entity){
		this.hibernateTemplate.update(entity);
	}
	/**
	 * ����ID��������
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object getObjectByID(Class entityClass,Serializable id){
		return this.hibernateTemplate.get(entityClass, id);
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param hql
	 * @param offset
	 * @param line
	 * @return
	 */
	public List findObjByFenye(final String hql ,final int offset,final int line){
	 return this.hibernateTemplate.executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setFirstResult(offset);
				query.setMaxResults(line);
				return query.list();
		}});
	}
	
	/**
	 * ��һ�������ѯ
	 * @param hql
	 * @return
	 */
	public Object singerResult(final String hql){
		return this.hibernateTemplate.execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				return query.uniqueResult();
			}
		});
		
	}
}
