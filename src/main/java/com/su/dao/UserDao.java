package com.su.dao;
import java.util.List;

import com.su.models.User;





/**
 * @author Yujie
 *
 */
public interface UserDao {
	public void addUser(User user);
	public void delete(String userName);	
	public User findByName(String userName);
	public List<User> findAllUser();
	public List<User> findUserUnAuth();
}
