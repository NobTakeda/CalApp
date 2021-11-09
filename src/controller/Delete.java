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

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String date=request.getParameter("date");
		String userid=request.getParameter("userid");
		FoodDAO dao=new FoodDAO();
		if(id != null) {
			dao.deleteFood(Integer.parseInt(id));
		}
		List<Food> list=dao.findToday(date,userid);
		request.setAttribute("list",list);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/manage.jsp");
		rd.forward(request, response);
	}

}
