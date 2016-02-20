
package ph.com.alliance.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;

import ph.com.alliance.dao.UserModuleDao;
import ph.com.alliance.entity.UserModule;
import ph.com.alliance.service.UserModuleService;

/**
 * 
 * @author cutamora
 * @description
 * 	This is the service implementation that handles database transaction
 *	Database transaction starts in this layer of the application, and also ends here
 *
 */
@Service("userModuleService")
public class UserModuleServiceImpl implements UserModuleService{

	@Autowired
	private JpaTransactionManager transacationManager;
	
	@Autowired
	private UserModuleDao userModuleDao;
	
	@Override
	public List<UserModule> getUserModuleList(String searchName) {
		//get data from DB
		List<UserModule> list= userModuleDao.getUserModuleList(transacationManager.getEntityManagerFactory().createEntityManager(), searchName);
		if(list!=null && list.size()>0){
			//code
		}
		return list;
	}

	@Override
	public List<UserModule> getUserListFetchFirstFive() {
		List<UserModule> list= userModuleDao.getUserListFetchFirstFive(transacationManager.getEntityManagerFactory().createEntityManager());
		if(list!=null && list.size()>0){
			//code
		}
		return list;
	}

	@Override
	public void insert(UserModule user) {
		userModuleDao.insert(transacationManager.getEntityManagerFactory().createEntityManager(), user);
	}

	@Override
	public UserModule getUserById(String userid) {
		return userModuleDao.getUserById(transacationManager.getEntityManagerFactory().createEntityManager(), userid);
	}

	@Override
	public void update(UserModule user) {
		userModuleDao.update(transacationManager.getEntityManagerFactory().createEntityManager(), user);
	}
	
}
