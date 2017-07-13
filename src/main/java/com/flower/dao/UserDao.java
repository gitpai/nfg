package com.flower.dao;
import java.util.List;

import com.flower.models.User;
import com.flower.models.UserVerifyCode;



/**
 * @author Yujie
 *
 */
public interface UserDao {
	public void addUserRegCode(UserVerifyCode userVerifyCode);
	public String getUserRegCode(String phoneNum);
	public void addUser(User user);
	public void delete(String userName);	
	public User findByName(String userName);
	public List<User> findAllUser();
	public List<User> findUserUnAuth();

}
