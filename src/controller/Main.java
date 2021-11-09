package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;
import model.UserLogic;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		Double height=Double.parseDouble(request.getParameter("heightCm"));
		Double weight=Double.parseDouble(request.getParameter("weightKg"));
		int targetCal=Integer.parseInt(request.getParameter("targetCal"));
		User user=new User(name,height,weight);
		user.setTargetCal(targetCal);
		UserLogic ul=new UserLogic();
		ul.execute(user);
		String userid=request.getParameter("userid");
		String userpass=request.getParameter("userpass");
		user.setUserid(userid);
		user.setUserpass(userpass);
		//ここまででユーザーデータ作成、DAOを呼び出してusersテーブルを更新
		UserDAO dao=new UserDAO();
		dao.updateUser(user);

		HttpSession session=request.getSession();
		session.setAttribute("user", user);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/manage.jsp");
		rd.forward(request, response);

	}

}
