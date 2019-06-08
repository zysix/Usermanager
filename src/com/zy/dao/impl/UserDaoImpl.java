package com.zy.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.zy.dao.UserDao;
import com.zy.model.User;
import com.zy.util.SQLHelper;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean addUser(User user) {
		boolean b=false;
		String sql="insert into tuserlogin(username,password,grade,email) values(?,?,?,?)";
		String []parameters= {user.getUsername(),user.getPassword(),String.valueOf(user.getGrade()),user.getEmail()};
		try {
			SQLHelper.exectueUpdate(sql, parameters);;
			b=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean deleteUser(String id) {
		boolean b=false;
		String sql="delete from tuserlogin where id=?";
		String []parameters= {id};
		try {
			SQLHelper.exectueUpdate(sql, parameters);;
			b=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean deleteUserBySelected(String para) {
		boolean b=false;
		String sql="delete from tuserlogin where id in ("+para+")";
		try {
			SQLHelper.executeUpdate2(sql);
			b=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean modifyUser(User user) {
		boolean b=false;
		String sql="update tuserlogin set username=?,password=?,grade=?,email=? where id=?";
		String []parameters= {user.getUsername(),user.getPassword(),String.valueOf(user.getGrade()),user.getEmail(),String.valueOf(user.getId())};
		try {
			SQLHelper.exectueUpdate(sql, parameters);;
			b=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public User findUserById(String id) {
		User user=new User();
		String sql="select * from tuserlogin where id=?";
		String []parameters= {id};
		user=SQLHelper.executeQueryUser(sql, parameters).get(0);//取出第0个元素
		return user;	
	}

	@Override
	public User findUserByName(String username) {
		User user=new User();
		String sql="select * from tuserlogin where username=?";
		String []parameters= {username};
		user=SQLHelper.executeQueryUser(sql, parameters).get(0);
		return user;
	}

	@Override
	public ArrayList<User> getAllUser() {
		String sql="select * from tuserlogin";
		String []parameters= {};
		ArrayList<User> allUser=SQLHelper.executeQueryUser(sql, parameters);
		return allUser;
	}

	@Override
	public ArrayList<User> getAllUsersByPage(int currentPage, int pageSize) {
		ArrayList<User> arrayUsersByPage=new ArrayList<User>();
		String sql="select * from tuserlogin limit "+(currentPage-1)*pageSize+","+pageSize;
		String []parameters= null;
		arrayUsersByPage=SQLHelper.executeQueryUser(sql, parameters);
		return arrayUsersByPage;
	}

	@Override
	public Integer getUserCount() {
		int count=0;
		String sql="select count(*) from tuserlogin";
		String []parameters=null;
		ResultSet rs=SQLHelper.executeQuery(sql, parameters);
		try {
			if(rs.next()) {
			count=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			SQLHelper.close(SQLHelper.getRs(),SQLHelper.getPs(),SQLHelper.getConn());
		}
		System.out.println(count);
		return count;
		
	}

	@Override
	public boolean checkUserLogin(User user) {
		boolean b=false;
		String sql="select * from tuserlogin where username=? and password=?";
		String []parameters= {user.getUsername(),user.getPassword()};
		ArrayList<User> arraylist= SQLHelper.executeQueryUser(sql, parameters);
		if(arraylist.size()>0) {
			b=true;
		}
		return b;
	}

}
