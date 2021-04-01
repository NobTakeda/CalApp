<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*"%>
    <%
    String date=(String)request.getAttribute("date");
    Integer id=(Integer)request.getAttribute("id");
    Food food=(Food)request.getAttribute("food");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="css/main.css"/>

</head>
<body>
<div id="maincontainer">
	<h2>更新ページ</h2>
	<form action="/calapp/FoodUpdate" method="post">
		<table class="inputform">
			<tr><th>名前</th><td><input type="text" name="name" value="<%= food.getName() %>" required></td></tr>
			<tr><th>カロリー</th><td><input type="number" name="cal" value="<%= food.getCal() %>" min="0" step="1" required></td></tr>
		</table>
			食べた日:<input type="date" name="date" value="<%= food.getDate() %>" required><br>
			朝<label><input type="radio" name="time" <% if(food.getTime()==0){%> checked <% } %> value="0"></label>
			昼<label><input type="radio" name="time" <% if(food.getTime()==1){%> checked <% } %> value="1"></label>
			夜<label><input type="radio" name="time" <% if(food.getTime()==2){%> checked <% } %> value="2"></label><br>
			<button type="submit">更新する</button>
			<input type="hidden" name="id" value="<%= id %>">
	</form>
</div>
</body>
</html>