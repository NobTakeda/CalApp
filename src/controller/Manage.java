package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FoodDAO;
import model.Food;

@WebServlet("/Manage")
public class Manage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date=request.getParameter("date");
		FoodDAO dao=new FoodDAO();
		List<Food> list=dao.findToday(date);
		if(list.size()<1) {
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/manage.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("list", list);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/manage.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		int cal=Integer.parseInt(request.getParameter("cal"));
		String date=request.getParameter("date");
		String listDate=request.getParameter("listDate");
		//System.out.print(date);
		int time=Integer.parseInt(request.getParameter("time"));
		FoodDAO dao=new FoodDAO();
		Food food=new Food(name,cal,time,date);
		dao.ConnectCheck();
		dao.insertOne(food);
		if(listDate!=null && date != listDate) {
			request.setAttribute("date", listDate);
			RequestDispatcher rd=request.getRequestDispatcher("/Resister");
			rd.forward(request, response);
		}else {
			//入力したFoodをテーブルに追加
			List<Food> list=dao.findToday(date);
			request.setAttribute("list", list);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/manage.jsp");
			rd.forward(request, response);
		}
	}

}
