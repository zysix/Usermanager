package com.zy.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zy.model.PageBean;
import com.zy.model.User;
import com.zy.service.UserService;
import com.zy.service.impl.UserServiceImpl;
import com.zy.util.MD5;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService=new UserServiceImpl();
	int currentPage=1;
	int pageSize=5;
	//private Object pageBean;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("utf-8");
		//response.setCharacterEncoding("utf-8");
	    String type=request.getParameter("type");	
		if("delete".equals(type)) {
			String id=request.getParameter("id");
			User user=new User();
			user.setId(Integer.parseInt(id));
			if(userService.deleteUser(id)) {	
				ArrayList<User> recordList=userService.getAllUsersByPage(currentPage, pageSize);
				int recordCount=userService.getUserCount();
				PageBean pageBean=new PageBean(currentPage,pageSize,recordCount,recordList);
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("allUser", userService.getAllUser());
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/list.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/error.jsp").forward(request,response);
			}
		}	
		else if("modifyUI".equals(type)) {
			String id=request.getParameter("id");
			User user=userService.findUserById(id);
			request.getSession().setAttribute("modifyUser", user);
			request.getRequestDispatcher("/modifyUI.jsp").forward(request, response);
		}
		else if("modify".equals(type)) {
			User user=new User();
			MD5 md5 = new MD5();
			String id=request.getParameter("id");
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String passwordMD5=md5.getResult(password);
			String grade=request.getParameter("grade");
			String email=request.getParameter("email");
			user.setId(Integer.parseInt(id));
			user.setUsername(username);
			user.setPassword(password);
			user.setPassword(passwordMD5);
			user.setGrade(Integer.parseInt(grade));
			user.setEmail(email);
			if(userService.modifyUser(user)) {
				request.setAttribute("allUser", userService.getAllUser());
				request.getSession().setAttribute("user", user);
				ArrayList<User> recordList=userService.getAllUsersByPage(currentPage, pageSize);
				int recordCount=userService.getUserCount();
				PageBean pageBean=new PageBean(currentPage,pageSize,recordCount,recordList);
				request.setAttribute("pageBean", pageBean);
				request.getRequestDispatcher("/list.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request,response);
			}
		}else if("add".equals(type)) {
			MD5 md5 = new MD5();
			User user=new User();
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			String passwordMD5 = md5.getResult(password);
			String grade=request.getParameter("grade");
			String email=request.getParameter("email");
			user.setUsername(username);
			user.setPassword(password);
			user.setPassword(passwordMD5);
			user.setGrade(Integer.parseInt(grade));
			user.setEmail(email);
			ArrayList<User> recordList=userService.getAllUsersByPage(currentPage, pageSize);
			int recordCount=userService.getUserCount();
			PageBean pageBean=new PageBean(currentPage,pageSize,recordCount,recordList);
			request.setAttribute("pageBean", pageBean);
			if(userService.addUser(user)) {
				request.setAttribute("allUser", userService.getAllUser());
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("/list.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request,response);
			}
		}else if("delBySelected".equals(type)) {
			String []para=request.getParameterValues("myselect");
			StringBuffer inParams=new StringBuffer();
			for(int i=0;i<para.length;i++) {
				inParams.append(para[i]);
				inParams.append(",");
			}
			String para2=inParams.substring(0, inParams.length()-1);
			if(userService.deleteUserBySelected(para2)) {
				ArrayList<User> recordList=userService.getAllUsersByPage(currentPage, pageSize);
				int recordCount=userService.getUserCount();
				PageBean pageBean=new PageBean(currentPage,pageSize,recordCount,recordList);
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("allUser", userService.getAllUser());
				request.getRequestDispatcher("/list.jsp").forward(request, response);
			}
		}else if("quit".equals(type)) {
			User user=new User();
			if(null!=request.getSession()) {
				//ArrayList<User> recordList=userService.getAllUsersByPage(currentPage, pageSize);
				//int recordCount=userService.getUserCount();
				//PageBean pageBean=new PageBean(currentPage,pageSize,recordCount,recordList);
				//request.setAttribute("pageBean", pageBean);
				request.getSession().setAttribute("myuser", user);
				request.setAttribute("allUser", userService.getAllUser());
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}
	}

}
