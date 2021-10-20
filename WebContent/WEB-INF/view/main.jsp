<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カロリー管理</title>
<link rel="stylesheet" href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="css/main.css"/>
</head>
<body>
<div id="maincontainer">
	<h1>カロリー管理アプリへようこそ</h1>
	<p>必要なデータを入力してください。<br>
	なお、目標体重に応じた摂取カロリーは<a href="https://keisan.casio.jp/exec/system/1183427246" target="_blank" rel="noopener noreferrer">こちら</a>のサイトから計算できます。</p>
	<form action="/calapp/Main" method="post">
		<table class="userdata">
			<tr><th>名前</th><td><input type="text" name="name" required></td></tr>
			<tr><th>身長(cm)</th><td><input type="number" name="heightCm" min="0" step="0.1" required></td></tr>
			<tr><th>体重(kg)</th><td><input type="number" name="weightKg" min="0" step="0.1" required></td></tr>
			<tr><th>目標摂取カロリー</th><td><input type="number" name="targetCal" min="0" step="1" required></td></tr>
		</table>
		<button type="submit">送信</button>
	</form>

</div>
</body>
</html>