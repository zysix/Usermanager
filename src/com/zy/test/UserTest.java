package com.zy.test;

import org.junit.Test;

import com.zy.model.User;
import com.zy.service.UserService;
import com.zy.service.impl.UserServiceImpl;

public class UserTest {
	private UserService userService=new UserServiceImpl();
	@Test
	public void CheckUserLogin() {
		User user=new User();
		user.setUsername("zhang");
		user.setPassword("123456");
		System.out.println(userService.checkUserLogin(user));
	}
}
