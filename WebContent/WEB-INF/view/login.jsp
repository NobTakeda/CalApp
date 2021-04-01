<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カロリー管理アプリログイン画面</title>
</head>
<body>
<div id="maincontaner">
	<h1>ログイン画面</h1>
	<form action="/calapp/Login" method="post">
		<p>ID,パスワードは半角英数字をお入れください</p>
		<table class="userdata">
			<tr><th>ID</th><td><input type="text" name="userid" required><td></tr>
			<tr><th>PASSWORD</th><td><input type="password" name="userpass" required><td></tr>
		</table>

	</form>
</div>

</body>
</html>