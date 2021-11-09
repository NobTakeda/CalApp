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

@WebServlet("/FoodUpdate")
public class FoodUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date=request.getParameter("date");
		String userid=request.getParameter("userid");
		int id=Integer.parseInt(request.getParameter("id"));
		FoodDAO dao=new FoodDAO();
		System.out.println("↓FoodUpdate.java内");
		Food food=dao.findOne(id,userid);
		request.setAttribute("date", date);
		request.setAttribute("id", id);
		request.setAttribute("food", food);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/update.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		int cal=Integer.parseInt(request.getParameter("cal"));
		String date=request.getParameter("date");
		String userid=request.getParameter("userid");
		//System.out.print(date);
		int time=Integer.parseInt(request.getParameter("time"));
		FoodDAO dao=new FoodDAO();
		Food food=new Food(id,name,cal,time,date);
		dao.updateFood(food,userid);
		List<Food> list=dao.findToday(date,userid);
		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/manage.jsp");
		rd.forward(request, response);

	}

}
