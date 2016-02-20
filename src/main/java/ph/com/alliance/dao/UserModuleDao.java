
package ph.com.alliance.dao;

import java.util.List;

import javax.persistence.EntityManager;

import ph.com.alliance.entity.UserModule;

public interface UserModuleDao {
	public List<UserModule> getUserModuleList(EntityManager entityManager, String searchName);
	public List<UserModule> getUserListFetchFirstFive(EntityManager entityManager);
	public void insert(EntityManager createEntityManager, UserModule user);
	public UserModule getUserById(EntityManager createEntityManager,String userid);
	public void update(EntityManager createEntityManager, UserModule user);
}
