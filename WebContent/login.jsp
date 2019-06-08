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
	</style>
	<script type="text/javascript">
	  function validate (){  
	    var inputCode = document.getElementById("vcode").value.toLowerCase(); 
	   // String saveCode = (String)session.getAttribute("check_code");
	  	//String checkCode = request.getParameter("check_code");
	    if(inputCode.length <=0){  
	        alert("请输入验证码！");  
	        return false;  
	    } 
	    else if(inputCode != code.toLowerCase()){
	    //else if(!saveCode.equals(checkCode)){
	        alert("验证码输入错误！");  
	        //show();
	        return false;  
	    }
	    else{  
	        alert("^-^ OK");  
	        return true;  
	    }  
	  }
	   function remember(){
		  var remFlag = $("input[type='checkbox']").is(':checked'); 
		  if(remFlag==true){ 
			  //如果选中设置remFlag为true 
			  //cookie存用户名和密码,回显的是真实的用户名和密码,存在安全问题. 
			  var conFlag = confirm("记录密码功能不宜在公共场所(如网吧等)使用,以防密码泄露.您确定要使用此功能吗?"); 
			  System.out.println("成功8");
			  if(conFlag){ 
				  //确认标志 
				  $("#remFlag").val(true); 
				  System.out.println("成功2");
			  }else{ 
				  $("input[type='checkbox']").removeAttr('checked'); 
				  $("#remFlag").val(false); 
				  System.out.println("成功3");
			  } 
		}else{ 
			//如果没选中设置remFlag为false 
			$("#remFlag").val(false); 
			 System.out.println("成功4");
		} 
	 }	
	  $(document).ready(function(){
		  //记住密码功能
		  var str = getCookie("login"); //??????????????????
		  str = str.substring(1,str.length-1); 
		  var username = str.split(",")[0]; 
		  var password = str.split(",")[1]; 
		  //自动填充用户名和密码 
		  $("#username").val(username); 
		  $("#password").val(password);
		  if(str!=null && str!=""){ 
			  $("input[type='checkbox']").attr("checked", true); 
			  System.out.println("成功");
		  } 
	   });
	  function getCookie(username) { 
		  var name = username + "="; 
		  var ca = document.cookie.split(';'); 
		  for(var i=0; i<ca.length; i++) { 
			  var c = ca[i]; 
			  while (c.charAt(0)==' ')
				  c = c.substring(1); 
			  if (c.indexOf(name) != -1){
				  System.out.println("成功1");
				  return c.substring(name.length, c.length); 
			  }
		  } 
		  return ""; 
	 } 
	</script>
</head>
<body>
	<form action=LoginServlet method="post" name="form1" onsubmit="return validate();">
	<div class="container"><!-- 固定宽度支持响应式布局 -->
		<div class ="text-center" style="font-size:36px;background:#1a38ca;width:1000px;height:100px">用户信息管理系统</div>
		<div class="row">
			<div class="col-md-3 col-md-offset-3" id="loginbody">
			<p class="text-center" style="font-size:30px">登录</p>
			<label for="exampleInputEmail1InputEmail1" class="text-center" >用户名</label>
			<br/>
			<input type="text" class="form-control" id="username" name="username" vaule="username"  placeholder="用户名">
			<br/>
			<label for="exampleInputEmail1" class="text-center" >密 &nbsp;&nbsp;码</label>
			<br/>
			<input type="password" class="form-control" id="password" name="password" placeholder="密码">
			<br/>
			<label  class="text-center" >验证码</label>
			<br/>
			<input name="check_code"  type="text" id="vcode"  placeholder="验证码">
       			<img  src="CheckServlet" method="post" name="check_code1" id="code"><!-- id="check_code" onClick="return validate(); -->
       			
       		<a href="login.jsp" method="post">看不清，换一张</a>
			<br/><br/>
			<input type="checkbox" name="remFlag" id="remFlag"  onclick="remember();">记住用户名
			<br/><br/>
			<input type="submit" name="submit1" value="登录" onClick="return validate ();">
			<input type="reset" value="重置">
			</div>
		</div>
	</div>
	</form>
</body>
</html>