package ph.com.alliance.service;

import java.util.List;

import javax.persistence.EntityManager;

import ph.com.alliance.entity.UserModule;

/**
 * 
 * @author cutamora
 * @description
 * 	This is the interface for UserModule
 * 
 */
public interface UserModuleService {
	public List<UserModule> getUserModuleList(String searchName);
	public List<UserModule> getUserListFetchFirstFive();
	public void insert(UserModule user);
	public UserModule getUserById(String userid);
	public void update(UserModule user);
}
