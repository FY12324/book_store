<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>上传头像</title>
<link href="boots/bootstrap.css" rel="stylesheet">
<link href="register.css" rel="stylesheet">
<style type="text/css">
</style>
</head>
<body>
<div align="center" class="container">
	<div class="form-cs" style="width:500px;height:300px;background-color: rgba(255,255,255, 0.7);margin-top: 200px;">
	<form method="post" action="index.jsp" enctype="multipart/form-data">
		<input type="hidden" name="name" value="test"><br>
		<div class="form-group">
    		<label for="exampleInputFile">头像上传</label>
    		<input type="file" id="exampleInputFile"  name="file1" size="30"><br/>
    		<p class="help-block">选择您要上传的头像，将作为您的个人资料展示给他人。</p>
    		<input type="submit" name="submit" value="上传" class="btn btn-default"><br>
  		</div>
		
	</form>
	</div>
</div>
</body>
</html>