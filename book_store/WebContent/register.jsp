<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<link href="boots/bootstrap.css" rel="stylesheet">
<link href="register.css" rel="stylesheet">
<link href="boots/font-awesome.css" rel="stylesheet">
<link href="boots/font-awesome.min.css" rel="stylesheet">
<script src="js/jquery-1.12.4.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script type="text/javascript" language="jsvascript">
	function RegisterSubmit() {
		with(document.Register){
			var user=document.Register.username.value;
			var pass=document.Register.pwd.value;
			var refomepass=document.Register.reformpwd.value;
			if (user==null||user=="") {
				alert("请填写用户名");
			}else if (pass==null||pass=="") {
				alert("请填写密码");
			}else if (pass!=refomepass) {
				alert("两次密码不一致");
			}
			else document.Register.submit();
		}
	}
</script>
</head>
<body>
	<div align="center" class="container">
		<h2>欢迎注册book商城</h2>
		<form action="RegisterServlet" method="post" name="Register" class="form-horizontal">
			<div class="form-group">
				<label for="exampleInputEmail1" class="col-md-3"><span class="glyphicon glyphicon-user"></span>用户名</label>
				<div class="col-md-6">
					<input type="text"class="form-control" id="exampleInputEmail1" placeholder="请填写用户名" name="username">
				</div> 
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1" class="col-md-3"><span class="glyphicon glyphicon-lock"></span>密码</label> 
				<div class="col-md-6">
					<input type="password"class="form-control" id="exampleInputPassword1" placeholder="请输入密码" name="pwd">
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1" class="col-md-3"><span class="glyphicon glyphicon-ok"></span>密码</label> 
				<div class="col-md-6">
					<input type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入密码" name="reformpwd">
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-offset-2 col-md-1">
					<button type="button" class="btn btn-default" onclick="RegisterSubmit()">Submit</button>
				</div>
				<div class="col-md-offset-2 col-md-1">
					<input type="reset" value="重置" class="btn btn-default">
				</div>
			</div>
		</form>
		
	</div>
</body>
</html>