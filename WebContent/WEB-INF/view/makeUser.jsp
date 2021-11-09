<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*" %>
    <%
    String userid=(String)request.getAttribute("userid");
    String userpass=(String)request.getAttribute("userpass");
    String errorMsg=(String)request.getAttribute("errorMsg");
    %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="css/main.css?"/>
<meta charset="UTF-8">
<title>新規ユーザー登録</title>
</head>
<body>
<div id="maincontaner">
	<h1>新規ユーザー登録</h1>
	<form action="/calapp/MakeUser" class="form" method="post">
	<% if(errorMsg == null){ %>
		<p>こちらの内容でよろしければ登録ボタンを押してください。</p>
	<% }else if(errorMsg=="sameID"){ %>
		<p>このIDは既に使われています。</p>
	<% } %>
		<table class="userdata">
			<% if(userid == null || userid.equals("null")){ %>
				<tr><th>ID</th><td><input type="text"  name="userid"></td></tr>
				<tr><th>パスワード</th><td><input type="text" name="userpass"></td></tr>
			<% }else{ %>
				<tr><th>ID</th><td><input type="text" value="<%= userid %>" name="userid" required></td></tr>
				<tr><th>パスワード</th><td><input type="text" value="<%= userpass %>" name="userpass" required></td></tr>
			<% } %>
		</table>
		<button type="submit" class="databtn">登録</button>
		<button type="submit" class="databtn" value="returnToLoginPage" name="returnToLoginPage">戻る</button>
	</form>
</div>
</body>
</html>