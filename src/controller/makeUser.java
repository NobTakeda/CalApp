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

@WebServlet("/MakeUser")
public class MakeUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid=request.getParameter("userid");
		String userpass=request.getParameter("userpass");
		String returnToLoginPage=request.getParameter("returnToLoginPage");
		if(returnToLoginPage != null) {
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/login.jsp");
			rd.forward(request,response);
		}else {
			UserDAO dao=new UserDAO();
			User user=new User();
			user=dao.findOne(userid);
			System.out.println("MakeUser.java内");

			if(user == null) {
				//新規ユーザー登録してmain.jspへ
				User newUser=new User();
				newUser.setUserid(userid);
				newUser.setUserpass(userpass);
				dao.insertNewUser(newUser);
				HttpSession session=request.getSession();
				session.setAttribute("user",newUser);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/main.jsp");
				rd.forward(request,response);
			}else {
				//既に同じIDのユーザーがいるため、エラーメッセージを返してmakeUser.jspへ
				String errorMsg="sameID";
				request.setAttribute("errorMsg",errorMsg);
				RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/makeUser.jsp");
				rd.forward(request,response);
			}
		}
	}
}
