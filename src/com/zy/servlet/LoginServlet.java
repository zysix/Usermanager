package com.zy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zy.model.PageBean;
import com.zy.model.User;
import com.zy.service.UserService;
import com.zy.service.impl.UserServiceImpl;
import com.zy.util.MD5;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService  userService=new UserServiceImpl();   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("utf-8");
		//response.setCharacterEncoding("utf-8");
	    //contentType="text/html;charset=UTF-8"的作用是指定对服务器响应进行重新编码的编码。
		//request.setCharacterEncoding("UTF-8")的作用是设置对客户端请求进行重新编码的编码。
		//response.setCharacterEncoding("UTF-8")的作用是指定对服务器响应进行重新编码的编码
		MD5 md5 = new MD5();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String passwordMD5=md5.getResult(password);
		PrintWriter out = response.getWriter();
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setPassword(passwordMD5);
		HttpSession session = request.getSession(false);  
        if(session == null) {  
            out.print("验证码处理问题1");  
            return;  
        }  
          
        String saveCode = (String)session.getAttribute("check_code");  
        if(saveCode == null) {  
            out.print("验证码处理问题2");  
            return;  
        }  
          
        String checkCode = request.getParameter("check_code");  
        if(!saveCode.equals(checkCode)) {  
        	request.getRequestDispatcher("/login.jsp").forward(request, response);
        }  
		if(userService.checkUserLogin(user)) {
			int currentPage=1;
			int pageSize=5;//每页数量为5；
			ArrayList<User> recordList=userService.getAllUsersByPage(currentPage, pageSize);
			int recordCount=userService.getUserCount();
			
			Cookie username_cookie=new Cookie("username", username);  
			username_cookie.setMaxAge(600);  
			PageBean pageBean=new PageBean(currentPage,pageSize,recordCount,recordList);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("allUser", userService.getAllUser());
			request.getSession().setAttribute("myuser", user);
			request.getRequestDispatcher("/list.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		}
		
	}

}
