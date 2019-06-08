package com.zy.service.impl;

import java.util.ArrayList;

import com.zy.dao.UserDao;
import com.zy.dao.impl.UserDaoImpl;
import com.zy.model.User;
import com.zy.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao=new UserDaoImpl();
	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return userDao.deleteUser(id);
	}

	@Override
	public boolean deleteUserBySelected(String para) {
		return userDao.deleteUserBySelected(para);
	}

	@Override
	public boolean modifyUser(User user) {
		return userDao.modifyUser(user);
	}

	@Override
	public User findUserById(String id) {
		return userDao.findUserById(id);
	}

	@Override
	public User findUserByName(String username) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(username);
	}

	@Override
	public ArrayList<User> getAllUser() {
		return userDao.getAllUser(); 
	}

	@Override
	public ArrayList<User> getAllUsersByPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return userDao.getAllUsersByPage(currentPage, pageSize);
	}

	@Override
	public Integer getUserCount() {
		// TODO Auto-generated method stub
		return userDao.getUserCount();
	}

	@Override
	public boolean checkUserLogin(User user) {
		return userDao.checkUserLogin(user);
	}

}
