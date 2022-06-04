<%@page import="com.jspsmart.upload.SmartUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录界面</title>
<link href="register.css" rel="stylesheet">
<link href="boots/bootstrap.css" rel="stylesheet">
<link href="boots/font-awesome.css" rel="stylesheet">
<link href="boots/font-awesome.min.css" rel="stylesheet">
<script src="js/jquery-1.12.4.js" type="text/javascript"></script>
<script src="js/bootstrap.js" type="text/javascript"></script>
<script type="" language="jsvascript">
	function LoginSubmit() {
		with(document.Login){
			var user=document.Login.loginName.value;
			var pass=document.Login.password.value;
			var check=document.Login.checkcode.value;
			if (user==null||user=="") {
				alert("请填写用户名");
			}else if (pass==null||pass=="") {
				alert("请填写密码");
			}else if (check==null||check=="") {
				alert("请填写验证码");
			}
			else document.Login.submit();
			}
		}
</script>
</head>
<body>

<%
	String username="",password="";
	Cookie[] cookies=request.getCookies();
	if(cookies!=null){
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("username")){
				username=cookies[i].getValue();
			}
			if(cookies[i].getName().equals("password")){
				password=cookies[i].getValue();
			}
		}
	}
%>
	<div align="center" class="container"  style="padding-top: 200px">
	<%
		//新建SmartUpload对象
		SmartUpload su=new SmartUpload();
		//SmartUpload对象初始化
		su.initialize(pageContext);
		//上传文件
		su.upload();
		//将上传文件全部保存到指定目录
		int count =su.save("/img/");
		//逐一提取上传文件信息，同时可保存文件
		for(int i=0;i<su.getFiles().getCount();i++){
		com.jspsmart.upload.File file=su.getFiles().getFile(i);
		//若文件不存在则继续
		if(file.isMissing())
			continue;
		//显示当前文件信息
		out.print("<img src='img/"+file.getFilePathName()+"' class='img1' style='height:50px;width:50px;'<br/>");
		}
	%>
		<h1>欢迎登录book商城</h1>
		<form action="LoginServlet" method="post" name="Login" class="form-horizontal">
			<div class="form-group">
				<label for="exampleInputEmail1" class="col-md-3"><span class="glyphicon glyphicon-user"></span>用户名</label>
				<div class="col-md-6">
					<input type="text"class="form-control" id="exampleInputEmail1" placeholder="请填写用户名" name="loginName" value="<%=username%>">
				</div> 
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1" class="col-md-3"><span class="glyphicon glyphicon-lock"></span>密码</label> 
				<div class="col-md-6">
					<input type="password"class="form-control" id="exampleInputPassword1" placeholder="请输入密码" name="password" value="<%=password%>">
				</div>
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1" class="col-md-3"><span class="glyphicon glyphicon-ok-circle"></span>验证码</label> 
				<div class="col-md-9">
					<div class="row">
						<div class="col-md-8">
							<input type="text" class="form-control" id="exampleInputPassword1" placeholder="请输入验证码" name="checkcode">
						</div>
						<div class="col-md-4">
						<div class="row">
							<div class="col-md-6">
								<img alt="验证码" src="CheckCode">
							</div>
							<div class="col-md-6">
								<input type="submit" value="换一张" onclick="Login.action='index.jsp'">
							</div>
						</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-3">
					<input type="button" class="btn btn-default" onclick="LoginSubmit()" name="B1" value="提交"/>
				</div>
				<div class="col-sm-3">
					<input type="reset" value="重置" class="btn btn-default" name="B3"/>
				</div>
				<div class="col-sm-3">
					<input type="submit" value="注册" class="btn btn-default" name="B2" onclick="Login.action='register.jsp'"/>
				</div>
			</div>
		</form>
	</div>
</body>
</html>