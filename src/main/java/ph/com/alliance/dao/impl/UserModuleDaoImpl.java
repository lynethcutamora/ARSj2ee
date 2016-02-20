
package ph.com.alliance.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ph.com.alliance.dao.UserModuleDao;
import ph.com.alliance.entity.UserModule;

/**
 * 
 * @author cutamora
 * @description
 * 	Data access usermodule implementation using Java Persistence API
 *
 */

@Repository("userModuleDao")
public class UserModuleDaoImpl implements UserModuleDao{

	@Override
	public List<UserModule> getUserModuleList(EntityManager entityManager, String searchName) {
		StringBuilder sb = new StringBuilder("From UserModule u");
		if(searchName!=null && !searchName.equals("") && !searchName.trim().equals("")){
			sb.append(" WHERE u.firstName LIKE :searchName"); //where u.firstName LIKE "searchName"
			sb.append(" OR u.familyName LIKE :searchName"); //where u.lastName LIKE "searchName"
			sb.append(" OR u.email LIKE :searchName"); //where u.email LIKE "searchName"
			sb.append(" AND u.deletedFlag = 0 ORDER BY u.id DESC");
		}else{
			sb.append(" WHERE u.deletedFlag = 0 ORDER BY u.id DESC");
		}
		Query query = entityManager.createQuery(sb.toString()); //Select * from user_md
		
		if(searchName!=null && !searchName.equals("") && !searchName.trim().equals("")){
			query.setParameter("searchName", "%"+searchName+"%");
		}
		
		List <UserModule> userList = query.getResultList();
		return userList;
	}
	
	@Override
	public List<UserModule> getUserListFetchFirstFive(EntityManager entityManager) {
		StringBuilder sb = new StringBuilder("From UserModule u WHERE u.deletedFlag = 0 ORDER BY u.createdDate DESC");
		Query query = entityManager.createQuery(sb.toString()); //Select * from user_md 5 rows only
		List <UserModule> userList = query.setMaxResults(5).getResultList();
		return userList;
	}

	@Override
	public void insert(EntityManager createEntityManager, UserModule user) {
		EntityTransaction transaction = createEntityManager.getTransaction();
		try{
			transaction.begin();
			createEntityManager.persist(user);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
	}

	@Override
	public UserModule getUserById(EntityManager createEntityManager,String userid) {
		StringBuilder sb = new StringBuilder("From UserModule u WHERE u.id = :userid");
		Query query = createEntityManager.createQuery(sb.toString()); //Select * from user_md 5 rows only
		query.setParameter("userid", userid);
		return (UserModule) query.getSingleResult();
	}

	@Override
	public void update(EntityManager createEntityManager, UserModule user) {
		EntityTransaction transaction = createEntityManager.getTransaction();
		try{
			transaction.begin();
			createEntityManager.merge(user);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}
	}
	
}
