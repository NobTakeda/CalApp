<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
    <%
    User user=(User)session.getAttribute("user");
    List<Meals> list=(List<Meals>)request.getAttribute("list");
    int cssCal=user.getTargetCal()+500;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>直近の1週間</title>
<link rel="stylesheet" href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="css/main.css"/>
</head>
<body>
<div id="maincontainer">
	<h1>あなたの情報</h1>
	<div>
		<table class="userdata">
			<tr><th>名前</th><td><%= user.getName() %></td></tr>
			<tr><th>身長</th><td><%= user.getHeight() %></td></tr>
			<tr><th>体重</th><td><%= user.getWeight() %></td></tr>
			<tr><th>BMI</th><td><%= user.getBmi() %></td></tr>
			<tr><th>目標摂取カロリー</th><td><%= user.getTargetCal() %></td></tr>
		</table>
	</div>
	<h2>&#9660;直近のデータ7日分</h2>

		<% for(int i=0;i<list.size();i++){ %>
			<% if(list.get(i).getDate() != null){ %>
				<%
					double asa=(list.get(i).getBreakfastCal()/(double)cssCal)*50;
					double hiru=(list.get(i).getLunchCal()/(double)cssCal)*50;
					double ban=(list.get(i).getSupperCal()/(double)cssCal)*50;
					double cssCalWidth=(user.getTargetCal()/(double)cssCal)*50;
				%>
				<div id="graphbox">
					<div id="graph">
						<span style="width:<%= asa %>vw; background-color:red;"></span><span style="width:<%= hiru %>vw; background-color:green;"></span><span style="width:<%= ban %>vw; background-color:blue;"></span><br>
						<span style="width:<%= cssCalWidth %>vw; height:3px; background-color:#333;"></span>
					</div>
					<div class="dataspan">
						<span><%= list.get(i).getDate() %></span>
						<span class="timedata">朝:<%= list.get(i).getBreakfastCal() %></span><span class="timedata">昼:<%= list.get(i).getLunchCal() %></span><span class="timedata">晩:<%= list.get(i).getSupperCal() %></span><span class="timedata">合計:<%= list.get(i).getTotalCal() %></span>
						<% if(list.get(i).getTotalCal()<user.getTargetCal()){ %>
							<span style='color=white; background-color:#00a381;'><%= user.getTargetCal() - list.get(i).getTotalCal() %>kcal less!</span>
						<% }else if(list.get(i).getTotalCal()>user.getTargetCal()){ %>
							<span style="color=white; background-color:#ff7f50;"><%= list.get(i).getTotalCal() - user.getTargetCal() %>kcal over!</span>
						<% }else{ %>
							<span>目標ぴったりです！</span>
						<% } %>
					</div>
				</div>
			<% }else{ %>
			<% } %>
		<% } %>

	<form action="/calapp/Manage">
		<button type="submit">戻る</button>
	</form>
</div>
</body>
</html>