package com.zy.service;

import java.util.ArrayList;

import com.zy.model.User;

public interface UserService {
	    //增加用户
		public boolean addUser(User user);
		//删除用户
		public boolean deleteUser(String id);
		//批量删除
		public boolean deleteUserBySelected(String para);
		//修改用户信息
		public boolean modifyUser(User user);
		//按照id查找
		public User findUserById(String id);
		//按照姓名查找
		public User findUserByName(String username);
		//显示全体用户
		public ArrayList<User> getAllUser();
		//分页
		public ArrayList<User> getAllUsersByPage(int currentPage,int pageSize);
		//数据库总记录数
		public Integer getUserCount();
		//验证用户登录
		public boolean checkUserLogin(User user);
	
}
