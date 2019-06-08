<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*,com.zy.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息系统</title>
	<link rel="stylesheet" type="text/css" href="bootstrap_plugins/css/bootstrap.css">
	<script src="bootstrap_plugins/js/jquery-1.11.3.min.js"></script>
	<script src="bootstrap_plugins/js/bootstrap.js"></script>
	<style>
		#loginbody{
			width: 500px;
			height:400px;
			border:solid 1px grey;
			margin-top:50px;
		}
		.info{
			width:1170px;
			height:100px;
			margin-top:60px;
			border:solid 1px grey;
		}
	</style>
	<script type="text/javascript">
		function del(){
			return window.confirm("删除用户?");
		}
		function select_all(){
			var myselect=document.getElementsByName("myselect");
			var mycheck=document.getElementById("all");
			if(mycheck.checked==false){
				for(var i=0;i<myselect.length;i++)
					myselect[i].checked=false;
			}else{
				for(var i=0;i<myselect.length;i++)
					myselect[i].checked=true;
			}
		}
		$(function(){
			$("#mybtn").click(function(){
				var statu=confirm("确定删除选中项！");
				if(!statu){
					return false;
				}else{
					$("#doSubmit").click();
				}
			});
		})
		
	</script>
	<script>
		window.history.go(1);
	</script>
</head>
<body>
	<% ArrayList<User> allUser=(ArrayList<User>)request.getAttribute("allUser");  %>
	 <div class="container">
	 <div class ="text-center" style="font-size:36px;background:#1a38ca;width:1150px;height:100px">用户信息管理系统</div>
			<div class="row">
			<div class="col-md-4  col-md-offset-10">
				<label for="exampleInputEmail1InputEmail1">欢迎您，${myuser.username }</label>
				&nbsp;&nbsp;
				<a href="UserServlet?type=quit" > 安全退出</a>
			</div>
			<div class="info">
				<div class="col-md-4">
				<button type="button" class="btn btn-success" onclick="window.location.href='addUser.jsp'">添加用户</button>
				<button type="button" id="mybtn" class="btn btn-primary" onclick="deleteUserBySelected();">批量删除</button>
				</div>
				<div class="col-md-4 col-md-offset-9">
				<input type="text" placeholder="关键字">
				<button type="button" class="btn btn-primary">查询</button>
				</div>			
			</div>
			<form action="UserServlet?type=delBySelected" method="post" >
			<br/>
				<table class="table table-hover">
					<tr class="info">
						<th><input type="checkbox" id="all" onclick="select_all()"/>全选</th>
						<th>id</th>
						<th>用户名</th>
						<th>密码</th>
						<th>等级</th>
						<th>邮箱</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageBean.recordList}" var="user">
					<%-- <c:forEach items="${allUser }" var="user"> --%>
					<tr>
						<td><input type="checkbox" name="myselect" value="${user.id}"/></td>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.password}</td>
						<td>${user.grade}</td>
						<td>${user.email }</td>
						<td><a href="UserServlet?type=delete&id=${user.id }" onClick="return del();">删除</a>
							&nbsp;&nbsp;<a href="UserServlet?type=modifyUI&id=${user.id }">编辑</a></td>
					</tr>
					</c:forEach>
 				<%-- <%for (int i=0;i<allUser.size();i++){ 
         			User user=allUser.get(i); 
				%>
					<tr> 
						<td><input type="checkbox"u></td>
 						<td><%=user.getId() %></td> 
 						<td><%=user.getUsername() %></td> 
 						<td><%=user.getPassword() %></td> 
						<td><%=user.getGrade() %></td> 
 						<td><%=user.getEmail() %></td>
 						<td><a href="UserServlet?type=delete&id=<%=user.getId() %>" onClick="return del();">删除</a>&nbsp;&nbsp;<a href="#">编辑</a></td> 
 					</tr>
 				<% }%>  --%>
				</table>
				<input type="submit" value="aaa" id="doSubmit" style="display:none"/>
			</form>
	</div>
	
	<div class="row">
		<div style="text-align:center">
 			<nav aria-label="Page navigation">
 				<ul class="pagination">
 					<li><a href="PageServlet" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
 						<c:forEach begin="1" end="${pageBean.pageCount}" var="i">
 							<li><a href="PageServlet?currentPage=${i}">${i}</a></li>
 						</c:forEach>
 					<li><a href="PageServlet" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
 				</ul>
 			</nav>
 		</div>
 	</div>
 	</div>
</body>
</html>