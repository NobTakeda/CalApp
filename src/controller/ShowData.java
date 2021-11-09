package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MealsDAO;
import model.Meals;

@WebServlet("/ShowData")
public class ShowData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		System.out.println("ShowData.java内：ユーザーIDは"+userid);
		MealsDAO dao=new MealsDAO();
		List<Meals> mealsList=dao.findWeek(userid);
		request.setAttribute("list", mealsList);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/showdata.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
