<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
		#mybody{
			width:980px;
			height:500px;
			margin-top:500px;
		}	
	</style>
</head>
<body>		
	<!-- <div class="col-md-12" style="background:#407281;color:white;width:980px;height:150px">
				<h1 style="text-align:center">用户信息管理</h1>
	</div>
	<div class="col-md-2 col-md-offser-10">
		<h4>欢迎您！XXXX &nbsp;&nbsp;&nbsp;<a href="#">安全退出</a></h4>
	</div> -->
	<div class="container">
		<div class ="text-center" style="font-size:36px;background:#1a38ca;width:1150px;height:100px">用户信息管理系统</div>
			<div class="row">
				<div class="col-md-4  col-md-offset-10">
					<label for="exampleInputEmail1InputEmail1">欢迎您，${myuser.username }</label>
					&nbsp;&nbsp;
					<a href="UserServlet?type=quit" > 安全退出</a>
				</div>
				<div class="col-md-2 col-md-offset-2" id="mybody">
					<h2 style="text-align:center;margin-top:-400px">增加用户信息</h2>
					</hr>
					<form class="form-horizontal" action="UserServlet?type=add" method="post">
						<div class="form-group">
							<lable for="inputUserName" class="col-sm-2 control-lable">姓名:</lable>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="username" id="username"  placeholder="用户名">
								</div>
						</div>
						<div class="form-group">
							<lable for="inputPassword" class="col-sm-2 control-lable">密码：</lable>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="password" id="password"  placeholder="密码">
								</div>
						</div>
						<div class="form-group">
							<lable for="inputGrade" class="col-sm-2 control-lable">等级：</lable>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="grade" id="grade"  placeholder="等级">
								</div>
						</div>
						<div class="form-group">
							<lable for="inputEmail" class="col-sm-2 control-lable">邮箱：</lable>
								<div class="col-sm-10">
									<input type="text"  class="form-control" name="email" id="email"  placeholder="邮箱">
								</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">	
								<button type="submit" class="btn btn-success">确定</button>
								<button type="reset" class="btn btn-warning">取消</button>
							</div>
						</div>
					</form>
				</div>
			</div>
	</div>
</body>
</html>