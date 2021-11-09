<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
    <%
    User user=(User)session.getAttribute("user");
    List<Food> list=(List<Food>)request.getAttribute("list");
    String msg=(String)request.getAttribute("msg");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>あなたの情報</title>
<link rel="stylesheet" href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="css/main.css?"/>
</head>
<body>
<div id="maincontainer">
	<h1>あなたの情報</h1>
	<table class="userdata">
		<tr><th>名前</th><td><%= user.getName() %></td></tr>
		<tr><th>身長</th><td><%= user.getHeight() %></td></tr>
		<tr><th>体重</th><td><%= user.getWeight() %></td></tr>
		<tr><th>BMI</th><td><%= user.getBmi() %></td></tr>
		<tr><th>目標摂取カロリー</th><td><%= user.getTargetCal() %></td></tr>
	</table>
	<div>
		<h2>&#9660;食べたものを登録してください</h2>
		<form action="/calapp/Manage" method="post">
			<table class="inputform">
				<tr><th>名前</th><td><input type="text" name="name" required></td></tr>
				<tr><th>カロリー</th><td><input type="number" name="cal" min="0" step="1" required></td></tr>
				<tr><th>食べた日</th><td><input type="date" name="date" required></td></tr>
				<tr><th>時間帯</th><td><label>朝<input type="radio" name="time" value="0"></label>
				<label>昼<input type="radio" name="time" value="1"></label>
				<label>晩<input type="radio" name="time" value="2"></label></td></tr>
			</table>
			<button type="submit">登録</button>
			<input type="hidden" name="userid" value="<%= user.getUserid() %>">
			<% if(list !=null && list.size() > 0){ %>
				<input type="hidden" name="listDate" value="<%= list.get(0).getDate() %>">
			<% }else{ }%>
		</form>
		<h3>&#9660;既に登録してある日を呼び出す場合はこちら</h3>
		<div id="callData">
			<form action="/calapp/Manage">
				<div id="dateform">
					<input type="date" name="date"  required><br>
					<input type="hidden" name="userid" value="<%= user.getUserid() %>">
				</div>
				<button type="submit" class="btn">呼び出し表示</button>
			</form>
		</div>
	</div>
	<% if(list != null && list.size() > 0){ %>
		<h2>&#9660;<%= list.get(0).getDate() %>日に食べたもの</h2>
		<div id="dataBox">
			<% int sum=0; %>
			<% for(int i=0;i<list.size();i++){ %>
				<table class="dataTable">
					<tr>
						<th><%= list.get(i).getTimeStr() %></th><td><%= list.get(i).getName() %></td><td><%= list.get(i).getCal() %> kcal</td>
					</tr>
				</table>
				<div id="updateBtns">
					<form action="/calapp/FoodUpdate">
						<button type="submit" name="id" value="<%= list.get(i).getId() %>" class="databtn">更新</button>
						<input type="hidden" name="date" value="<%= list.get(i).getDate() %>">
						<input type="hidden" name="userid" value="<%= user.getUserid() %>">
					</form>
					<form action="/calapp/Delete" method="post">
						<button type="submit" name="id" value="<%= list.get(i).getId() %>" onclick="return confirm('削除してよろしいですか？')" class="databtn">削除</button>
						<input type="hidden" name="date" value="<%= list.get(i).getDate() %>">
						<input type="hidden" name="userid" value="<%= user.getUserid() %>">
					</form>
				</div>
				<% sum+=list.get(i).getCal(); %>
			<% } %>
			<h3>&#9660;1日分の合計は<%= sum %>kcalです。登録する場合はこちら</h3>
			<form action="/calapp/Register" method="post" class="form">
				<button type="submit" name="date" value="<%= list.get(0).getDate() %>" class="btn">登録</button>
				<input type="hidden" name="userid" value="<%= user.getUserid() %>">
				<input type="hidden" name="hideListButton" value="hideListButton">
			</form>
		</div>
	<% }else{ %>
	<% } %>
	<div>
		<h2>&#9660;最新7日分のデータを見る</h2>
		<form action="/calapp/ShowData" class="form">
			<button type="submit" class="btn">データ閲覧</button>
			<input type="hidden" name="userid" value="<%= user.getUserid() %>">
		</form>
		<h2>&#9660;指定した日付から30日前までの登録データをJsonで出力</h2>
		<div id="callData">
			<form action="/calapp/MakeResult" target="_blank" method="post">
				<div id="dateform">
					<input type="date" name="date" required><br>
					<input type="hidden" name="userid" value="<%= user.getUserid() %>">
				</div>
				<button type="submit" class="btn">別タブで開く</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>