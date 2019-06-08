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

@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService=new UserServiceImpl();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage=request.getParameter("currentPage");
		int pageSize=5;
		ArrayList<User> recordList=userService.getAllUsersByPage(Integer.parseInt(currentPage),pageSize);
		int recordCount=userService.getUserCount();
		PageBean pageBean=new PageBean(Integer.parseInt(currentPage),pageSize,recordCount,recordList);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/list.jsp").forward(request, response);
		
	}

}
